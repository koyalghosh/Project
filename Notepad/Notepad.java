import java.awt.event.*;
import java.awt.*;
import javax.swing.filechooser.*;//for JFileChooser class and other related class
import javax.swing.*;
import java.io.*;//for class File
import javax.swing.event.*;//for DocumentListener

public class Notepad extends JFrame implements ActionListener {
	JTextArea ta;
	JMenuBar mbar;
	JMenu mnuFile,mnuEdit;
	JMenuItem New,Open,Save,SaveAs,Exit,Cut,Copy,Paste;
	Font f1;
	File currFile=null;
	boolean saveFlag=true;
	Notepad(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if(!saveFlag) {
					int ans=JOptionPane.showConfirmDialog(Notepad.this,"Do you want to save changes ?","Notepad",JOptionPane.YES_NO_CANCEL_OPTION);
					//											msg on dialog box			,	title
					if(ans==JOptionPane.YES_OPTION ) {
						save();
						dispose();
					}
					else if(ans==JOptionPane.CANCEL_OPTION ) {
						
					}
				}
			}
		});
		
		f1=new Font("arial",Font.PLAIN,15);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Notepad");
		setExtendedState(MAXIMIZED_BOTH);//so that frame opens to window size
		ta=new JTextArea();
		ta.setFont(f1);
		add(ta);//BorderLayout.CENTER);
		
		//Document listener for text area
		ta.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				saveFlag=false;
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				saveFlag=false;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				saveFlag=false;
			}
			
		});
		
		//File menu Items
		New=new JMenuItem("New");
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));//adding shortcut
		Open=new JMenuItem("Open");
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		Save=new JMenuItem("Save");
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		SaveAs=new JMenuItem("SaveAs");
		Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		Exit=new JMenuItem("Exit");
		New.setMnemonic(KeyEvent.VK_N);
		Open.setMnemonic(KeyEvent.VK_O);
		Save.setMnemonic(KeyEvent.VK_S);
		SaveAs.setMnemonic(KeyEvent.VK_A);
		New.addActionListener(this);
		Open.addActionListener(this);
		Save.addActionListener(this);
		SaveAs.addActionListener(this);
		Exit.addActionListener(this);
		//File menu
		mnuFile=new JMenu("File");
		mnuFile.add(New);
		mnuFile.add(Open);
		mnuFile.add(Save);
		mnuFile.add(SaveAs);
		mnuFile.addSeparator();
		mnuFile.add(Exit);
		
		
		//Edit menu items
		Cut=new JMenuItem("Cut");
		Copy=new JMenuItem("Copy");
		Paste=new JMenuItem("Paste");
		Cut.addActionListener(this);
		Copy.addActionListener(this);
		Paste.addActionListener(this);
		//Edit menu
		mnuEdit=new JMenu("Edit");
		mnuEdit.add(Cut);
		mnuEdit.add(Copy);
		mnuEdit.add(Paste);
		
		
		//for menu bar
		mbar=new JMenuBar();
		mbar.add(mnuFile);
		mbar.add(mnuEdit);
		setJMenuBar(mbar);//adding menu bar to frame
		
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//ClipBoard class can be used for cut copy paste 
		String s1=e.getActionCommand();
		if(s1.equalsIgnoreCase("New")) {
			if(saveFlag==false) {
				int ans=JOptionPane.showConfirmDialog(Notepad.this, "Save?","Notepad",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(ans==JOptionPane.YES_OPTION) {
					save();
				}				
			}
			setTitle("Notepad");
			ta.setText("");
			saveFlag=true;
		}
		else if(s1.equalsIgnoreCase("Exit"))
			dispose();
		else if(s1.equalsIgnoreCase("Open")) {
			JFileChooser jfc=new JFileChooser("D:\\java");//to choose file from the given path
			FileNameExtensionFilter filter1=new FileNameExtensionFilter("TextFiles","txt");//shows text files of the given location
			jfc.addChoosableFileFilter(filter1);//adding the filter
			jfc.setFileFilter(filter1);//setting it to default
			int n=jfc.showOpenDialog(Notepad.this);
			if(n==JFileChooser.APPROVE_OPTION) {
				File f=jfc.getSelectedFile();
				setTitle("Notepad- "+f.getName());
				try(FileInputStream fis=new FileInputStream(f)){
					ta.setText(new String(fis.readAllBytes()));
				}
				catch(IOException i) {
					i.printStackTrace();
				}
			}
			else if(n==JFileChooser.CANCEL_OPTION) {
				
			}
			saveFlag=true;
			
		}
		else if(s1.equalsIgnoreCase("Save")) {
			save();
		}
		else if(s1.equalsIgnoreCase("SaveAs")) {
			saveAs();
		}
		else if(s1.equalsIgnoreCase("Cut"))
			ta.cut();
		else if(s1.equalsIgnoreCase("Copy"))
			ta.copy();
		else if(s1.equalsIgnoreCase("Paste"))
			ta.paste();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Bar();
		new Notepad();
	}
	void saveAs() {
		JFileChooser jfc=new JFileChooser("D://java");
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("TextFiles","txt");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int n=jfc.showSaveDialog(Notepad.this);
		if(n==JFileChooser.APPROVE_OPTION) {
			File f=jfc.getSelectedFile();
			setTitle("Notepad- "+f.getName());
			try(FileOutputStream fos=new FileOutputStream(f)){
				fos.write(ta.getText().getBytes());
			}
			catch(IOException i) {
				i.printStackTrace();
			}
			currFile=f;
			saveFlag=true;
		}
	}
	void save() {
		if(currFile==null) {
			saveAs();
		}
		else {
			try(FileOutputStream fos=new FileOutputStream(currFile)){
				fos.write(ta.getText().getBytes());
			}
			catch(IOException i) {
				i.printStackTrace();
			}
			saveFlag=true;
		}
	}

}
class Bar extends JDialog{
	ImageIcon ii;
	JLabel l;
	JProgressBar bar;
	Bar(){
		setTitle("NOTEPAD");
		ii =new ImageIcon("images1\\notepad3.jpg");
		ii=new ImageIcon(ii.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT));
		l=new JLabel(ii);
		add(l,BorderLayout.CENTER);
		bar=new JProgressBar(SwingConstants.HORIZONTAL);//for horizontal bar
		bar.setMinimum(1);
		bar.setMaximum(100);
		bar.setValue(0);
		bar.setBackground(Color.YELLOW);
		bar.setForeground(Color.BLUE);
		bar.setStringPainted(true);
		setUndecorated(true);
		setBounds(550,300,250,250);
		add(bar,BorderLayout.SOUTH);
		setVisible(true);//done here so that we can watch the progressing bar
		int i;
		for( i=0;i<=100;i++) {
			bar.setValue(i);
			bar.setString("loading...." + i + "%");//possible becoz of setStringPainted(true)
			try {
				Thread.sleep(50);
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		dispose();
	}
}

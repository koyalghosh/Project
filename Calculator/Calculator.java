//Simple calculator to do basic operations

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener{
	JTextField t1;
	JButton b[];
	Font f1,f2,f3;
	int cnt=0;
	JPanel key;
	double old=0.0;
	char op='\u0000';//for operator
	boolean concat=true;//when true concat numbers in text field
	String buttons[]= {"%","CE","C","\u232b"," 1/x","x\u00B2","\u221A","÷","7","8","9","X","4","5","6","-","1","2","3","+","±","0",".","="};
	
	Calculator(){
		setTitle("CALCULATOR");
		t1=new JTextField("0",20);
		f1=new Font("arial",Font.BOLD,70);//for text Field
		t1.setFont(f1);
		t1.setHorizontalAlignment(JTextField.RIGHT);//so that the text field text appear from right side
		add(t1,BorderLayout.NORTH);
		f2=new Font("arial",Font.PLAIN,20);//for buttons
		GridLayout grid=new GridLayout(6,4);
		key=new JPanel(grid);
		//buttons adding to frame
		b=new JButton[24];
		for(int i=0;i<24;i++) {
			b[i]=new JButton(buttons[i]);
			if(i==23)
				b[i].setBackground(new Color(167,40,199));//background of =
			else if(Character.isDigit(b[i].getText().charAt(0)))
				b[i].setBackground(new Color(207, 209, 209));
			else //background of operators and other symbols
				b[i].setBackground(new Color(113, 120, 119));
			if(i!=3)
				b[i].setFont(f2);
			b[i].addActionListener(this);
			b[i].addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent me) {//when mouse enters a button
					JButton b=(JButton)me.getSource();
					b.setBackground(Color.DARK_GRAY);
				}
				public void mouseExited(MouseEvent me) {//when mouse exits from a button
					JButton btn=(JButton)me.getSource();
					if(btn.getText().equals("="))
						btn.setBackground(new Color(167,40,199));
					else if(Character.isDigit(btn.getText().charAt(0)))
						btn.setBackground(new Color(207, 209, 209));
					else
						btn.setBackground(new Color(113, 120, 119));
				}
			});
			key.add(b[i]);
		}
		t1.setEditable(false);//so that we can't edit TextField
		add(key,BorderLayout.CENTER);
		setBounds(200,100,330,540);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			JButton b1=(JButton)e.getSource();
			String s1=b1.getText();//returns text of button
			String current=t1.getText();
			if(Character.isDigit(s1.charAt(0))) {//0 to 9
				if(concat==true) {
					t1.setText(filter(current+s1.charAt(0)));
				}
				else {
					t1.setText(filter(s1));//not concat overlap it.. used after any operator is clicked
					concat=true;
				}
			}
			else if(isOperator(s1.charAt(0))) {//+ - * ÷
				if(op!='\u0000') {//if any operator is already there i.e 5+4+6 
					double n=Double.parseDouble(current);
					if(op=='+')
						old=old+n;
					else if(op=='-')
						old=old-n;
					else if(op=='X')
						old=old*n;
					else if(op=='/')
						old=old/n;
					t1.setText(filter(old+""));
					current=t1.getText();
				}
				concat=false;
				op=s1.charAt(0);//storing clicked operator
				old=Double.parseDouble(current);
			}
			else if(s1.equals("=")) {//=
				double n=Double.parseDouble(current);
				if(op=='+')
					t1.setText(filter(old+n+""));
				else if(op=='-')
					t1.setText(filter(old-n+""));
				else if(op=='X')
					t1.setText(filter(old*n+""));
				else if(op=='÷')
					t1.setText(filter(old/n+""));
			}
			else if(s1.equals("%")) {//%
				double ans=old*Double.parseDouble(current)/100;
				t1.setText(filter(ans+""));
			}
			else if(s1.equals(".")) {
				if(current.indexOf('.')==-1)//if decimal not present in the whole string
					t1.setText(current+".");
			}
			else if(s1.equals("CE")) {//CE
				t1.setText("0");
			}
			else if(s1.equals("C")) {//C
				t1.setText("0");
				old=0;
				op='\u0000';
			}
			else if(s1.equals("\u232b")) {//backspace)
				t1.setText(filter(current.substring(0,current.length()-1)));
			}
			else if(s1.equals(" 1/x")) {// 1/x
				t1.setText(filter(1/Double.parseDouble(current)+""));
			}
			else if(s1.equals("x\u00B2")) {//x square
				t1.setText(filter(Double.parseDouble(current)*Double.parseDouble(current)+""));
			}
			else if(s1.equals("\u221A")) {// sqrt
				t1.setText(filter(Math.sqrt(Double.parseDouble(current))+""));
			}
			else if(s1.equals("±")) {//+/-
				t1.setText(filter(-Double.parseDouble(current)+""));
			}
			
	}
	String filter(String value) {//converts data of TextField to its appropriate data type
		if(value.isEmpty())
			return "0";
		double n=Double.parseDouble((value));
		if(n==(int)n)//if double of number is equals to its integer 
			return (int)n+"";
		return n+"";
	}
	boolean isOperator(char op) {//tells weather the symbol is operator or not
		if(op=='+' || op=='-' || op=='X' || op=='÷')
			return true;
		return false;
	}
}

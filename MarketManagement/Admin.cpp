//Make "Market.txt" 

#include<iostream>
#include<fstream>
#include<string.h>
using namespace std;
class Market{
    string name="Your name";
    string email="Email";
    string pass="password";
    int productCode;
    string productName;
    int discount;
    int quantity;
    int price;
    public:

    //Functions
    /*void administrator();
    void displayList();
    void adminFunction();
    void addNewProduct();
    void modifyProduct();
    void modifyQuantity(int );
    void deleteAll();
    void deleteProduct(int );
    void customer();
    void menuFunction();
    */

    void administrator(){
        string aname,aemail,apass;
        cout<<"ENTER ADMINISTATOR DETAILS:- \n";
        cout<<"Enter name:- ";
        getline(cin,aname);
        cout<<"Enter email address:- ";
        getline(cin,aemail);
        cout<<"Enter password:- ";
        getline(cin,apass);
        if(name.compare(aname)!=0 || email.compare(aemail)!=0 || pass.compare(apass)!=0){
            cout<<"...........................EXIT DUE TO INCORRECT DETAILS..........................";
            exit(0);
        }
        cout<<"\nSuccessfully recognised...\n";
        cout<<"\n                   Welcome back KOYAL GHOSH\n";
        adminFunction();
    }
    void displayList(){
            cout<<"\n\nOur Database have following items:\n\n\n";
            cout<<"Code\t\tName\t\tQuantity\tPrice(Rs.)\tDiscount(%)"<<endl;
            ifstream fin;
            fin.open("Market.txt");
            if(!fin){
                cout<<"File not opened\n";
            }
            string word;
            int count=0;
            while(fin >> word){
                cout<<word<<"\t\t";
                count++;
                if(word=="\n"){
                    cout<<endl;
                }
                if(count==5){
                    cout<<endl;
                    count=0;
                }
            }
            fin.close();
    }
    void adminFunction(){
            cout<<"\n\n_____________________________________________________________________________\n";
            cout<<"                                                                             \n";
            cout<<"***************** WELCOME   TO    ADMIN    BLOCK ****************************\n";
            cout<<"                                                                             \n";
            cout<<"_____________________________________________________________________________\n\n\n\n";
            while(true){
            cout<<"\nWhat you want to do ? "<<endl;
            cout<<"1.Add new product."<<endl;
            cout<<"2.Modify product details."<<endl;
            cout<<"3.Display list"<<endl;
            cout<<"4.Delete a product."<<endl;
            cout<<"5.Delete all record."<<endl;
            cout<<"6.Go back to main menu."<<endl;
            cout<<"7.Exit "<<endl;
            cout<<"ENTER YOUR CHOICE: ";
            int ch;string no;
            cin>>ch;
                switch(ch){
                    case 1:
                        addNewProduct();
                        break;
                    case 2:
                        cout<<"Enter the product code : ";
                        cin.ignore();
                        getline(cin,no);
                        modifyProduct(no);
                        break;
                    case 3:
                        displayList();
                        break;
                    case 4:
                        displayList();
                        cout<<"\nEnter product code : ";
                        cin.ignore();
                        getline(cin,no);
                        deleteProduct(no);
                        cout<<"\nDONE!\n\n";
                        break;
                    case 5:
                        deleteAll();
                        cout<<"\nDONE!\n\n";
                        break;
                    case 6:
                        menuFunction();
                        break;
                    case 7:
                        exit(0);
                    default :
                        cout<<"Enter correct choice"<<endl;
                }
            }
    }
    void addNewProduct(){
        cout<<"Enter product code:- ";
        cin>>productCode;
        cout<<"Enter product name:- ";
        cin>>productName;
        cout<<"Enter product price :- ";
        cin>>price;
        cout<<"Enter quantity:- ";
        cin>>quantity;
        cout<<"Enter discount on the product :- ";
        cin>>discount;
        cout<<endl<<"Successfully added!"<<endl<<endl;
        ofstream fout;
        fout.open("Market.txt",ios::app);
        if(!fout){
            cout<<"File not opened\n";
        }
        fout<<productCode<<" "<<productName<<" "<<quantity<<" "<<price<<" "<<discount<<endl;
        fout.close();
    }
    void modifyProduct(string no){
        deleteProduct(no);
        cout<<"Enter new product code:- ";
        cin>>productCode;
        cout<<"Enter new product name:- ";
        cin>>productName;
        cout<<"Enter new product price :- ";
        cin>>price;
        cout<<"Enter new quantity:- ";
        cin>>quantity;
        cout<<"Enter discount on the product :- ";
        cin>>discount;
        cout<<endl<<"Successfully modified!"<<endl<<endl;
        ofstream fout;
        fout.open("Market.txt",ios::app);
        if(!fout){
            cout<<"File not opened\n";
        }
        fout<<productCode<<" "<<productName<<" "<<quantity<<" "<<price<<" "<<discount<<endl;
        fout.close();
    }
    void deleteAll(){
        ifstream fin("Market.txt");
        remove("Market.txt");
        ofstream fout("Market.txt");
    }
    void deleteProduct(string deleteLine){
        ifstream fin("Market.txt");
        ofstream fout("temp.txt");
        string str1,str;
        string check="";
        while(getline(fin,str)){
            string s(str);
            for(int i=0;i<str.length();i++){
                if(s[i]==' '){
                    break;
                }
                check+=s[i];
            }
            if(check!=deleteLine){
                fout<<str<<endl;
            }
            check="";
        }
        fin.close();
        fout.close();
        remove("Market.txt");
        rename("temp.txt","Market.txt");
    }
    void menuFunction(){
        cout<<"\n\n__________________________________________________________________________________\n";
        cout<<"                                                                                  \n";
        cout<<"********************* WELCOME    TO        SUPERMARKET ***************************\n";
        cout<<"                                                                                  \n";
        cout<<"__________________________________________________________________________________\n";
        administrator();
    }
};
int main(){
        Market m1;
        while(true){
            m1.menuFunction();
            string ask;
            cout<<"Do you want to run again ?(Y/N) ";
            cin>>ask;
            if(ask=="N"){
                exit(0);
            }
        }
    
}

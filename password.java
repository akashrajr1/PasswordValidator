import java.util.Scanner;
import java.util.Random;

class PasswordGen
{

	String password="",username;

	public PasswordGen(String username)
	{
		this.username=username;
	}

	String [] commonpasswords ={"password","12345678","123456789","football","baseball","welcome","1234567890","abc123","1qaz2wsx","letmein","princess","qwertyuiop","passw0rd","starwars",""};
	char []  symbols= {'!','@','$','%','^','_','`','~',':','.',',','/','\\','\''}; 		//All the symbols which are permitted according to Microsoft Password Guidance 
	char [] invalid = {'>','=',';','<',']','[','{','}','(',')','#','&','*','?'};					//Invalid symbols have been selected according to regulations


	void password(){

		Random r = new Random();
		password+= (char)(r.nextInt(26)+'a');
		password+= (char)(r.nextInt(26)+'a');
		password+= (char)(r.nextInt(26)+'A');
		password+= (char)(r.nextInt(26)+'A');
		password+= (char)(r.nextInt(10)+'0');
		password+= (char)(r.nextInt(10)+'0');
		password+=symbols[r.nextInt(symbols.length)];
		password+=symbols[r.nextInt(symbols.length)];
		for(int k=0;k<100;k++)   						//Shuffling the password string
		{
			char ps[]=password.toCharArray();
			int p=r.nextInt(8);
			int q=r.nextInt(8);
			char temp=ps[p];
			ps[p]=ps[q];
			ps[q]=temp;
			password=new String(ps);
		}

		boolean flag=true;
		for(int i=0;i<=r.nextInt(6);i++)
			{char k=(char)(r.nextInt(77)+46);
				for(char s:invalid)
					if(s==k)
						flag=false;
			if(flag)
			password+=k;		
			}

		commonpasswords[commonpasswords.length-1]=username;				//Adding the username string to the commonpassword array
		for(int j=0;j<password.length()-username.length();j++)
			for(String s:commonpasswords)								//Cross checking if the commonpasswords are prsent as substrings or whole string in the password string. (Also username is also checked)
		if(password.substring(j,username.length()+j).toLowerCase().equals(s.toLowerCase()))
			password();
		System.out.println(password);
	}
	static boolean uservalid(String un){
		if(un.length()<5)
			return false;
		return true;
	}
	}

class password
{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String username="";

		while(!PasswordGen.uservalid(username))
		{
			System.out.println("Please Enter username:");
		 	username=in.next();
		 if(!PasswordGen.uservalid(username))
		 {
		 	System.out.println("Username is too weak (minimum length is 5)\n Do you want to continue? (y/n)");
		 	char choice =in.next().charAt(0);
		 	if(choice=='y')
		 		break; 
		 }
		}
		System.out.print("The generated Password is:  ");
		PasswordGen p=new PasswordGen(username);
		p.password();

}

}
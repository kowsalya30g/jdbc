package Client;

import Service.InputLogin;
import Service.ValidateInputs;

public class LibrarySystem {
	public static void main(String[] args) {
		boolean exit = true;
		do {
			System.out.println("1.Signup\n2.Login\n3.Exit");
			int option = ValidateInputs.intValidate();
			switch(option) {
			case 1:
				InputLogin.signup();
				break;
			case 2:
				InputLogin.Login();
				break;
			case 3:
				exit = false;
				break;
				
			}
			
		}while(exit);
		
	}

}

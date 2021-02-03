package Service;

import Connection.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Scanner;
import Entity.User;
import Implement.BookInsert;
import Implement.BookService;


public class InputLogin {
	static Scanner sc = new Scanner(System.in);

	public static void signup() {
		User ob = new User();
		try {
			 String query = "insert into user values(?,?,?,?,?)";
			 Connection con = JdbcConnection.connect();
			 PreparedStatement pst = con.prepareStatement(query);
			 do {
				 System.out.println("Enter User id");
				 ob.setUserId(ValidateInputs.intValidate());
				 System.out.println("Enter the name");
				 String name = sc.next();
				 ob.setUserName(ValidateInputs.stringValidate(name));
				 System.out.println("Enter the role ");
				 String role = sc.next();
				 ob.setRole(ValidateInputs.stringValidate(role));
				 System.out.println("Enter the phone number");
				 ob.setPhoneNo(ValidateInputs.intValidate());
				 System.out.println("Enter the password");
				 String password = sc.next();
				 ob.setPassword(ValidateInputs.stringValidate(password));
				 pst.setInt(1,ob.getUserId());
				 pst.setString(2, ob.getUserName());
				 pst.setString(3, ob.getRole());
				 pst.setInt(4, ob.getPhoneNo());
				 pst.setString(5, ob.getPassword());
				 int i = pst.executeUpdate();
				 System.out.println(i + " row affected");
				 System.out.println("Do u want to signup once more \n1 yes\n2 No");
				 int n=ValidateInputs.intValidate();
				 if(n == 1) {
					 
				 }else {
					 JdbcConnection.close(con, pst);
					 break;
				 }
			 } while(true);
		}catch(Exception e) {
			System.out.println(e+" insertion failure ");
		}
		
		
	}

	public static void Login() {
		System.out.println("Enter user id: ");
		int userid = ValidateInputs.intValidate();
		System.out.println("Enter the password");
		String password = sc.next();
		password = ValidateInputs.stringValidate(password);
		try {
			String query = "select * from user where userpassword=? and userId=?";
			Connection con = JdbcConnection.connect();
			PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst.setInt(2, userid);
			pst.setString(1, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()==false) {
				System.out.println("no such records");
			}
			else {
				System.out.println("Login success full");
				rs.previous();
				
				while(rs.next()) {
					Date da = new Date();
					
					System.out.println("\n");
					 System.out.println(" "+ rs.getInt(1) + "  "+rs.getString(2) + "  "+rs.getString(3)  +
							 " logged in at " +da);
					 if(rs.getString(3).compareTo("student")==0) {
						 student();
						 Date d1 = new Date();
						  System.out.println("\n");
						   System.out.println(" "+ rs.getInt(1) + "  "+rs.getString(2) + "  "+rs.getString(3)  +
									 " logged in at " +d1 );
						   JdbcConnection.close(con, pst);
					 }
					 else {
						 staff();
						 Date d2 = new Date();
						  System.out.println("\n");
						   System.out.println(" "+ rs.getInt(1) + "  "+rs.getString(2) + "  "+rs.getString(3)  +
									 " logged in at " +d2 );
						   JdbcConnection.close(con, pst);
						   System.out.println("\n");
					}
					 JdbcConnection.close(con, pst);
					
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void student() {
		boolean result = true;
		do {
			System.out.println("\n");
			System.out.println("1. ->View book details\n 2. -> purchase by id\n 3. -> logout ");
			int choice = ValidateInputs.intValidate();
			switch(choice) {
			case 1:
				BookService.displayBook();
				break;
			case 2:
				BookService.searchByID();
				break;
			case 3:
				
				result = false;
				System.out.println("Logout done");
				break;
			default:
				System.out.println("Invalid option ");
		}
		} while(result);
		System.out.println("\n");
		
	}

	public static void staff() {
		boolean result = true;
		do {
			System.out.println("\n");
			System.out.println("1. addbook\n 2. update book by quantity\n 3. delete book by id\n 4. Search book by id\n 5. display all book");
			int choice = ValidateInputs.intValidate();
			switch(choice) {
			case 1:
				BookInsert.addBook();
				break;
			case 2:
				BookInsert.updateBook();
				break;
			case 3:
				BookInsert.deleteById();
				break;
			case 4:
				BookInsert.searchById();
				break;
			case 5:
				BookInsert.displayAll();
				break;
			case 6:
				result = false;
				System.out.println("Logout done ");
				break;
			default:
				System.out.println("Invalid Option");
			}
			
		}while(result);
			System.out.println("\n");
	
		
	}
	

}

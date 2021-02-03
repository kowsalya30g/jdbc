import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import java.sql.PreparedStatement;

public class InsertData {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student1","root","E6EE038@12345");
			String s1 = "insert into student Values(1,'kowsi','chennai')";
			String s2 = "insert into student Values(2,'mukesh','tirunelveli')";
			String s3 = "insert into student Values(3,'zara','trichy')";
			PreparedStatement st = con.prepareStatement(s1);
			PreparedStatement st1 = con.prepareStatement(s2);
			PreparedStatement st2 = con.prepareStatement(s3);
			st.execute();
			st1.execute();
			st2.execute();
			con.close();
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		  
		
	}

}

package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class JdbcConnection {
	String url = "";
	String user = "";
	String password = "";
	
	public JdbcConnection(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static Connection connect() {
	   Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","E6EE038@12345");
			System.out.println("Connection success full");
		}catch(Exception e) {
			System.out.println(e+ " Connection failure");
		}
		return con;
	}
	
	public static void close(Connection con,PreparedStatement pst) {
		try {
			pst.close();
			con.close();
			System.out.println("connection closed");
		}catch(Exception e) {
			System.out.println("Error : "+e);
		}
	}

	
	
	
	public static void close(Connection con,Statement pst) {
		try {
			pst.close();
			con.close();
			System.out.println("connection closed");
		}catch(Exception e) {
			System.out.println("Error : "+e);
		}
	}
	
	
	

}

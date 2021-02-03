import java.sql.*;

public class ExampleProblem {
	public static void main(String[] args) {
		
		try 
		{
			  String Query = "SELECT * FROM STUDENT WHERE ROLLNO = 2";
			  Class.forName("com.mysql.jdbc.Driver");
			  
			  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DEMO","root","E6EE038@12345");
	          
			  Statement st = con.createStatement();
			  
			  ResultSet rs = st.executeQuery(Query);
			  rs.next();
			  String sname = rs.getString(2);
			  System.out.println(sname);
			  
			  con.close();
			  
			  
		} 
		
		catch (Exception e) 
		{
		
			e.printStackTrace();
		}
		
		
	} 

}

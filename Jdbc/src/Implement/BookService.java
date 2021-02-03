package Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.JdbcConnection;
import Service.ValidateInputs;

public class BookService {
    public static void displayBook() {
    	   String query = "select * from book";
    	   Connection con = JdbcConnection.connect();
    	   try {
    		   PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    		   ResultSet rs = pst.executeQuery();
    		   if(rs.next() == false) {
    			   System.out.println("No such records");
    		   }else {
    			   System.out.println("Login done ");
    			   rs.previous();
    			   while(rs.next()) {
    				   System.out.println("Bookid: "+rs.getInt(1) +" Book name: "+rs.getString(2)
    				   +" Book roll: "+rs.getString(3) + "Book quantity :"+rs.getInt(4) +" Book genre: "+rs.getString(5));
    			   }
    				   System.out.println("\n");
    				   JdbcConnection.close(con, pst);
    			   }
    		   } 
    	   catch( SQLException e) {
    			   e.printStackTrace();
    		   }
    	   }
		
		public static void searchByID() {
			System.out.println("Enter the book id:");
			int bookid = ValidateInputs.intValidate();
			String query = "select * from book where bookid = ?";
			//String querynext = "update book set bookquantity = (bookquantity-1) where bookid=bookid";
			try {
				Connection con = JdbcConnection.connect();
				PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				pst.setInt(1, bookid);
				ResultSet rs = pst.executeQuery();
				if(rs.next()==false) {
					JdbcConnection.close(con, pst);
					throw new Exception("Book not in the list");
				}else {
					rs.previous();
					rs.next();
					System.out.println("Book present here");
					if(rs.getInt(4)==0) {
						JdbcConnection.close(con, pst);
						throw new Exception("Book not available");
					}
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
				
			
		
		
	}

}

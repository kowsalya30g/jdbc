package Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import Connection.JdbcConnection;
import Entity.Book;
import Service.ValidateInputs;

public class BookInsert {
	static Scanner sc = new Scanner(System.in);
	

	public static void addBook() {
		String query = "insert into book values(?,?,?,?,?)";
		while(true) {
			Book obj = new Book();
			System.out.println("Enter the book id: ");
			int bookid = ValidateInputs.intValidate();
			System.out.println("Enter the book name");
			String name = sc.next();
			name = ValidateInputs.stringValidate(name);
			System.out.println("Enter the book author");
			String author = sc.next();
			author = ValidateInputs.stringValidate(author);
			System.out.println("Enter the book quantity ");
			int quantity = ValidateInputs.intValidate();
			System.out.println("Enter the book genre");
			String genre = sc.next();
			genre = ValidateInputs.stringValidate(genre);
			try {
				Connection con = JdbcConnection.connect();
				PreparedStatement pst = con.prepareStatement(query);
				pst.setInt(1, bookid);
				pst.setString(2, name);
				pst.setString(3, author);
				pst.setInt(4, quantity);
				pst.setString(5, genre);
				int i = pst.executeUpdate();
				System.out.println(i + " row effected ");
				System.out.println(obj);
				System.out.println("Do u want to sign up one more\n 1-> yes\n 2 -> No");
				int n = ValidateInputs.intValidate();
				if(n==1 ) {
					
				}else {
					JdbcConnection.close(con, pst);
					break;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	public static void updateBook() {
		String query ="update book set bookquantity =? where bookId =?";
		System.out.println("Enter book id:");
		int bookid = ValidateInputs.intValidate();
		System.out.println("Enter the book quantity");
		int quantity = ValidateInputs.intValidate();
		try {
			Connection con = JdbcConnection.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1,quantity);
			pst.setInt(2,bookid);
			int i = pst.executeUpdate();
			if(i>0) {
				System.out.println("Quantity updated");
				JdbcConnection.close(con, pst);
			}else {
				JdbcConnection.close(con, pst);
				throw new Exception("update not successfull ");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteById() {
		String query = "delete  from book where bookId=?";
		System.out.println("Enter book id");
		int bookid = ValidateInputs.intValidate();
		try {
			Connection con = JdbcConnection.connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, bookid);
			int i = pst.executeUpdate();
			if(i>0) {
				System.out.println("book deleted ");
				JdbcConnection.close(con, pst);
				
			}else {
				JdbcConnection.close(con, pst);
				throw new Exception("query not done");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void searchById() {
		String query = "Select * from book where bookId=?";
		System.out.println("Enter the book id");
		int bookid = ValidateInputs.intValidate();
		try {
			Connection con = JdbcConnection.connect();
			PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst.setInt(1, bookid);
			ResultSet rs = pst.executeQuery();
			if(rs.next()==false) {
				JdbcConnection.close(con, pst);
				throw new Exception("no such records");
			}else {
				rs.previous();
				rs.next();
				System.out.println("Bookid: "+rs.getInt(1) + "  Book name: "+rs.getString(2)
						+ " Book roll: " + rs.getString(3) + "Book quantity : "+rs.getInt(4) +" Book genre: "+rs.getString(5));
				JdbcConnection.close(con, pst);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public static void displayAll() {
		String query = "Select * from book";
		try {
			Connection con = JdbcConnection.connect();
			PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pst.executeQuery();
			if(rs.next()==false) {
				JdbcConnection.close(con, pst);
				throw new Exception("no such records");
				
			}else {
				rs.previous();
				while(rs.next())
				{
					System.out.println("Bookid: "+rs.getInt(1) + "  Book name: "+rs.getString(2)
						+ " Book roll: " + rs.getString(3) + "Book quantity : "+rs.getInt(4) +" Book genre: "+rs.getString(5));
				}
				JdbcConnection.close(con, pst);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
				
	}

}

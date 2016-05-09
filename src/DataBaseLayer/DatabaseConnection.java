package DataBaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import LoginStructs.LoginAccount;

public class DatabaseConnection implements IDatabase {

	   public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   public static final String DB_URL = "jdbc:mysql://localhost:3306/clients?useSSL=false";

	   //  Database credentials
	   public static final String USER = "root";
	   public static final String PASS = "123456";
	  
	   
	   private Connection conn;
	   private Statement stmt;
	   
	   public DatabaseConnection () {

		 
		      //STEP 2: Register JDBC driver
		      try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			

		      
		      } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		      
		      System.out.println("Connected database successfully...");
		      
		      try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		      System.out.println("success");
	   }
	
	
	
	@Override
	public boolean insertAccount(LoginAccount acc) {
		String insertTableSQL = "INSERT INTO clients"
				+ "(id, password, name) VALUES"
				+ "(?,?,?)";
		
		
		try {
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, acc.getId());
		preparedStatement.setString(2, acc.getPassword());
		preparedStatement.setString(3, acc.getName());
		
		// execute insert SQL stetement

			preparedStatement .executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
   
    return true;
	}

	@Override
	public boolean deleteAccount(LoginAccount acc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(LoginAccount acc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LoginAccount getAccount(int id) {
		String selectSQL = "SELECT id, password, name FROM clients WHERE id = ?";
		
		try {
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(selectSQL);

			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int userid = rs.getInt("id");
				String password = rs.getString("password");	
				String name = rs.getString("name");	
				
				LoginAccount acc = new LoginAccount(name , userid, password);
				
				return acc;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		
		
		return null;
	}

}

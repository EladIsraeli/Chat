package DataBaseLayer;

import LoginStructs.LoginAccount;

public class testDB {

	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		
		
		
		db.insertAccount(new LoginAccount("bla", 1, "bla"));
	}

}

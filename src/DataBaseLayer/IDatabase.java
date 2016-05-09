package DataBaseLayer;

import LoginStructs.LoginAccount;

public interface IDatabase {
	
	boolean insertAccount(LoginAccount acc);
	boolean deleteAccount(LoginAccount acc);
	boolean updateAccount(LoginAccount acc);
	LoginAccount getAccount(int id);

}

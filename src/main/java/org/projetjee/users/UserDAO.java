package org.projetjee.users;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface UserDAO {
	
	public ArrayList<User> findByUserName(String userName, String password);
	
	public boolean CreateUser(User user) throws NoSuchAlgorithmException;
	
	public boolean DeleteUser(String username);

}

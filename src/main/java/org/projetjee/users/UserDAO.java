package org.projetjee.users;

import java.util.ArrayList;

public interface UserDAO {
	
	public ArrayList<User> findByUserName(String userName, String password);

}

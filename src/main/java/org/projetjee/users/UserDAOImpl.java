package org.projetjee.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.projetjee.DBManager;

public class UserDAOImpl implements UserDAO {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<User> findByUserName(String userName, String password) throws NoSuchAlgorithmException {
		ArrayList<User> users = new ArrayList<User> ();
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("select * from user where username='%s' and password='%s'", userName, this.doHash(password));
		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			if (rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("password"), UserRole.valueOf(rs.getString("role"))));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return users;
		}
		
	}	

	@Override
	public boolean CreateUser(User user) throws NoSuchAlgorithmException {
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("insert into user(username, password, role) values('%s', '%s', '%s')", user.getUserName(),
				this.doHash(user.getPassword()), user.getRole().toString());
		try {
			conn.createStatement().executeUpdate(query);
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private String doHash(String message) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encoded = digest.digest(message.getBytes(StandardCharsets.UTF_8));
		return UserDAOImpl.bytesToHex(encoded);
	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuilder hexString = new StringBuilder(2 * hash.length);
	    for (int i = 0; i < hash.length; i++) {
	        String hex = Integer.toHexString(0xff & hash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}

	@Override
	public boolean DeleteUser(String username, String password) throws NoSuchAlgorithmException {
		if (this.findByUserName(username, password).size() == 0)
			return false;
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("delete from user where username='%s'", username);
		try {
			conn.createStatement().executeUpdate(query);
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

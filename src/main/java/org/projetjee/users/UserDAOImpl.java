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
	public ArrayList<User> findByUserName(String userName, String password) {
		ArrayList<User> users = new ArrayList<User> ();
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("select * from user where username='%s' and password='%s'", userName, password);
		try {
			ResultSet rs = conn.createStatement().executeQuery(query);
			if (rs.next()) {
				users.add(new User(rs.getString("username"), rs.getString("password"), UserRole.valueOf(rs.getString("role"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return users;
		}
		
	}

	@Override
	public boolean CreateUser(User user) throws NoSuchAlgorithmException {
		System.out.print(doHash(user.getPassword()));
		return true;
		/*
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("select into user values(%s, %s, %s)", user.getUserName(),
				this.doHash(user.getPassword()), user.getRole().toString());
		try {
			conn.createStatement().executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}*/
	}
	
	private String doHash(String message) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encoded = digest.digest(message.getBytes(StandardCharsets.UTF_8));
		return new String(Base64.getDecoder().decode(encoded));
	}

	@Override
	public boolean DeleteUser(String username) {
		Connection conn = DBManager.getInstance().getConnection();
		String query = String.format("delete from user where username=%s", username);
		try {
			conn.createStatement().executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}

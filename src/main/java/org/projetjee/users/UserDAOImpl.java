package org.projetjee.users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

}

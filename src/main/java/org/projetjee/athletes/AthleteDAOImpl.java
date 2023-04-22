package org.projetjee.athletes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.projetjee.DBManager;

public class AthleteDAOImpl implements AthleteDAO{

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Athlete> findByAll() {
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from athlete");
			while ( rs.next() ) {
				athletes.add(new Athlete(rs.getString("first_name"), rs.getString("last_name"), rs.getDate("birthday"),
						AthleteGender.valueOf(rs.getString("gender")), rs.getString("nationality"), rs.getString("discipline_name")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return athletes;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Athlete> findByName(String firstName, String lastName) {
		ArrayList<Athlete> athletes = new ArrayList<Athlete>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(String.format("select * from athlete where first_name='%s' and last_name='%s'",
					firstName, lastName));
			while ( rs.next() ) {
				athletes.add(new Athlete(rs.getString("first_name"), rs.getString("last_name"), rs.getDate("birthday"),
						AthleteGender.valueOf(rs.getString("gender")), rs.getString("nationality"), rs.getString("discipline_name")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return athletes;
		}
	}

	@Override
	public boolean Import(List<String[]> rows) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			conn.createStatement().executeUpdate("delete from athlete");
			String query_base = "insert into athlete values(?, ?, ?, ?, ?, ?);";
			PreparedStatement pstatement = conn.prepareStatement(query_base);
			for (String[] row : rows) {
				pstatement.setString(1, row[0]);				// first_name
				pstatement.setString(2, row[1]);				// last_name
				pstatement.setDate(3, Date.valueOf(row[2]));	// birthday
				pstatement.setString(4, row[3]);				// gender
				pstatement.setString(5, row[4]);				// nationality
				pstatement.setString(6, row[5]);				// discipline_name
				pstatement.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean DeleteAthlete(String firstName, String lastName) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("delete from athlete where first_name='%s' and last_name='%s'",
					firstName, lastName));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

}

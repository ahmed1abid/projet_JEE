package org.projetjee.disciplines;

import org.projetjee.DBManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DisciplineDAOImpl implements DisciplineDAO {

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Discipline> findByAll() {
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from discipline");
			while ( rs.next() ) {
				disciplines.add(new Discipline(rs.getString("name"), rs.getBoolean("flag")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return disciplines;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Discipline> findByName(String searchText) {
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from discipline where name like '%"+searchText+"%'");
			while ( rs.next() ) {
				disciplines.add(new Discipline(rs.getString("name"), rs.getBoolean("flag")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return disciplines;
		}
	}

	@Override
	public boolean CreateDiscipline(Discipline new_discipline) {
		System.out.println("test");
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("insert into discipline values('%s', %b)",
					new_discipline.getName(), new_discipline.getFlag()));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

	@Override
	public boolean EditDiscipline(String name, Boolean new_flag) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("update discipline set flag=%b where name='%s'",
					new_flag, name));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

	@Override
	public boolean DeleteDiscipline(String name) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("delete from discipline where name='%s'", name));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public DisciplineStatistics getStatistics(String name) {
		DisciplineStatistics stats = new DisciplineStatistics(name);
		Connection conn = DBManager.getInstance().getConnection();
		String query;
		ResultSet rs;
		try {
			Statement statement = conn.createStatement();
			
			query = "select count(*) from athlete where discipline_name='"+name+"'";
			rs = statement.executeQuery(query);
			if (rs.next())
				stats.setNbAtheletes(rs.getInt(1));
			
			query = "select count(*) from session where discipline='"+name+"'";
			rs = statement.executeQuery(query);
			if (rs.next())
				stats.setNbSessions(rs.getInt(1));
			
			query = "select round(avg(age)) from athlete_age AA, athlete A "
					+ "where AA.first_name = A.first_name and "
					+ "AA.last_name = A.last_name and A.discipline_name='"+name+"'";
			rs = statement.executeQuery(query);
			if (rs.next())
				stats.setAverageAge(rs.getInt(1));
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return stats;
		}
	}

}

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
	public void CreateDiscipline(Discipline new_discipline) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("insert into discipline values('%s', '%s')",
					new_discipline.getName(), new_discipline.getFlag()));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void EditDiscipline(String name, Boolean new_flag) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("update discipline set flag='%d' where name='%s'",
					new_flag, name));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void DeleteDiscipline(String name) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("delete from discipline where name='%s'", name));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

}

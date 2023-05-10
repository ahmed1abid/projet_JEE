package org.projetjee.sessions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import org.projetjee.DBManager;

public class SessionDAOImpl implements SessionDAO{

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Session> findByAll() {
		ArrayList<Session> sessions = new ArrayList<Session>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select code, date, start_time, "
					+ "end_time, discipline.name as dsc_name, site.name as site_name, site.city as site_city, description, "
					+ "type, session.category as ctgry from session, site, discipline"
					+ "where session.idSite = site.idSite and session.discipline = discipline.name");
			while ( rs.next() ) {
				sessions.add(new Session(rs.getString("code"), rs.getDate("date"), rs.getTime("start_time"),
						rs.getTime("end_time"), rs.getString("dsc_name"), rs.getString("site_name"),
						rs.getString("site_city"), rs.getString("description"),
						SessionType.valueOf(rs.getString("type")), SessionCategory.valueOf(rs.getString("ctgry"))));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return sessions;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Session> findByCode(String searchText) {
		ArrayList<Session> sessions = new ArrayList<Session>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select code, date, start_time, "
					+ "end_time, discipline.name as dsc_name, site.name as site_name, site.city as site_city, description, "
					+ "type, session.category as ctgry from session, site, discipline"
					+ "where session.idSite = site.idSite and session.discipline = discipline.name and code='"+searchText+"'");
			if ( rs.next() ) {
				sessions.add(new Session(rs.getString("code"), rs.getDate("date"), rs.getTime("start_time"),
						rs.getTime("end_time"), rs.getString("dsc_name"), rs.getString("site_name"),
						rs.getString("site_city"), rs.getString("description"),
						SessionType.valueOf(rs.getString("type")), SessionCategory.valueOf(rs.getString("ctgry"))));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return sessions;
		}
	}

	@Override
	public boolean CreateSession(Session new_session) throws TimeOverlapException {
		Connection conn = DBManager.getInstance().getConnection();
		
		Time start_time_suggestion = checkTimeOverlap(conn, new_session.getDiscipline(),
				new_session.getSite_name(), new_session.getDate(), new_session.getStart_time());
		if (start_time_suggestion == null) {	// start time specified by the user is valid
			try {
				String query_base = "insert into session values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pstatement = conn.prepareStatement(query_base);
				pstatement.setString(1, new_session.getCode());
				pstatement.setDate(2, new_session.getDate());
				pstatement.setTime(3, new_session.getStart_time());
				pstatement.setTime(4, new_session.getEnd_time());
				pstatement.setString(5, new_session.getDiscipline());
				pstatement.setInt(6, new_session.getSite_id());
				pstatement.setString(7, new_session.getDescription());
				pstatement.setString(8, new_session.getType());
				pstatement.setString(9, new_session.getCategory());
				pstatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println(e);
				return false;
			}
		}
		throw new TimeOverlapException("Proposer de démarrer la session à " + start_time_suggestion.toString());
	}
	
	private Time checkTimeOverlap(Connection conn, String discipline, String site, Date date, Time start_time) {
		try {
			String query_base = String.format("select end_time from session "
					+ "where idSite='%s' group by end_time desc "
					+ "having discipline='%s' and date=?", site, discipline);
			PreparedStatement pstatement = conn.prepareStatement(query_base);
			pstatement.setDate(1, date);
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {
				LocalTime latest_end_time = rs.getTime("end_time").toLocalTime();
				LocalTime user_start_time = start_time.toLocalTime();
				if (latest_end_time.until(user_start_time, ChronoUnit.HOURS) < 1) {
					return Time.valueOf(latest_end_time.plusHours(1));	// suggestion
				}
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean EditSession(String code, Session session) throws TimeOverlapException {
		Connection conn = DBManager.getInstance().getConnection();
		
		String[] sessionsToExclude = new String[] {code};
		Time nearest_available_time = checkTimeOverlapExclude(conn, session.getDiscipline(), session.getStart_time(), sessionsToExclude);
		if (nearest_available_time == null) {
			try {
				String query_base = "update session set date=?, start_time=?, end_time=?, discipline=?, "
						+ "idSite=?, description=?, type=?, category=? where code=?";
				PreparedStatement pstatement = conn.prepareStatement(query_base);
				pstatement.setDate(1, session.getDate());
				pstatement.setTime(2, session.getStart_time());
				pstatement.setTime(3, session.getEnd_time());
				pstatement.setString(4, session.getDiscipline());
				pstatement.setInt(5, session.getSite_id());
				pstatement.setString(6, session.getDescription());
				pstatement.setString(7, session.getType());
				pstatement.setString(8, session.getCategory());
				pstatement.setString(9, code);
				pstatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				System.out.println(e);
				return false;
			}
		} throw new TimeOverlapException("Proposer de démarrer la session à " + nearest_available_time.toString());
		
		
	}
	
	private Time checkTimeOverlapExclude(Connection conn, String discipline, Time start_time, String[] codes) {
		String query = String.format("select end_time from session group by end_time desc "
				+ "having discipline='%s'", discipline);
		for (String code : codes) {
			query += " and code != " + code;
		}
		try {
			ResultSet rs = conn.createStatement().executeQuery(String.format("select end_time from session group by end_time desc "
					+ "having discipline='%s'", discipline));
			if (rs.next()) {
				LocalTime latest_end_time = rs.getTime("end_time").toLocalTime();
				LocalTime user_start_time = start_time.toLocalTime();
				if (latest_end_time.until(user_start_time, ChronoUnit.HOURS) < 1) {
					return Time.valueOf(latest_end_time.plusHours(1));	// nearest available time
				}
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean DeleteSession(String code) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("delete from session where code='%s'", code));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}

}

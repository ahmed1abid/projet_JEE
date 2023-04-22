package org.projetjee.sites;

import org.projetjee.DBManager;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SiteDAOImpl implements SiteDAO{

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Site> findByAll() {
		ArrayList<Site> sites = new ArrayList<Site>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from site");
			while ( rs.next() ) {
				sites.add(new Site(rs.getInt("idSite"), rs.getString("name"), rs.getString("city"), SiteCategory.valueOf(rs.getString("category"))));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return sites;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public ArrayList<Site> findByName(String searchText) {
		ArrayList<Site> sites = new ArrayList<Site>();
		Connection conn = DBManager.getInstance().getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select * from site where name like '%"+searchText+"%'");
			while ( rs.next() ) {
				sites.add(new Site(rs.getInt("idSite"), rs.getString("name"), rs.getString("city"), SiteCategory.valueOf(rs.getString("category"))));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			return sites;
		}
	}
	
	@Override
	public void CreateSite(Site new_site) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("select name, city from site");
			while ( rs.next() ) {
				if (rs.getString("name").equals(new_site.getName()) && (rs.getString("city").equals(new_site.getCity()))) {
					throw new SQLException(String.format("Le site '%s' dans la ville '%s' exite déjà", new_site.getName(),
							new_site.getCity()));
				}
			}
			statement.executeUpdate(String.format("insert into site(name, city, category) values('%s', '%s', '%s')",
					new_site.getName(), new_site.getCity(), new_site.getCategory()));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void EditSite(int id, Site site) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("update site set name='%s', city='%s', category='%s' "
					+ "where idSite=%d",
					site.getName(), site.getCity(), site.getCategory(), id));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void DeleteSite(int id) {
		Connection conn = DBManager.getInstance().getConnection();
		try {
			
			Statement statement = conn.createStatement();
			statement.executeUpdate(String.format("delete from site where idSite=%d", id));
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

}

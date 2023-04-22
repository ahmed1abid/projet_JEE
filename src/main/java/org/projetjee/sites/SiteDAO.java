package org.projetjee.sites;

import java.util.ArrayList;

public interface SiteDAO {
	
	public ArrayList<Site> findByAll();
	
	public ArrayList<Site> findByName(String searchText);
	
	public void CreateSite(Site new_site);
	
	public void EditSite(int id, Site site);
	
	public void DeleteSite(int id);

}

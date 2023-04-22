package org.projetjee.sites;

import java.util.ArrayList;

public interface SiteDAO {
	
	public ArrayList<Site> findByAll();
	
	public ArrayList<Site> findByName(String searchText);
	
	public boolean CreateSite(Site new_site) throws SiteAlreadyExistsException;
	
	public boolean EditSite(int id, Site site);
	
	public boolean DeleteSite(int id);

}

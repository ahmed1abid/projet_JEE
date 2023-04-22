package org.projetjee.sites;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/site-management")
public class SiteController {
	
	private SiteDAO siteDAO = new SiteDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sites/")
	public String getSitesByName(@QueryParam("name") String name) {
		ArrayList<Site> sites;
		if (name != "" && name != null) {
			sites = siteDAO.findByName(name);
		} else {
			sites = siteDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(sites);
		return json;
	}
	
	@POST
	@Path("/new/")
	public void createSite(@FormParam("name") String name,
						   @FormParam("city") String city,
						   @FormParam("category") String category) {
		Site site = new Site(name, city, SiteCategory.valueOf(category));
		siteDAO.CreateSite(site);
	}
	
	@POST
	@Path("/edit/")
	public void editSite(@QueryParam("id") int id,
						   @FormParam("newName") String newName,
						   @FormParam("newCity") String newCity,
						   @FormParam("newCategory") String newCategory) {
		Site site = new Site(newName, newCity, SiteCategory.valueOf(newCategory));
		siteDAO.EditSite(id, site);
		
	}
	
	@POST
	@Path("/delete/")
	public void deleteSite(@QueryParam("id") int id) {
		siteDAO.DeleteSite(id);
	}
	
}
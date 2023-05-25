package org.projetjee.sites;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/site-management/*")
public class SiteServlet extends HttpServlet {
	
	private SiteDAO siteDAO = new SiteDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/sites"))
			this.doGetSitesByName(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/new"))
			this.doCreateSite(request, response);
		else if (pathInfo.equals("/edit"))
			this.doEditSite(request, response);
		else if (pathInfo.equals("/delete"))
			this.doDeleteSite(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	private void doGetSitesByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		ArrayList<Site> sites;
		if (name != "" && name != null) {
			sites = siteDAO.findByName(name);
		} else {
			sites = siteDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(sites);
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(json);
	}
	
	private void doCreateSite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesC") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String category = request.getParameter("category");
		
		Site site = new Site(name, city, SiteCategory.valueOf(category));
		try {
			if (!siteDAO.CreateSite(site))
				response.setStatus(500);
			else
				response.setStatus(200);
		} catch(SiteAlreadyExistsException e) {
			response.setStatus(422);
			response.getWriter().write(e.getMessage());
		}
	}
	
	private void doEditSite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesC") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		int id = Integer.valueOf(request.getParameter("id"));
		String newName = request.getParameter("newName");
		String newCity = request.getParameter("newCity");
		String newCategory = request.getParameter("newCategory");
		Site site = new Site(newName, newCity, SiteCategory.valueOf(newCategory));
		if (!siteDAO.EditSite(id, site)) 
			response.setStatus(500);
		else
			response.setStatus(200);
		
	}
	
	private void doDeleteSite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesS") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		int id = Integer.valueOf(request.getParameter("id"));
		if (!siteDAO.DeleteSite(id))
			response.setStatus(500);
		else
			response.setStatus(200);
	}
	
}
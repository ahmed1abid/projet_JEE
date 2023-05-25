package org.projetjee.sessions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/session-management/*")
public class SessionServlet extends HttpServlet {
	
	private SessionDAO sessionDAO = new SessionDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/sessions"))
			this.doGetSessionByCode(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/new"))
			this.doCreateSession(request, response);
		else if (pathInfo.equals("/edit"))
			this.doEditSession(request, response);
		else if (pathInfo.equals("/delete"))
			this.doDeleteSession(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	private void doGetSessionByCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		ArrayList<Session> sessions;
		if (code != "" && code != null) 
			sessions = sessionDAO.findByCode(code);
		else 
			sessions = sessionDAO.findByAll();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(sessions);
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(json);
	}
	
	private void doCreateSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesS") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String code = request.getParameter("code");
		Date date = Date.valueOf(request.getParameter("date"));
		Time start_time = Time.valueOf(request.getParameter("start_time"));
		Time end_time = Time.valueOf(request.getParameter("end_time"));
		String discipline = request.getParameter("dicipline");
		int site = Integer.valueOf(request.getParameter("site"));
		String description = request.getParameter("description");
		String type = request.getParameter("type");
		String category = request.getParameter("category");
				
		Session session = new Session(code, date, start_time, end_time, discipline, site, description,
				SessionType.valueOf(type), SessionCategory.valueOf(category));
		if (!session.validCode()) {
			System.out.println("Session code must match '3 first letters of discipline + two-digits number'");
			response.setStatus(422);
			response.getWriter().write("Session code must match '3 first letters of discipline + two-digits number'");
		} else {
			try {
				if (!sessionDAO.CreateSession(session))
					response.setStatus(422);
				else
					response.setStatus(200);
			} catch (TimeOverlapException e) {
				e.printStackTrace();
				response.setStatus(422);
				response.getWriter().write(e.getMessage());
			}
		}
	}
	
	private void doEditSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesS") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String code = request.getParameter("code");
		Date newDate = Date.valueOf(request.getParameter("date"));
		Time newStart_time = Time.valueOf(request.getParameter("start_time"));
		Time newEnd_time = Time.valueOf(request.getParameter("end_time"));
		String newDiscipline = request.getParameter("dicipline");
		int newSite = Integer.valueOf(request.getParameter("site"));
		String newDescription = request.getParameter("description");
		String newType = request.getParameter("type");
		String newCategory = request.getParameter("category");
		
		Session session = new Session(code, newDate, newStart_time, newEnd_time, newDiscipline, newSite, newDescription,
				SessionType.valueOf(newType), SessionCategory.valueOf(newCategory));
		try {
			if (!sessionDAO.EditSession(code, session))
				response.setStatus(500);
			else
				response.setStatus(200);
		} catch (TimeOverlapException e) {
			e.printStackTrace();
			response.setStatus(422);
			response.getWriter().write(e.getMessage());
		}	
	}
	
	private void doDeleteSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession usersession = request.getSession(false);
    	if (usersession == null || usersession.getAttribute("role") != "gesS") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String code = request.getParameter("code");
		if (!sessionDAO.DeleteSession(code)) {
			response.setStatus(500);
		} else
			response.setStatus(200);
	}
}

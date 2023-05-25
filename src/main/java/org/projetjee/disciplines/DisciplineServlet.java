package org.projetjee.disciplines;

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

@WebServlet("/discipline-management/*")
public class DisciplineServlet extends HttpServlet {
	
	private DisciplineDAO disciplineDAO = new DisciplineDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/disciplines"))
			this.doGetDisciplines(request, response);
		else if (pathInfo.equals("/statistics"))
			this.doGetStatistics(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/new"))
			this.doCreateDiscipline(request, response);
		else if (pathInfo.equals("/edit"))
			this.doSetDisciplineFlag(request, response);
		else if (pathInfo.equals("/delete"))
			this.doDeleteDiscipline(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	
	private void doGetDisciplines(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		ArrayList<Discipline> disciplines;
		if (name != "" && name != null) {
			disciplines = disciplineDAO.findByName(name);
		} else {
			disciplines = disciplineDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(disciplines);
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(json);
	}
	
	
	private void doGetStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String discipline = request.getParameter("discipline");
		if (discipline != "" && discipline != null) {
			DisciplineStatistics stats = disciplineDAO.getStatistics(discipline);
			GsonBuilder builder = new GsonBuilder();
			Gson gson = builder.create();
			String json = gson.toJson(stats);

			response.setContentType("application/json");
			response.setStatus(200);
			response.getWriter().write(json);
		} else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
		
	}
	
	private void doCreateDiscipline(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("role") != "gesC") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String name = request.getParameter("name");
		Integer flag = Integer.valueOf(request.getParameter("flag"));
		Discipline discipline = new Discipline(name, flag!=0);
		if (!disciplineDAO.CreateDiscipline(discipline)) {
			response.setStatus(500);
		} else
			response.setStatus(200);
	}
	
	private void doSetDisciplineFlag(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("role") != "gesC") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String name = request.getParameter("name");
		Integer new_flag = Integer.valueOf(request.getParameter("flag"));
		if (!disciplineDAO.EditDiscipline(name, new_flag!=0)) {
			response.setStatus(500);
		} else
			response.setStatus(200);
	}
	
	private void doDeleteDiscipline(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("role") != "gesC") {
    		response.setStatus(403);
    		response.sendRedirect("/projet_JEE/vues/login.jsp");
    		return;
    	}
		String name = request.getParameter("name");
		if (!disciplineDAO.DeleteDiscipline(name)) {
			response.setStatus(500);
		} else
			response.setStatus(200);
	}
}

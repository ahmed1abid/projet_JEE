package org.projetjee.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/user-management/*")
public class UserServlet extends HttpServlet {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/user"))
			this.doGetUserByAccount(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	private void doGetUserByAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ArrayList<User> users;
		if (username != null && password != null) 
			users = userDAO.findByUserName(username, password);
		else 
			users = new ArrayList<User>();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json =A gson.toJson(users);
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(json);
	}
	  private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        ArrayList<User> users;

	        if (username != null && password != null) {
	            users = userDAO.findByUserName(username, password);
	        } else {
	            users = new ArrayList<User>();
	        }

	        GsonBuilder builder = new GsonBuilder();
	        Gson gson = builder.create();
	        String json = gson.toJson(users);

	        response.setContentType("application/json");
	        response.setStatus(200);
	        response.getWriter().write(json);
	    }

	    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        if (username != null && password != null) {
	            User newUser = new User(username, password);
	            userDAO.createUser(newUser);
	            response.sendRedirect("login.jsp"); // Redirige vers la page de connexion après la création du compte
	        } else {
	            response.setStatus(400);
	            response.getWriter().write("Invalid username or password");
	        }
	    }

	    private void handleUserDeletion(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String deleteUsername = request.getParameter("deleteUsername");

	        if (deleteUsername != null) {
	            boolean deleted = userDAO.deleteUser(deleteUsername);
	            if (deleted) {
	                response.sendRedirect("login.jsp"); // Redirige vers la page de connexion après la suppression de l'utilisateur
	            } else {
	                response.setStatus(400);
	                response.getWriter().write("User deletion failed");
	            }
	        } else {
	            response.setStatus(400);
	            response.getWriter().write("Invalid username");
	        }
	    }

}

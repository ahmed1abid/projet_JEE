package org.projetjee.users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/user-management/*")
public class UserServlet extends HttpServlet {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/delete"))
			this.handleUserDeletion(request, response);
		else if (pathInfo.equals("/login"))
			this.handleLogin(request, response);
		else if (pathInfo.equals("/registration"))
			this.handleRegistration(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	private ArrayList<User> doGetUserByAccount(String username, String password) {
		ArrayList<User> users;
		try {
			users = userDAO.findByUserName(username, password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			users = new ArrayList<User>();
		}
		return users;
	}
	  private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        ArrayList<User> users;

	        if (username != null && password != null && username != "" && password != "") {
				users = this.doGetUserByAccount(username, password);
				if (users.size() == 1) {
					request.getSession().setAttribute("username", username);
					request.getSession().setAttribute("role", users.get(0).getRole().toString());
					request.getSession().setMaxInactiveInterval(3600);	// 1H (in seconds) session timeout
					response.setStatus(200);
					response.sendRedirect("/projet_JEE/vues/index.jsp");
				} else {
					response.setStatus(404);
					response.sendRedirect("/projet_JEE/vues/login.jsp");
				}
	        } else {
	        	response.setStatus(400);
	        	response.sendRedirect("/projet_JEE/vues/login.jsp");
	        }
	    }

	    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    	HttpSession session = request.getSession(false);
	    	if (session == null || session.getAttribute("role") != "admin") {
	    		response.setStatus(403);
	    		response.sendRedirect("/projet_JEE/vues/login.jsp");
	    		return;
	    	}
	        String username = request.getParameter("registerUsername");
	        String password = request.getParameter("registerPassword");
	        String role = request.getParameter("registerRole");

	        if (username != null && password != null && role != null) {
	            User newUser = new User(username, password, UserRole.valueOf(role));
	            try {
	            	if (!userDAO.CreateUser(newUser)) {
	            		response.setStatus(422);
	            		response.getWriter().write("This user name is already taken");
	            	}
	            	else {
	            		    response.setStatus(200);
	            		    String message = "Le compte a été créé avec succès !";
	            		    response.sendRedirect("/projet_JEE/vues/login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));	                
	            	}
	            } catch (NoSuchAlgorithmException e) {
	            	e.printStackTrace();
	            	response.setStatus(500);
	            }
	        } else {
	            response.setStatus(400);
	            response.getWriter().write("Invalid username or password");
	        }
	    }

	    private void handleUserDeletion(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	HttpSession session = request.getSession(false);
	    	if (session == null || session.getAttribute("role") != "admin") {
	    		response.setStatus(403);
	    		response.sendRedirect("/projet_JEE/vues/login.jsp");
	    		return;
	    	}
	        String deleteUsername = request.getParameter("deleteUsername");
	        String deletePassword = request.getParameter("deletePassword");

	        if (deleteUsername != null) {
	        	try {
	        		if (userDAO.DeleteUser(deleteUsername, deletePassword)) {
	            		   
	            		    String message = "Le compte a été supprimé avec succès !";
	            		    response.sendRedirect("/projet_JEE/vues/login.jsp?message=" + URLEncoder.encode(message, "UTF-8"));		         
	            		    } else {
		                response.setStatus(500);
		                response.getWriter().write("User deletion failed");
		            }
	        	} catch (NoSuchAlgorithmException e) {
	        		response.setStatus(500);
	                response.getWriter().write("User deletion failed");
	        	}
	            
	        } else {
	            response.setStatus(400);
	            response.getWriter().write("Invalid username");
	        }
	    }

}

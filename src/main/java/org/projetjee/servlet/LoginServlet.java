package org.projetjee.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserDAO userDAO = new UserDAOImpl();
        
        User user = userDAO.findByAccount(username, password);
        
        if (user != null) {
            // Les informations d'identification sont valides
            
            // Créer une session pour l'utilisateur connecté
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            
            // Rediriger vers la page d'accueil sécurisée (par exemple accueil.jsp)
            response.sendRedirect("index.jsp");
        } else {
            // Les informations d'identification sont invalides
            
            // Rediriger vers la page de connexion avec un message d'erreur
            response.sendRedirect("login.jsp?error=1");
        }
    }

}

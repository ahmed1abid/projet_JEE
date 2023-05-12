package org.projetjee.users;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

import org.projetjee.sites.Site;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/user-management")
public class UserController {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/")
	public String getUserByAccount(@QueryParam("username") String username,
								 @QueryParam("password") String password) {
		ArrayList<User> users;
		if (username != null && password != null) {
			users = userDAO.findByUserName(username, password);
		}
		else {
			users = new ArrayList<User>();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(users);
		return json;
	}

}

package org.projetjee.sessions;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/session-management")
public class SessionController {
	
	private SessionDAO sessionDAO = new SessionDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sessions/")
	public String getSessionByCode(@QueryParam("code") String code) {
		ArrayList<Session> sessions;
		if (code != "" && code != null) {
			sessions = sessionDAO.findByCode(code);
		} else {
			sessions = sessionDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(sessions);
		return json;
	}
	
	@POST
	@Path("/new/")
	public Response createSession(@FormParam("code") String code, @FormParam("date") Date date,
							@FormParam("start_time") Time start_time, @FormParam("end_time") Time end_time,
							@FormParam("discipline") String discipline, @FormParam("site") int site,
							@FormParam("description") String description, @FormParam("type") String type,
							@FormParam("category") String category) {
		
		Session session = new Session(code, date, start_time, end_time, discipline, site, description,
				SessionType.valueOf(type), SessionCategory.valueOf(category));
		if (!session.validCode()) {
			System.out.println("Session code must match '3 first letters of discipline + two-digits number'");
			return Response.status(422).build();
		} else {
			if (!sessionDAO.CreateSession(session)) {
				return Response.serverError().build();
			} return Response.ok().build();
		}
	}
	
	@POST
	@Path("/edit/")
	public Response editSession(@QueryParam("code") String code, @FormParam("newdate") Date newDate,
							@FormParam("newStart_time") Time newStart_time, @FormParam("newEnd_time") Time newEnd_time,
							@FormParam("newDiscipline") String newDiscipline, @FormParam("newSite") int newSite,
							@FormParam("newDescription") String newDescription, @FormParam("newType") String newType,
							@FormParam("newCategory") String newCategory) {
		Session session = new Session(code, newDate, newStart_time, newEnd_time, newDiscipline, newSite, newDescription,
				SessionType.valueOf(newType), SessionCategory.valueOf(newCategory));
		if (!sessionDAO.EditSession(code, session)) {
			return Response.serverError().build();
		} return Response.ok().build();		
	}
	
	@POST
	@Path("/delete/")
	public Response deleteSite(@QueryParam("code") String code) {
		if (!sessionDAO.DeleteSession(code)) {
			return Response.serverError().build();
		} return Response.ok().build();
	}

}

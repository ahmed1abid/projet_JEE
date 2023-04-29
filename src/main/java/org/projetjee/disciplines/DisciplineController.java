package org.projetjee.disciplines;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/discipline-management")
public class DisciplineController {
	
	private DisciplineDAO disciplineDAO = new DisciplineDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/disciplines/")
	public String getDisciplines(@QueryParam("name") String name) {
		ArrayList<Discipline> disciplines;
		if (name != "" && name != null) {
			disciplines = disciplineDAO.findByName(name);
		} else {
			disciplines = disciplineDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(disciplines);
		return json;
	}
	
	@POST
	@Path("/new/")
	public Response createDiscipline(@FormParam("name") String name,
						   @FormParam("flag") Integer flag) {
		Discipline discipline = new Discipline(name, flag!=0);
		if (!disciplineDAO.CreateDiscipline(discipline)) {
			return Response.serverError().build();
		} return Response.ok().build();
	}
	
	@POST
	@Path("/edit/")
	public Response setDisciplineFlag(@QueryParam("name") String name,
						   		  @FormParam("flag") Integer new_flag) {
		if (!disciplineDAO.EditDiscipline(name, new_flag!=0)) {
			return Response.serverError().build();
		} return Response.ok().build();
	}
	
	@POST
	@Path("/delete/")
	public Response deleteDiscipline(@QueryParam("name") String name) {
		if (!disciplineDAO.DeleteDiscipline(name)) {
			return Response.serverError().build();
		} return Response.ok().build();
	}

}

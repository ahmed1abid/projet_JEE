package org.projetjee.athletes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import org.glassfish.jersey.media.multipart.FormDataParam;          
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;

@Path("/athlete-management")
public class AthleteController {
	
	private AthleteDAO athleteDAO = new AthleteDAOImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/athletes/")
	public String getAthletes(@QueryParam("first_name") String firstName,
								 @QueryParam("last_name") String lastName) {
		ArrayList<Athlete> athletes;
		if (firstName != "" && firstName != null && lastName != "" && lastName != null) {
			athletes = athleteDAO.findByName(firstName, lastName);
		} else {
			athletes = athleteDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(athletes);
		return json;
	}
	
	@POST
	@Path("/upload/")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadCsvFile(@FormDataParam("file") InputStream fileInputStream) {
		try {
			byte[] bytes = fileInputStream.readAllBytes();
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();	// set separator used in the CSV file
			CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(byteArrayInputStream, StandardCharsets.ISO_8859_1))
					.withCSVParser(csvParser)
					.withSkipLines(1)	// skip first line of file because it contains headers, not actual data
					.build();
		
			List<String[]> rows = csvReader.readAll();
			boolean completed = athleteDAO.Import(rows);
			if (!completed) {
				return Response.status(500).build();
			}
		    return Response.ok().build();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			return Response.status(422).build();	// Can't process uploaded file
		} catch (NotEnoughColumnsException e) {
			e.printStackTrace();
			return Response.status(422, e.getMessage()).build();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			return Response.status(500).build();
		}
	}
	
	@POST
	@Path("/delete/")
	public Response deleteAthlete(@QueryParam("first_name") String firstName,
			@QueryParam("last_name") String lastName) {
		if(!athleteDAO.DeleteAthlete(firstName, lastName)) {
			return Response.serverError().build();
		} return Response.ok().build();
	}


}

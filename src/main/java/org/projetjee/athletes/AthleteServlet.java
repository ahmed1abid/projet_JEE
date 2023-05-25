package org.projetjee.athletes;

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

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/athlete-management")
public class AthleteServlet extends HttpServlet{
	
	private AthleteDAO athleteDAO = new AthleteDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/athletes")) {
			this.doGetAthletes(request, response);
		} else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/upload"))
			System.out.println("...");
			//this.handleUpload(request, response);
		else if (pathInfo.equals("/delete"))
			this.doDeleteAthlete(request, response);
		else {
			response.setStatus(404);
			response.getWriter().write("RESSOURCE NOT FOUND");
		}
	}
	
	private void doGetAthletes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		ArrayList<Athlete> athletes;
		if (firstName != "" && firstName != null && lastName != "" && lastName != null) {
			athletes = athleteDAO.findByName(firstName, lastName);
		} else {
			athletes = athleteDAO.findByAll();
		}
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String json = gson.toJson(athletes);
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.getWriter().write(json);
	}
	
	
	//private void handleUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	//@FormDataParam("file") InputStream fileInputStream = request.getParameter("file");
	//	if (ServletFileUpload.isMultipartContent(request)) {
	//		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
	//		List<FileItem> items = upload.parseRequest(request);
	//		for (FileItem item : items) {
	//			if (!item.isFormField()) {
	//				InputStream fileContent = item.getInputStream();
	//				this.doUploadCsvFile(request, response, fileContent);
	//			}
	//		}
	//	}
	//}
	
	private void doUploadCsvFile(HttpServletRequest request, HttpServletResponse response, InputStream fileInputStream) throws ServletException, IOException {
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
				response.setStatus(500);
				response.getWriter().write("INTERNAL SERVER ERROR");
			} else
				response.setStatus(200);
		} catch (IOException | CsvException e) {
			e.printStackTrace();
			response.setStatus(422);	// Can't process uploaded file
		} catch (NotEnoughColumnsException e) {
			e.printStackTrace();
			response.setStatus(422);
			response.getWriter().write(e.getMessage());
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}
		
	private void doDeleteAthlete(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		if(!athleteDAO.DeleteAthlete(firstName, lastName)) {
			response.setStatus(500);
		} else 
			response.setStatus(200);
	}


}

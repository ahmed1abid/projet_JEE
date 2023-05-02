package org.projetjee.sessions;

import java.sql.Date;
import java.sql.Time;
import java.util.regex.*;

public class Session {
	
	private String codeRegexBase = "^%c%c%c[0-9][0-9]$";
	
	private String code;
	private Date date;
	private Time start_time;
	private Time end_time;
	private String discipline;
	private int site_id;
	private String site_name;
	private String site_city;
	private String description;
	private SessionType type;
	private SessionCategory category;
	
	/**
	 * Create a Session object with informations retrieved from the database */
	public Session(String code, Date date, Time start_time, Time end_time, String discipline, String site_name,
			String site_city, String description, SessionType type, SessionCategory category) {
		this.code = code;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.discipline = discipline;
		this.site_name = site_name;
		this.site_city = site_city;
		this.description = description;
		this.type = type;
		this.category = category;
	}
	
	/**
	 * Create a Session object to be saved in the database */
	public Session(String code, Date date, Time start_time, Time end_time, String discipline, int site_id,
			String description, SessionType type, SessionCategory category) {
		this.code = code;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.discipline = discipline;
		this.site_id = site_id;
		this.description = description;
		this.type = type;
		this.category = category;
	}
	
	public boolean validCode() {
		String discipline = this.discipline.toUpperCase();
		String codeRegex = String.format(codeRegexBase, discipline.charAt(0), discipline.charAt(1), discipline.charAt(2));
		Pattern p = Pattern.compile(codeRegex);
		Matcher m = p.matcher(code);
		return m.find();
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSite_city() {
		return site_city;
	}

	public void setSite_city(String site_city) {
		this.site_city = site_city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type.name();
	}
	
	public void setType(String type) {
		this.type = SessionType.valueOf(type);
	}
	
	public String getCategory() {
		return this.category.name();
	}
	
	public void setCategory(String category) {
		this.category = SessionCategory.valueOf(category);
	}

	public int getSite_id() {
		return site_id;
	}

	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}

}

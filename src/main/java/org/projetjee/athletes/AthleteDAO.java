package org.projetjee.athletes;

import java.util.ArrayList;

public interface AthleteDAO {
	
public ArrayList<Athlete> findByAll();
	
	public ArrayList<Athlete> findByName(String firstName, String lastName);
	
	public void ImportFromCSV();
	
	public void EditDiscipline(String firstName, String lastName, Athlete athlete);
	
	public void DeleteAthlete(String firstName, String lastName);

}

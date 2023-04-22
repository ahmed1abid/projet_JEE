package org.projetjee.athletes;

import java.util.ArrayList;
import java.util.List;

public interface AthleteDAO {
	
public ArrayList<Athlete> findByAll();
	
	public ArrayList<Athlete> findByName(String firstName, String lastName);
	
	public boolean Import(List<String[]> rows) throws NotEnoughColumnsException;
	
	public boolean DeleteAthlete(String firstName, String lastName);

}

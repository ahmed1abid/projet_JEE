package org.projetjee.disciplines;

import java.util.ArrayList;

public interface DisciplineDAO {
	
	public ArrayList<Discipline> findByAll();
	
	public ArrayList<Discipline> findByName(String searchText);
	
	public boolean CreateDiscipline(Discipline new_discipline);
	
	public boolean EditDiscipline(String name, Boolean new_flag);
	
	public boolean DeleteDiscipline(String name);

}

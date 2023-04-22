package org.projetjee.disciplines;

import java.util.ArrayList;

public interface DisciplineDAO {
	
	public ArrayList<Discipline> findByAll();
	
	public ArrayList<Discipline> findByName(String searchText);
	
	public void CreateDiscipline(Discipline new_discipline);
	
	public void EditDiscipline(String name, Boolean new_flag);
	
	public void DeleteDiscipline(String name);

}

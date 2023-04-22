package org.projetjee.sessions;

import java.util.ArrayList;


public interface SessionDAO {
	
public ArrayList<Session> findByAll();
	
	public ArrayList<Session> findByCode(String searchText);
	
	public void CreateSession(Session new_session);
	
	public void EditSession(String code, Session session);
	
	public void DeleteSession(String code);

}

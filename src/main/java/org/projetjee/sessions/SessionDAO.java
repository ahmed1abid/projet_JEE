package org.projetjee.sessions;

import java.util.ArrayList;


public interface SessionDAO {
	
public ArrayList<Session> findByAll();
	
	public ArrayList<Session> findByCode(String searchText);
	
	public boolean CreateSession(Session new_session);
	
	public boolean EditSession(String code, Session session);
	
	public boolean DeleteSession(String code);

}

package org.projetjee.sessions;

import java.util.ArrayList;


public interface SessionDAO {
	
public ArrayList<Session> findByAll();
	
	public ArrayList<Session> findByCode(String searchText);
	
	public boolean CreateSession(Session new_session) throws TimeOverlapException;
	
	public boolean EditSession(String code, Session session) throws TimeOverlapException;
	
	public boolean DeleteSession(String code);

}

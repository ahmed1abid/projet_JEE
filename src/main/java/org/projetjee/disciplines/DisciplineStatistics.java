package org.projetjee.disciplines;

public class DisciplineStatistics {
	
	private String name;
	private int nbAtheletes;
	private int nbSessions;
	private int averageAge;
	
	public DisciplineStatistics(String name) {
		this.name = name;
		this.nbAtheletes = 0;
		this.nbSessions = 0;
		this.averageAge = 0;
	}
	
	public DisciplineStatistics(String name, int nbAtheletes, int nbSessions, int averageAge) {
		this.name = name;
		this.nbAtheletes = nbAtheletes;
		this.nbSessions = nbSessions;
		this.averageAge = averageAge;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbAtheletes() {
		return nbAtheletes;
	}

	public void setNbAtheletes(int nbAtheletes) {
		this.nbAtheletes = nbAtheletes;
	}

	public int getNbSessions() {
		return nbSessions;
	}

	public void setNbSessions(int nbSessions) {
		this.nbSessions = nbSessions;
	}

	public int getAverageAge() {
		return averageAge;
	}

	public void setAverageAge(int averageAge) {
		this.averageAge = averageAge;
	}
	
	

}

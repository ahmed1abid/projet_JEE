package org.projetjee.disciplines;

public class Discipline {
	
	private String name;
	private Boolean flag;
	
	public Discipline(String name, Boolean flag) {
		this.name = name;
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	

}

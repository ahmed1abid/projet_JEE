package org.projetjee.sites;

public class Site {
	
	private int id;
	private String name;
	private String city;
	private SiteCategory category;
	
	public Site(String name, String city, SiteCategory category) {
		this.name = name;
		this.city = city;
		this.category = category;
	}

	public Site(int id, String name, String city, SiteCategory category) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.category = category;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category.name();
	}

	public void setCategory(String category) {
		this.category = SiteCategory.valueOf(category);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
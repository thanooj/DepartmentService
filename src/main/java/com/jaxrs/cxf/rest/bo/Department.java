package com.jaxrs.cxf.rest.bo;

import org.springframework.stereotype.Component;

@Component
public class Department {

	private Integer id;
	private String name;
	private String location;
	private Integer eIds;

	public Department() {
	}

	public Department(Integer id, String name, String location, Integer eIds) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.eIds = eIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer geteIds() {
		return eIds;
	}

	public void seteIds(Integer eIds) {
		this.eIds = eIds;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", location=" + location + ", eIds=" + eIds + "]";
	}

}

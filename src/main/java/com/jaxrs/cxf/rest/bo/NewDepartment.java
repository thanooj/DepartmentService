package com.jaxrs.cxf.rest.bo;

import org.springframework.stereotype.Component;

@Component("newDepartment")
public class NewDepartment {

	private String name;
	private String location;

	public NewDepartment() {
	}

	public NewDepartment(String name, String location) {
		this.name = name;
		this.location = location;

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

	@Override
	public String toString() {
		return "newDepartment [name=" + name + ", location=" + location + "]";
	}

}

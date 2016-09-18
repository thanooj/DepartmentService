package com.jaxrs.cxf.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.jaxrs.cxf.rest.bo.Department;
import com.jaxrs.cxf.rest.bo.Greeting;
import com.jaxrs.cxf.rest.bo.NewDepartment;

@Path("/department")
public interface DepartmentService {
	
	@GET
	@Path("/greeting/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting greeting(@PathParam("name") String name);
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON})
	@Path("/getDepartmentByID/{id}")
	public Response getDepartmentByID(@PathParam("id") Integer id);
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON/*, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML */})
	@Path("/getDepartments")
	public Response getDepartments();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createDepartment")
	public Response createDepartment(final List<NewDepartment> newDepartment);
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateDepartment")
	public Response updateDepartment(final Department customer);
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteDepartmentByID/{id}")
	public Response deleteDepartmentByID(@PathParam("id") Integer id);
	
}

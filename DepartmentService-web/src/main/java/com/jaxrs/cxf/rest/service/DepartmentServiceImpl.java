package com.jaxrs.cxf.rest.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaxrs.cxf.rest.bo.Department;
import com.jaxrs.cxf.rest.bo.Greeting;
import com.jaxrs.cxf.rest.bo.NewDepartment;
import com.jaxrs.cxf.rest.dao.DepartmentDAOImpl;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private DepartmentDAOImpl departmentDAO;

	@Override
	public Greeting greeting(String name) {
		logger.info("GreetingController.greeting()");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@Override
	public Response getDepartmentByID(Integer id) {
		Response response = null;
		logger.info("DepartmentService.getDepartmentByID() : " + id);
		Department department = departmentDAO.getDepartmentByID(id);
		if (department != null) {
			response = Response.status(Status.OK).entity(department).build();
		} else {
			response = Response.status(Status.NOT_FOUND).entity(null).build();
		}
		return response;
	}

	@Override
	public Response getDepartments() {
		Response response = null;
		logger.info("DepartmentService.getDepartments()");
		List<Department> departments = departmentDAO.getDepartments();
		if (departments != null && !departments.isEmpty()) {
			response = Response.status(Status.OK).entity(departments).build();
		} else {
			response = Response.status(Status.NOT_FOUND).entity(null).build();
		}
		return response;
	}

	@Override
	public Response createDepartment(final List<NewDepartment> newDepartment) {
		Response response = null;
		String msg = "BAD REQUEST!";
		if (newDepartment != null && !newDepartment.isEmpty()) {
			logger.info("newDepartment != null && newDepartment.isEmpty() == false  :: "
					+ (newDepartment != null && newDepartment.isEmpty() == false));
			int[] createDepartment = departmentDAO.createDepartment(newDepartment);
			if (createDepartment.length == newDepartment.size()) {
				msg = "All new departments are successfully created.";
				response = Response.status(Status.CREATED).entity(msg).build();
			} else if (createDepartment.length < newDepartment.size()) {
				msg = "NOT all new departments are successfully created.";
				response = Response.status(Status.CREATED).entity(msg).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).entity(msg).build();
			}
		} else {
			logger.info("else");
			response = Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		return response;
	}

	@Override
	public Response updateDepartment(Department department) {
		Response response = null;
		String msg = "BAD REQUEST";
		if (department != null && department.getId() != null) {
			logger.info("newDepartment != null");
			int updateDepartment = departmentDAO.updateDepartment(department);
			if (updateDepartment > 0) {
				msg = "A department has been updated/created successfully.";
				response = Response.status(Status.CREATED).entity(msg).build();
			} else {
				response = Response.status(Status.BAD_REQUEST).entity(msg).build();
			}
		} else {
			logger.info("else");
			response = Response.status(Status.BAD_REQUEST).entity(msg).build();
		}
		return response;
	}

	@Override
	public Response deleteDepartmentByID(Integer id) {
		Response response = null;
		String msg = "BAD REQUEST";
		logger.info("DepartmentService.deleteDepartmentByID() : " + id);
		int deleteDepartmentByID = departmentDAO.deleteDepartmentByID(id);
		if (deleteDepartmentByID > 0) {
			msg = "A department has been deleted successfully.";
			response = Response.status(Status.OK).entity(msg).build();
		} else {
			response = Response.status(Status.NOT_FOUND).entity(msg).build();
		}
		return response;
	}
}

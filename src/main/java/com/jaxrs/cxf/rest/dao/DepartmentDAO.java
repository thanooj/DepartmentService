package com.jaxrs.cxf.rest.dao;

import java.util.List;

import com.jaxrs.cxf.rest.bo.Department;
import com.jaxrs.cxf.rest.bo.NewDepartment;

public interface DepartmentDAO {

	public Department getDepartmentByID(Integer id);

	public List<Department> getDepartments();

	public int[] createDepartment(final List<NewDepartment> newCustomer);

	public int updateDepartment(final Department customer);

	public int deleteDepartmentByID(Integer id);

}

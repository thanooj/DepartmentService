package com.jaxrs.cxf.rest.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.jaxrs.cxf.rest.bo.Department;
import com.jaxrs.cxf.rest.bo.DepartmentMapper;
import com.jaxrs.cxf.rest.bo.NewDepartment;

@Repository("departmentDAO")
public class DepartmentDAOImpl extends JdbcDaoSupport implements DepartmentDAO {

	private static final Logger logger = LoggerFactory.getLogger(DepartmentDAOImpl.class);

	@Autowired
	private DataSource departmentDataSource;

	@PostConstruct
	private void initialize() {
		logger.info("*START****DepartmentDAOImpl.initialize()*******");
		setDataSource(departmentDataSource);
		logger.info("*END****DepartmentDAOImpl.initialize()*******");
	}

	@Override
	public Department getDepartmentByID(Integer id) {
		Department department = null;
		String SQL = "select * from employee_department.department where id = ?";
		List<Department> departments = getJdbcTemplate().query(SQL, new Object[] { id },
				new BeanPropertyRowMapper<Department>(Department.class));
		if (departments != null && (!departments.isEmpty() && departments.size() == 1)) {
			department = departments.get(0);
		}
		return department;
	}

	@Override
	public List<Department> getDepartments() {
		String SQL = "select * from employee_department.department";
		return getJdbcTemplate().query(SQL, new DepartmentMapper());
	}

	@Override
	public int[] createDepartment(final List<NewDepartment> newDepartments) {
		String SQL = "insert into employee_department.department (name, location) values (?, ?)";
		return getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				NewDepartment newDepartment = newDepartments.get(i);
				ps.setString(1, newDepartment.getName());
				ps.setString(2, newDepartment.getLocation());
			}

			@Override
			public int getBatchSize() {
				return newDepartments.size();
			}
		});
	}

	@Override
	public int updateDepartment(Department department) {
		Department departmentByID = getDepartmentByID(department.getId());
		if (departmentByID != null) {
			String SQL = "update employee_department.department set name = ?, location = ? where id = ?";
			return getJdbcTemplate().update(SQL,
					new Object[] { department.getName(), department.getLocation(), department.getId() });
		} else {
			String SQL = "insert into employee_department.department  (name, location) values (?, ?)";
			return getJdbcTemplate().update(SQL, new Object[] { department.getName(), department.getLocation() });
		}
	}

	@Override
	public int deleteDepartmentByID(Integer id) {
		String SQL = "delete from employee_department.department where id = ?";
		return getJdbcTemplate().update(SQL, new Object[] { id });
	}

}

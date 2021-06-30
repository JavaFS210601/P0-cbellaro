package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDaoInterface {

	public List<Employee> getEmployees();
	
	public void addEmployee(Employee emp);
	
	public void changeJob(int employee_id, int job_id);
	
	public void fireEmployee(int employee_id);
}
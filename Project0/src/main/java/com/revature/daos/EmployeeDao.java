package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

public class EmployeeDao implements EmployeeDaoInterface {

	
	@Override
	public List<Employee> getEmployees() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet results = null;
			
			String postgres = "SELECT * FROM employees;";
			
			Statement stmt = conn.createStatement(); 
			
			results = stmt.executeQuery(postgres);
			
			List<Employee> employeeList = new ArrayList<>();
			
			while(results.next()) {
				
				Employee employee = new Employee(
						results.getInt("employee_id"),
						results.getString("first_name"),
						results.getString("last_name"),
						results.getInt("job_id")
					);
				
				employeeList.add(employee);
			}
			
			return employeeList;
			
		} catch (SQLException e) {
			System.out.println("Couldn't access database, please try again");
			e.printStackTrace();
			
		}
		
		return null;
	}

	@Override
	public void addEmployee(Employee emp) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String postgres = "INSERT INTO employees (first_name, last_name, job_id)"
					+ "VALUES (?, ?, ?);"; 
					
			PreparedStatement preppedStmnt = conn.prepareStatement(postgres);
			
			preppedStmnt.setString(1, emp.getFirst_name());
			preppedStmnt.setString(2, emp.getLast_name());
			preppedStmnt.setInt(3, emp.getJob_id());	
			preppedStmnt.executeUpdate();
			
			System.out.println(emp.getFirst_name() + " " + emp.getLast_name() + " has been added to the system. Congrats and welcome!");
			
		} catch(SQLException e) {
			System.out.println("Unable to add employee, please try again.");
			e.printStackTrace();
		}
	
	}

	@Override
	public void changeJob(int employee_id, int job_id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String postgres = "UPDATE employees SET job_id = ? WHERE employee_id = ?;";
			
			PreparedStatement preppedStmnt = conn.prepareStatement(postgres);

			preppedStmnt.setInt(1, job_id);
			preppedStmnt.setInt(2,  employee_id);
			preppedStmnt.executeUpdate();
			
			System.out.println("Employee's job has been changed to: " + job_id);
			
		} catch (SQLException e) {
			System.out.println("Couldn't change job, please try again.");
			e.printStackTrace();
		}
		
	}

	@Override
	public void fireEmployee(int employee_id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String postgres = "DELETE FROM employees WHERE employee_id = ?;";
			
			PreparedStatement preppedStmnt = conn.prepareStatement(postgres);
			
			preppedStmnt.setInt(1, employee_id);	
			preppedStmnt.executeUpdate();
			
			System.out.println("You're fired! We promise Michael isn't kidding this time!");
			
		} catch (SQLException e) {
			System.out.println("Unable to delete employee, please try again.");
			e.printStackTrace();
		}
		
	}

}
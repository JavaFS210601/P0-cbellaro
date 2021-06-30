package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.EmployeeDao;
import com.revature.daos.JobDao;

public class Menu {

	EmployeeDao empDao = new EmployeeDao();
	JobDao jobDao = new JobDao();

	public void display() {
		
		boolean displayMenu = true;
		
		Scanner scan = new Scanner(System.in);
		final Logger logs = LogManager.getLogger(Menu.class);
		
		System.out.println("================================================================================");
		System.out.println("       Welcome to Dunder Mifflin Scranton's HR Employee Management System");
		System.out.println("================================================================================");
		System.out.println("\r\n");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n"
				+ "@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n"
				+ "@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n"
				+ "@&&&&       /@&&&    ,&    #&/   &&&   .&&       *&&&&        &% ..,,,,%&&&&&&&&\r\n"
				+ "@&&&&    .     &&    ,&    #&/    &&    &&    .     &&        &%    .    ,&&&&&&\r\n"
				+ "@&&&@    &&    &&    ,&    #&/     &    &&    &&    &&     &&&&%    &&    &&&&&&\r\n"
				+ "@&&&@    &&    &&    ,&    #&/          &&    &&    &&       ,&%         @&&&&&&\r\n"
				+ "@&&&@    &&    &&    ,&    #&/   ,      &&    &&    &&     %%%&%    @%    &&&&&&\r\n"
				+ "@&&&@    &&    &&    ,&    #&/   @      &&    &&    &&     &&&&%    &%    &&&&&&\r\n"
				+ "@&&&&          &&     *    %&/   &&     &&          &&        &%    &%    &&&&&&\r\n"
				+ "@&&&&////***#&&&&&@*     %&&&(***&&&****&&////***(&&&&((((((((&&****&&****&&&&&&\r\n"
				+ "@&&      &&.     &&     &&       ,&       ,&    @&&&,    &&    &&/   @&&&&&&&&&&\r\n"
				+ "@&&      #&      &&     &&       *@       ,&    @&&&,    &&     &/   &&&&&&&&V&&\r\n"
				+ "@&&       %      &&     &&    &&&&&    %&&&&    @&&&,    &&     ./   @&&&&&&&&&&\r\n"
				+ "@&&    .     .   &&     &&       &&       &&    @&&&,    &&          @&&&&&&&&&&\r\n"
				+ "@&&    @    ,.   &&     &&    &&&&&    &&&%&    @&&&,    &&   .      @&&&&&&&&&&\r\n"
				+ "@&&    &    &.   &&     &&    &&&&&    %&&&&    @&&&,    &&    @     &&&&&&&&&&&\r\n"
				+ "@&&    &(   &.   &&     &&    &&&&&    %&&&&       &,    &&    &&    &&&&&&&&&&&\r\n"
				+ "@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\r\n"
				+ "@&%&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&%&&&&&&&&&&&&&\r\n"
				+ "@&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println("\r\n");
		while(displayMenu) {
			
			System.out.println("-----------------------------------------------------");
			System.out.println("            How can we help you today?");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			System.out.println("1) List Employees -> Show all current employees."); //read
			System.out.println("2) List Positions - > Show all job titles and descriptions.");
			System.out.println("3) Add New Hire -> Add a new hire."); //create
			System.out.println("4) Update Employee's Job -> Update an employee's position."); //update
			System.out.println("5) Fire Employee -> Fire an employee."); //delete
			System.out.println("6) Exit -> Exit the HR Employee Management System.");
			
			int input = scan.nextInt();
			scan.nextLine();
			
			switch(input) {
				case 1: {
					System.out.println("Loading employees of Dunder Mifflin Scranton.....");
					
					List<Employee> employees = empDao.getEmployees();
					
					for(Employee e : employees) {
						System.out.println(e.getEmployee_id() + ") " + e.getFirst_name() + " " + e.getLast_name() + ", job_id: " + e.getJob_id());
					}		
					break;
				}
				case 2: {
					System.out.println("Loading job titles and positions.....");
					
					List<Job> jobs = jobDao.getJobs();
					
					for(Job j : jobs) {
						System.out.println(j.getJob_id() + ") " + j.getJob_title()+ " - " + j.getJob_description());
					}		
					break;
				}
				case 3: {
					logs.warn("User attempting to add a new employee to the database.");
					System.out.println("We see you're trying to register a new employee.");
					
					System.out.println("Please enter the new employee's first name");
					String f_name = scan.nextLine();
					
					System.out.println("Please enter the new employee's last name");
					String l_name = scan.nextLine();
					
					System.out.println("Please enter new employee's job_id: 1) Regional Manager, 2) Assistant to the Regional Manager, 3) Sales, 4) Accounting, \r\n 5) Quality Assurance, 6) Supplier Relations, 7) HR, 8) Customer Service, 9) Reception, 10) Temp");
					int job_id = scan.nextInt();
					scan.nextLine();
				
					Employee newEmployee = new Employee(f_name, l_name, job_id);
									
					empDao.addEmployee(newEmployee);
					
					break;	
				}
				case 4: {
					logs.warn("User attempting to update an employee's position in the database.");
					System.out.println("We see you're trying change an employee's position.");
					System.out.println("Here are our current employees...");
					
					List<Employee> employees = empDao.getEmployees();
					
					for(Employee e : employees) {
						System.out.println(e.getEmployee_id() + ") " + e.getFirst_name() + " " + e.getLast_name() + ", job_id: " + e.getJob_id());
					}
					
					System.out.println("Please enter the employee_id for the employee you would like to update.");
					int employee_id_Input = scan.nextInt();
					scan.nextLine();
					
					System.out.println("Please enter the employee's new job_id: 1) Regional Manager, 2) Assistant to the Regional Manager, 3) Sales, 4) Accounting, 5) Quality Assurance, 6) Supplier Relations, 7) HR, 8) Customer Service, 9) Reception, 10) Temp");
					int job_id_Input = scan.nextInt();
					scan.nextLine();
					
					empDao.changeJob(employee_id_Input, job_id_Input);
					
					break;	
				}
				case 5: {
					logs.warn("User attempting to fire an employee and remove them from the database.");
					System.out.println("We see you're trying fire an employee. We're surprised Toby allowed you to do this.");
					System.out.println("Here are our current employees...");
					
					List<Employee> employees = empDao.getEmployees();
					
					for(Employee e : employees) {
						System.out.println(e.getEmployee_id() + ") " + e.getFirst_name() + " " + e.getLast_name() + ", job_id: " + e.getJob_id());
					}
					
					System.out.println("Please enter the employee_id of the employee you would like to fire.");
					
					int employee_id_Input = scan.nextInt();
					scan.nextLine();
					
					empDao.fireEmployee(employee_id_Input);
					
					break;
				}
				case 6: {
					System.out.println("Goodbye!");
					displayMenu = false;
					break;
				}

			}

		}
		
	}
	
}

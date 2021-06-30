package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Job;
import com.revature.utils.ConnectionUtil;

public class JobDao implements JobDaoInterface {

	@Override
	public List<Job> getJobs() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet results = null;
			
			String postgres = "SELECT * FROM jobs;";
			
			Statement stmt = conn.createStatement(); 
			
			results = stmt.executeQuery(postgres);
			
			List<Job> jobList = new ArrayList<>();
			
			while(results.next()) {
				
				Job job = new Job(
						results.getInt("job_id"),
						results.getString("job_title"),
						results.getString("job_description")
					);
				
				jobList.add(job);
			}
			
			return jobList;
			
		} catch (SQLException e) {
			System.out.println("Couldn't access database, please try again");
			e.printStackTrace();
			
		}
		
		return null;
	}
}
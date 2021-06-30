package com.revature.models;

public class Job {

	public int job_id;
	private String job_title;
	private String job_description;
	
	public Job() {
		super();
	}

	public Job(int job_id, String job_title, String job_description) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
		this.job_description = job_description;
	}

	public Job(String job_title, String job_description) {
		super();
		this.job_title = job_title;
		this.job_description = job_description;
	}

	@Override
	public String toString() {
		return "Job [job_id=" + job_id + ", job_title=" + job_title + ", job_description=" + job_description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job_description == null) ? 0 : job_description.hashCode());
		result = prime * result + job_id;
		result = prime * result + ((job_title == null) ? 0 : job_title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (job_description == null) {
			if (other.job_description != null)
				return false;
		} else if (!job_description.equals(other.job_description))
			return false;
		if (job_id != other.job_id)
			return false;
		if (job_title == null) {
			if (other.job_title != null)
				return false;
		} else if (!job_title.equals(other.job_title))
			return false;
		return true;
	}

	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	
}

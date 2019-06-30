package com.coll.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
@SequenceGenerator(name="jobid_seq",sequenceName="jobidseq")

public class Job 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jobid_seq")
	
	int jobId;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getJobDesignation() {
		return jobDesignation;
	}
	public void setJobDesignation(String jobDesignation) {
		this.jobDesignation = jobDesignation;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getCTC() {
		return CTC;
	}
	public void setCTC(int cTC) {
		CTC = cTC;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getLastDatetoApply() {
		return lastDatetoApply;
	}
	public void setLastDatetoApply(Date lastDatetoApply) {
		this.lastDatetoApply = lastDatetoApply;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	String jobDesignation;
	String companyName;
	int CTC;
	String location;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	Date lastDatetoApply;
	String skills;

}

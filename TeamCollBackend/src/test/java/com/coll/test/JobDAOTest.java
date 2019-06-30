package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

public class JobDAOTest 
{
	static JobDAO jobDAO;

	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		jobDAO=(JobDAO)context.getBean("jobDAO");
	}
	
	@Ignore
	@Test
	public void publishJobTest()
	{
		Job job=new Job();
		job.setJobDesignation("Senior Developer");
		job.setLocation("Hyderabad");
		job.setCompanyName("Infosys");
		job.setSkills("Backend Developer");
		job.setCTC(1200000);
		job.setLastDatetoApply(new java.util.Date(2019,06,10));
		
		assertTrue("Problem in Publishing job",jobDAO.publishJob(job));
	}
	
	@Test
	public void showJobsTest()
	{
		List<Job> jobList=jobDAO.showJobs();
		
		assertTrue("Problem in displaying JobList",jobList.size()>0);
		
		for(Job job:jobList)
		{
			System.out.print(job.getJobId()+":::");
			System.out.print(job.getJobDesignation()+":::");
			System.out.print(job.getCompanyName()+":::");
			System.out.println(job.getCTC());
		}
	}
	
	@Ignore
	@Test
	public void deleteJobTest()
	{
		Job job=jobDAO.getJob(953);
		
		assertTrue("Problem in Deleting job",jobDAO.deleteJob(job));
	}
	

}

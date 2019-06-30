package com.coll.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.JobDAO;
import com.coll.model.Job;

@RestController
public class JobRestController 
{
	@Autowired
	JobDAO jobDAO;
	
	@PostMapping(value="/publishJob",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addJob(@RequestBody Job job)
	{
		if(jobDAO.publishJob(job))
		{
			return new ResponseEntity<String>("Job Published", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/showJobs")
	public ResponseEntity<List<Job>> showJob()
	{
		List<Job> listJobs=jobDAO.showJobs();
		
		if(listJobs.size()>0)
		{
			return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Job>>(listJobs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable("jobId")int jobId)
	{
		Job job=jobDAO.getJob(jobId);
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	@GetMapping(value="/deleteJob/{jobId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteJob(@PathVariable("jobId")int jobId)
	{
		Job job=jobDAO.getJob(jobId);
		
		if(jobDAO.deleteJob(job))
		{
			return new ResponseEntity<String>("Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

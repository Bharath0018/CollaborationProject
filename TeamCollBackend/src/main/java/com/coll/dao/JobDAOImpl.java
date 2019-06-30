package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.Job;

@Repository("jobDAO")
@Transactional

public class JobDAOImpl implements JobDAO 
{
	@Autowired 
	SessionFactory sessionFactory;

	@Override
	public boolean publishJob(Job job) 
	{
		try 
		{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) 
	{
		try 
		{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public List<Job> showJobs() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		List<Job> listJobs=query.list();
		session.close();
		return listJobs;
	}

	@Override
	public Job getJob(int jobId) 
	{
		Session session=sessionFactory.openSession();
		Job job=session.get(Job.class, jobId);
		session.close();
		return job;
	}

}

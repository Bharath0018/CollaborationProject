package com.coll.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coll.model.BlogComment;

@Repository("blogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO 
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addComment(BlogComment comment) 
	{
		try
		{
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean deleteComment(BlogComment comment) 
	{
		try
		{
			sessionFactory.getCurrentSession().delete(comment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public BlogComment getBlogComment(int commentId) 
	{
		Session session=sessionFactory.openSession();
		BlogComment comment=session.get(BlogComment.class, commentId);
		session.close();
		return comment;
	}

	@Override
	public List<BlogComment> listBlogComment(int blogId) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from BlogComment where blogId=:blogid");
		query.setParameter("blogid", blogId);
		List<BlogComment> listBlogComment=query.list();
		session.close();
		return listBlogComment;
	}

	@Override
	public boolean updateComment(BlogComment comment) 
	{
		try
		{
			sessionFactory.getCurrentSession().update(comment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}

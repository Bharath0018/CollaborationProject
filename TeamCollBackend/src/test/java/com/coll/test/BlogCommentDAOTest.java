package com.coll.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogCommentDAO;
import com.coll.model.Blog;
import com.coll.model.BlogComment;

public class BlogCommentDAOTest 
{
	static BlogCommentDAO blogCommentDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
	}
	
	@Ignore
	@Test
	public void addBlogCommentTest()
	{
		BlogComment comment=new BlogComment();
		comment.setBlogId(954);
		comment.setCommentText("Very Helpful. Keep up the Good Work");
		comment.setUsername("varun");
		comment.setCommentDate(new Date());
		
		assertTrue("Problem in Adding Comment",blogCommentDAO.addComment(comment));
	}
	
	@Ignore
	@Test
	public void deleteBlogCommentTest()
	{
		BlogComment comment=blogCommentDAO.getBlogComment(952);
		assertTrue("Problem in Deleting Comment",blogCommentDAO.deleteComment(comment));
	}
	
	@Ignore
	@Test
	public void updateBlogCommentTest()
	{
		BlogComment comment=blogCommentDAO.getBlogComment(953);
		comment.setCommentText("Sensible Blog and very informative");
		assertTrue("Problem in Updating Comment",blogCommentDAO.updateComment(comment));
	}
	
	@Test
	public void listBlogCommentTest()
	{
		List<BlogComment> listBlogComments=blogCommentDAO.listBlogComment(952);
		
		assertTrue("Problem in Listing Blogs",listBlogComments.size()>0);
		
		for(BlogComment blog:listBlogComments)
		{
			System.out.print(blog.getBlogId()+":::");
			System.out.print(blog.getCommentId()+":::");
			System.out.print(blog.getCommentText()+":::");
			System.out.print(blog.getUsername()+":::");
			System.out.print(blog.getCommentDate()+":::");
			
		}
	}
}

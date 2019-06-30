package com.coll.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

public class BlogDAOTest 
{
	static BlogDAO blogDAO;

	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}
	
	@Ignore
	@Test
	public void addBlogTest()
	{
		Blog blog=new Blog();
		blog.setBlogName("JavaScript");
		blog.setBlogContent("This is a tutorial for learning basic concepts of JavaScript");
		blog.setUsername("varun");
		blog.setCreateDate(new Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setStatus("NA");
		
		assertTrue("Problem in Adding the Blog",blogDAO.addBlog(blog));
	}
	
	@Ignore
	@Test
	public void deleteBlogTest()
	{
		Blog blog=blogDAO.getBlog(953);
		assertTrue("Problem in Deleting the Blog",blogDAO.deleteBlog(blog));
	}
	
	@Ignore
	@Test
	public void updateBlogTest()
	{
		Blog blog=blogDAO.getBlog(954);
		blog.setBlogContent("This is a tutorial for learning basic concepts of JavaScript and its Datatypes");
		assertTrue("Problem in updating the Blog",blogDAO.updateBlog(blog));
	}
	
	@Test
	public void listBlogTest()
	{
		List<Blog> blogList=blogDAO.listBlogs();
		
		assertTrue("Problem in Listing Blogs",blogList.size()>0);
		
		for(Blog blog:blogList)
		{
			System.out.print(blog.getBlogid()+":::");
			System.out.print(blog.getBlogName()+":::");
			System.out.print(blog.getBlogContent()+":::");
			System.out.print(blog.getStatus()+":::");
			System.out.print(blog.getLikes()+":::");
			System.out.println(blog.getDislikes());
		}
	}
	
	@Ignore
	@Test
	public void approveBlogTest()
	{
		Blog blog=blogDAO.getBlog(952);
		assertTrue("Problem in Approving Blog",blogDAO.approveBlog(blog));
		
	}
	
	@Ignore
	@Test
	public void rejectBlogTest()
	{
		Blog blog=blogDAO.getBlog(954);
		assertTrue("Problem in Rejecting Blog",blogDAO.rejectBlog(blog));
	}
	
	@Ignore
	@Test
	public void incrementLikes()
	{
		assertTrue("Problem in incrementing Likes",blogDAO.incrementLikes(952));
	}
	
	@Ignore
	@Test
	public void incrementDislikes()
	{
		assertTrue("Problem in incrementing DisLikes",blogDAO.incrementDisLikes(954));
	}
}

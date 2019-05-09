package com.coll.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
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
	
	@Test
	public void addBlogTest()
	{
		Blog blog=new Blog();
		blog.setBlogName("The Java Source");
		blog.setBlogContent("This is a tutorial for learning basic java Concepts");
		blog.setUsername("varun");
		blog.setCreateDate(new Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setStatus("NA");
		
		assertTrue("Problem in Adding the Blog",blogDAO.addBlog(blog));
	}

}

package com.coll.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.UserDAO;
import com.coll.model.UserDetail;

public class UserDAOTest 
{

	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	@Ignore
	@Test
	public void registerUserTest()
	{
		UserDetail user=new UserDetail();
		user.setUsername("bharath");
		user.setPassword("12345");
		user.setEmailId("bharath@gmail.com");
		user.setStatus("A");
		user.setMemberName("Bharath DB");
		user.setIsOnline("Y");
		user.setRole("ROLE_ADMIN");
		
		assertTrue("Problem in Registering the User",userDAO.registerUser(user));
	}
	
	@Ignore
	@Test
	public void checkUser()
	{
		UserDetail user=new UserDetail();
		user.setUsername("varun");
		user.setPassword("12345");
		
		UserDetail user1=userDAO.checkUser(user);
		
		assertNotNull("Problem in Login Credentials",user1);
		
		System.out.println("Member Name:"+user1.getMemberName());
		System.out.println("Role:"+user1.getRole());
	}
	
	@Ignore
	@Test
	public void updateUserTest()
	{
		UserDetail user=userDAO.getUser("prashanth");
		user.setEmailId("prashanth8@gmail.com");
		
		assertTrue("Problem in updating the Userdetail",userDAO.updateUser(user));
	}
}

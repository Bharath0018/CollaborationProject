package com.coll.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coll.dao.UserDAO;
import com.coll.model.UserDetail;

@RestController
public class UserRestController 
{
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/registerUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody UserDetail userDetail)
	{
		userDetail.setRole("ROLE_USER");
		userDetail.setStatus("A");
		userDetail.setIsOnline("Y");
		
		if(userDAO.registerUser(userDetail))
		{
			return new ResponseEntity<String>("User Resgistered",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/checkUser")
	public ResponseEntity<UserDetail> checkLogin(@RequestBody UserDetail user, HttpSession session)
	{
		UserDetail userDetail1=userDAO.checkUser(user);
		
		if(userDetail1!=null)
		{
			session.setAttribute("userDetail", userDetail1);
			return new ResponseEntity<UserDetail>(userDetail1, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserDetail>(userDetail1,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/updateUser",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody UserDetail userDetail)
	{
		UserDetail user1=userDAO.getUser(userDetail.getUsername());
		userDetail.setRole(user1.getRole());
		userDetail.setStatus(user1.getStatus());
		userDetail.setIsOnline(user1.getIsOnline());
		
		if(userDAO.updateUser(userDetail))
		{
			return new ResponseEntity<String>("User Updated", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

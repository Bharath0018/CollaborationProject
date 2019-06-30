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

import com.coll.dao.BlogDAO;
import com.coll.model.Blog;

@RestController
public class BlogRestController 
{
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/showAllBlogs")
	public ResponseEntity<List<Blog>> showAllBlogs()
	{
		List<Blog> listBlogs=blogDAO.listBlogs();
		
		
		if(listBlogs.size()>0)
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogId")int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	
	@PostMapping(value="/addBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setDislikes(0);
		blog.setStatus("NA");
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("Blog Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Problem Occured",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/deleteBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlog(@PathVariable("blogId")int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping(value="/updateBlog",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
	{
		Blog blog1=blogDAO.getBlog(blog.getBlogid());
		blog.setCreateDate(blog1.getCreateDate());
		blog.setLikes(blog1.getLikes());
		blog.setDislikes(blog1.getDislikes());
		blog.setStatus(blog1.getStatus());
		blog.setUsername(blog1.getUsername());
	
		if(blogDAO.updateBlog(blog))
		{
			return new ResponseEntity<String>("Blog Updated", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/approveBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> approveBlog(@PathVariable("blogId")int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.approveBlog(blog))
		{
			return new ResponseEntity<String>("Approved",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectBlog/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId")int blogId)
	{
		Blog blog=(Blog)blogDAO.getBlog(blogId);
		
		if(blogDAO.rejectBlog(blog))
		{
			return new ResponseEntity<String>("Rejected",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed to Reject",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@GetMapping(value="/incrementLikes/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementLikes(@PathVariable("blogId")int blogId)
	{
		if(blogDAO.incrementLikes(blogId))
		{
			return new ResponseEntity<String>("Liked",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/incrementDisLikes/{blogId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> incrementDisLikes(@PathVariable("blogId")int blogId)
	{
		if(blogDAO.incrementDisLikes(blogId))
		{
			return new ResponseEntity<String>("DisLiked",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

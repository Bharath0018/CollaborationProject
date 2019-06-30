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

import com.coll.dao.BlogCommentDAO;
import com.coll.model.BlogComment;

@RestController
public class BlogCommentRestController 
{
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@GetMapping("/listBlogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> listBlogComments(@PathVariable("blogId")int blogId)
	{
		List<BlogComment> listBlogComments=blogCommentDAO.listBlogComment(blogId);
		
		if(listBlogComments.size()>0)
		{
			return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<BlogComment>>(listBlogComments, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/addBlogComment",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment)
	{
		blogComment.setCommentDate(new java.util.Date());
		
		if(blogCommentDAO.addComment(blogComment))
		{
			return new ResponseEntity<String>("Comment Added",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed", HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping(value="/deleteComment/{blogCommentId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteBlogComment(@PathVariable("blogCommentId")int blogCommentId)
	{
		BlogComment blogComment=blogCommentDAO.getBlogComment(blogCommentId);
		
		if(blogCommentDAO.deleteComment(blogComment))
		{
			return new ResponseEntity<String>("Comment Deleted", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed",HttpStatus.NOT_FOUND);
		}
	}

}

package com.coll.dao;

import java.util.List;

import com.coll.model.BlogComment;

public interface BlogCommentDAO 
{
	public boolean addComment(BlogComment comment);
	public boolean deleteComment(BlogComment comment);
	public boolean updateComment(BlogComment comment);
	public BlogComment getBlogComment(int commentId);
	public List<BlogComment> listBlogComment(int blogId);

}

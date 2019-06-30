package com.coll.dao;

import com.coll.model.UserDetail;

public interface UserDAO 
{
	public boolean registerUser(UserDetail user);
	public UserDetail getUser(String username);
	public boolean updateUser(UserDetail user);
	public UserDetail checkUser(UserDetail user);

}

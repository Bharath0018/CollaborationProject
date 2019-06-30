package com.coll.dao;

import com.coll.model.ProfilePicture;

public interface ProfilePictureDAO 
{
	public boolean save(ProfilePicture profilePicture);
	public ProfilePicture getProfilePicture(String username);

}

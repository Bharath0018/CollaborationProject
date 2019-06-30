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

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;

@RestController
public class FriendRestController 
{
	@Autowired
	FriendDAO friendDAO;
	
	@GetMapping("/showFriendList/{username}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("username")String username)
	{
		List<Friend> friendList=friendDAO.showFriendList(username);
		
		if(friendList.size()>=0)
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/showPendingFriendList/{username}")
	public ResponseEntity<List<Friend>> showPendingFriendList(@PathVariable("username")String username)
	{
		List<Friend> friendList=friendDAO.showPendingFriendRequest(username);
		
		if(friendList.size()>=0)
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Friend>>(friendList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/showSuggestedFriendList/{username}")
	public ResponseEntity<List<UserDetail>> showSuggestedFriendList(@PathVariable("username")String username)
	{
		List<UserDetail> friendSuggestionList=friendDAO.showSuggestedFriend(username);
		
		if(friendSuggestionList.size()>=0)
		{
			return new ResponseEntity<List<UserDetail>>(friendSuggestionList,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<UserDetail>>(friendSuggestionList,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/acceptFriendRequest/{friendId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> acceptFriendRequestf(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.acceptFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Friend Request Accepted", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/deleteFriendRequest/{friendId}",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteFriendRequestf(@PathVariable("friendId")int friendId)
	{
		if(friendDAO.deleteFriendRequest(friendId))
		{
			return new ResponseEntity<String>("Friend Request Deleted", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/sendFriendRequest",produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> sendFriendRequest(@RequestBody Friend friend)
	{
		if(friendDAO.sendFriendRequest(friend))
		{
			return new ResponseEntity<String>("Friend Request Sent", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}

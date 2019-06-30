package com.coll.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.coll.dao.FriendDAO;
import com.coll.model.Friend;
import com.coll.model.UserDetail;

public class FriendDAOTest 
{
	static FriendDAO friendDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.coll");
		context.refresh();
		
		friendDAO=(FriendDAO)context.getBean("friendDAO");
	}
	@Ignore
	@Test
	public void sendFriendRequestTest()
	{
		Friend friend=new Friend();
		
		friend.setUsername("prashanth");
		friend.setFriendusername("bharath");
		
		assertTrue("Problem in sending Friend Request",friendDAO.sendFriendRequest(friend));
	}
	@Ignore
	@Test
	public void acceptFriendRequest()
	{
		assertTrue("Problem in Accepting Friend Request",friendDAO.acceptFriendRequest(954));
	}
	@Ignore
	@Test
	public void showFriendListTest()
	{
		List<Friend> friendList=friendDAO.showFriendList("prashanth");
		assertTrue("Problem in Listing Friends",friendList.size()>0);
		
		for(Friend friend:friendList)
		{
			System.out.print(friend.getUsername()+":::");
			System.out.println(friend.getFriendusername());
		}
	}
	@Ignore
	@Test
	public void showPendingFriendListTest()
	{
		List<Friend> pendingFriendList=friendDAO.showPendingFriendRequest("bharath");
		assertTrue("Problem in Listing Pending requests",pendingFriendList.size()>0);
		
		for(Friend friend:pendingFriendList)
		{
			System.out.print(friend.getUsername());
		}
	}
	@Ignore
	@Test
	public void deleteFriendRequest()
	{
		assertTrue("Problem in deleting Friend Request",friendDAO.deleteFriendRequest(952));
	}
	@Ignore
	@Test
	public void showSuggestedFriendList()
	{
		List<UserDetail> userDetailList=friendDAO.showSuggestedFriend("bharath");
		
		assertTrue("Problem in showing Suggested Friends",userDetailList.size()>0);
		
		for(UserDetail userDetail:userDetailList)
		{
			System.out.println(userDetail.getUsername());
		}
	}

	
}

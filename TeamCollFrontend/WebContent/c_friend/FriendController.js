myApp.controller("FriendController",function($scope,$location,$rootScope,$http)
{
	$scope.friend={"friendId":0,"username":"","friendusername":"","status":""};
	$scope.friendList;
	
	$scope.pendingFriendList;
	
	$scope.suggestedFriendList;
	
	function showFriendList()
	{
		console.log('--Showing Friend List--');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/showFriendList/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.friendList=response.data;
		},function(errresponse)
		{
			alert("Error Occured");
		});
	}
	
	function showPendingFriendList()
	{
		console.log('--Showing Pending Friend List--');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/showPendingFriendList/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.pendingFriendList=response.data;
		},function(errresponse)
		{
			alert("Error Occured");
		});
	}
	
	function showSuggestedFriendList()
	{
		console.log('--Showing Suggested Friend List--');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/showSuggestedFriendList/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.suggestedFriendList=response.data;
			
		},function(errresponse)
		{
			alert("Error Occured");
		});
	}
	
	$scope.send=function(username)
	{
		$scope.friend.username=$rootScope.currentUser.username;
		$scope.friend.friendusername=username;
		$scope.friend.status="A";
		
		$http.post('http://localhost:8081/TeamCollMiddleware/sendFriendRequest',$scope.friend)
		.then(function(response)
		{
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
			alert("Friend Request Sent");
		},function(errresponse)
		{
			alert("Error Occured while Sending Friend Request");
		});
	}
	
	$scope.accept=function(friendId)
	{
		console.log('Accepting Friend Request');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/acceptFriendRequest/'+friendId)
		.then(function(response)
		{
			alert("Friend Request Accpeted");
		},function(errresponse)
		{
			alert("Unable to accept Friend Request");
		});
	}
	
	$scope.unfriend=function(friendId)
	{
		console.log('Rejecting or Deleting Friend Request');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/deleteFriendRequest/'+friendId)
		.then(function(response)
		{
			
			alert("Friend Request Deleted");
		},function(errresponse)
		{
			alert("Unable to Delete Friend Request");
		});
		
	}
	
	
	
	showFriendList();
	showPendingFriendList();
	showSuggestedFriendList();
});
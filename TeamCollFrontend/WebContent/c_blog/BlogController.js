myApp.controller("BlogController",function($scope,$location,$rootScope,$http)
{
	$scope.blog={"blogid":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
	
	$scope.blogdata;
	
	$rootScope.blogid;
	
	$scope.addBlog=function()
	{
		$scope.blog.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8081/TeamCollMiddleware/addBlog',$scope.blog)
		.then(function(response){
			console.log('Blog Added');
			console.log($scope.blog);
			alert("Blog Added");
			$location.path("/addBlog");
			
		},
		function(errresponse)
		{
			console.log('Error Occured');
			alert("Error while Adding Blog");
			$location.path("/addBlog");
			
		});
	}
	
	function listBlogs()
	{
		console.log('List Blog Method');
		
		$http.get('http://localhost:8081/TeamCollMiddleware/showAllBlogs')
		.then(function(response)
		{
			console.log('Showing All Blogs');
			$scope.blogdata=response.data;
			
		},
		function(errresponse)
		{
			console.log('Error Occured');
		});
	}
	
	$scope.incrementLikes=function(blogid)
	{
		console.log('Liked');
		$http.get('http://localhost:8081/TeamCollMiddleware/incrementLikes/'+blogid)
		.then(function(response)
		{
			listBlogs();
			$location.path("/showBlog");
		});
	}
	
	$scope.incrementDisLikes=function(blogid)
	{
		console.log('DisLiked');
		$http.get('http://localhost:8081/TeamCollMiddleware/incrementDisLikes/'+blogid)
		.then(function(response)
		{
			listBlogs();
			$location.path("/showBlog");
		});
	}
	
	$scope.deleteBlog=function(blogid)
	{
		console.log('Deleting the Blog');
		$http.get('http://localhost:8081/TeamCollMiddleware/deleteBlog/'+blogid)
		.then(function(response)
		{
			listBlogs();
			alert('Blog Deleted');
			$location.path("/showBlog");
		},function(errresponse)
		{
			console.log('Error Occured');
			alert('Error occured while Deleting Blog');
	
		});
	}
	
	$scope.approve=function(blogid)
	{
		console.log('Approving Blog');
		$http.get('http://localhost:8081/TeamCollMiddleware/approveBlog/'+blogid)
		.then(function(response)
		{
			listBlogs();
			alert('Blog Approved');
			$location.path("/adminBlog");
		},function(errresponse)
		{
			console.log('Error Occured');
			alert('Error occured while Approving Blog');
	
		});
	}
	
	$scope.reject=function(blogid)
	{
		console.log('Rejecting Blog');
		$http.get('http://localhost:8081/TeamCollMiddleware/rejectBlog/'+blogid)
		.then(function(response)
		{
			listBlogs();
			alert('Blog Rejected');
			$location.path("/adminBlog");
		},function(errresponse)
		{
			console.log('Error Occured');
			alert('Error occured while Rejecting Blog');
	
		});
	}
	
	$scope.editBlog=function(blogid)
	{
		console.log('Updating the Blog');
		$rootScope.blogid=blogid;
		console.log('RootScope BlogId'+$rootScope.blogid);
		$location.path("/updateBlog");
	}
	function getBlog()
	{
		$http.get('http://localhost:8081/TeamCollMiddleware/getBlog/'+$rootScope.blogid)
		.then(function(response)
		{
			$scope.blog=response.data;
		});
	}
	
	$scope.updateBlog=function()
	{
		console.log('I am in Update Blog');
		$location.path("/showBlog");
		$http.post('http://localhost:8081/TeamCollMiddleware/updateBlog',$scope.blog)
		.then(function(response)
		{
			alert("Blog Updated");
			
		},function(errresponse)
		{
			alert("Error Occured");
		});
	}
	
	$scope.showComment=function(blogid)
	{
		console.log('I am in Show Comment');
		$rootScope.blogid=blogid;
		$location.path("/showBlogComment");
	}
	
	getBlog();
	listBlogs();
});
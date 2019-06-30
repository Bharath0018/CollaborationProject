myApp.controller("BlogCommentController",function($scope,$location,$rootScope,$http)
{
	$scope.blog={"blogid":0,"blogName":"","blogContent":"","username":"","createDate":"","status":"","likes":0,"dislikes":0};
	
	$scope.blogComment;
	
	$scope.comment={"commentId":0,"blogId":0,"commentText":"","commentDate":"","username":""};
	
	function loadBlog()
	{
		$http.get('http://localhost:8081/TeamCollMiddleware/getBlog/'+$rootScope.blogid)
		.then(function(response)
		{
			$scope.blog=response.data;
			console.log($scope.blog);
		});
	}
	
	function loadBlogComments()
	{
		$http.get('http://localhost:8081/TeamCollMiddleware/listBlogComments/'+$rootScope.blogid)
		.then(function(response)
		{
			$scope.blogComment=response.data;
			console.log($scope.blogComment);
		});
	}
	
	$scope.addComment=function()
	{
		console.log('I am in add Comment');
		$scope.comment.blogId=$scope.blog.blogid;
		$scope.comment.username=$rootScope.currentUser.username;
		$http.post('http://localhost:8081/TeamCollMiddleware/addBlogComment',$scope.comment)
		.then(function(response)
		{
			loadBlogComments();
			alert("Comment Added");
		},function(errresponse)
		{
			alert("Error Occured");
		});
	}
	
	loadBlog();
	loadBlogComments();
});
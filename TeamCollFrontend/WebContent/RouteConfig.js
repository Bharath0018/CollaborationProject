var myApp=angular.module("myApp",['ngRoute','ngCookies']);
myApp.config(function($routeProvider)
{
	
	alert("I am in Route Provider");
	
	$routeProvider.when("/login",{templateUrl:"c_user/Login.html"})
				  .when("/register",{templateUrl:"c_user/Register.html"})
				  .when("/addBlog",{templateUrl:"c_blog/AddBlog.html"})
				  .when("/showBlog",{templateUrl:"c_blog/ShowBlog.html"})
				  .when("/adminBlog",{templateUrl:"c_blog/ManageBlog.html"})
				  .when("/updateBlog",{templateUrl:"c_blog/UpdateBlog.html"})
				  .when("/showBlogComment",{templateUrl:"c_blog/BlogComment.html"})
				  .when("/profileUpdate",{templateUrl:"c_user/ProfileUpdate.html"})
				  .when("/friend",{templateUrl:"c_friend/Friend.html"})
				  .when("/chat",{templateUrl:"c_chat/Chat.html"})
				  .when("/manageJob",{templateUrl:"c_job/ManageJob.html"})
				  .when("/showJobs",{templateUrl:"c_job/ShowJob.html"})
				  .when("/aboutus",{templateUrl:"c_about/AboutUs.html"})
				  .when("/contactus",{templateUrl:"c_about/ContactUs.html"})
				  .when("/userHome",{templateUrl:"c_about/UserHome.html"});

});

myApp.run(function($rootScope,$cookieStore)
{
	console.log('I am in run function');
	if($rootScope.currentUser==undefined)
	{
		$rootScope.currentUser=$cookieStore.get('userDetail');
	}
	
});
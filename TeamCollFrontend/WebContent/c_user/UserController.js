myApp.controller("UserController",function($scope,$location,$rootScope,$http,$cookieStore){
	
	$scope.userDetail={username:'',password:'',memberName:'',emailId:'',role:'',status:'',isOnline:''};
	
	$rootScope.currentUser;
	
	$scope.checkUser=function()
	{
		console.log($scope.userDetail);
		
		$http.post('http://localhost:8081/TeamCollMiddleware/checkUser',JSON.stringify($scope.userDetail))
		.then(function(response)
		{
			console.log('Logged In');
			console.log(response.data);
			console.log(response.statusCode);
			$rootScope.currentUser=response.data;
			console.log($rootScope.currentUser);
			$cookieStore.put('userDetail',response.data);
			$location.path("/userHome");
		},
		function(errresponse)
		{
			console.log('Invalid Credentials');
			$scope.error="Invalid Credentials, please enter correct Username and password";
			$location.path("/login");
		});
	}
	
	$scope.register=function()
	{
		$scope.userDetail.role='ROLE_USER';
		$scope.userDetail.status='Y';
		$scope.userDetail.isOnline='N';
		
		console.log($scope.userDetail);
		
		$http.post('http://localhost:8081/TeamCollMiddleware/registerUser',JSON.stringify($scope.userDetail))
		.then(function(response)
		{
			console.log('User Registered');
			console.log(response.data);
			$location.path("/login");
		},
		function(errresponse)
		{
			console.log('Unable to Register');
			$scope.error="Unable to Register";
			console.log(response.data);
			$location.path("/register");
		});
	}
	
	$scope.logout=function()
	{
		console.log('Logging Out');
		delete $rootScope.currentUser;
		$cookieStore.remove('userDetail');
		alert("Logged Out!! To continue please Login");
		$location.path("/login");
	}
});

myApp.controller("JobController",function($scope,$location,$rootScope,$http)
{
	
	$scope.job={"jobId":0,"jobDesignation":"","companyName":"","CTC":0,"location":"","lastDatetoApply":"","skills":""};
	
	$scope.jobs;
	
	$scope.publishJob=function()
	{
		console.log('--I am in Publish Job--');
		
		console.log('job Detail'+$scope.job);
		
		$http.post('http://localhost:8081/TeamCollMiddleware/publishJob',$scope.job)
		.then(function(response){
			alert("Job Published");
		},function(errresponse){
			alert("Error occured while publishing Job");
		});
	}
	
	function showJobs()
	{
		$http.get('http://localhost:8081/TeamCollMiddleware/showJobs')
		.then(function(response){
			$scope.jobs=response.data;
		},function(errresponse){
			alert("Error occured while Listing all Jobs");
		});
	}
	
	showJobs();
	
});
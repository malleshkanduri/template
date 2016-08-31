var workflowApp = angular.module('workflow', ['ngRoute','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
    // configure our routes
workflowApp.config(function($routeProvider) {
        $routeProvider

            // route for the home page
            .when('/', {
                templateUrl : 'typeahead.html',
                controller  : 'TypeaheadCtrl'
            })

            // route for the about page
            .when('/about', {
                templateUrl : 'about.html',
                controller  : 'aboutController'
            })

            // route for the contact page
            .when('/contact', {
                templateUrl : 'contact.html',
                controller  : 'contactController'
            })
            
            .when('/edit', {
            	templateUrl : 'edit.html',
            	controller  : 'editController'
            });
    });

    // create the controller and inject Angular's $scope
    workflowApp.controller('mainController', function($scope) {
        // create a message to display in our view
        $scope.message = 'Everyone come and see how good I look!';
    });
    
    workflowApp.controller('editController', function($scope, $http, $routeParams, $location, $timeout) {
        $scope.pname="";
        $scope.powner="";
		$scope.recordSaved=false;
		
		
        $scope.clear = function() {
        	  $scope.pname="";
              $scope.powner="";			
    	};
    	
    	$scope.save = function() {
            return $http.get('http://template-141501.appspot.com/template?action=add', {
  		      params: {
  		        pname:$scope.pname,
  		        powner:$scope.powner,  
  		        
  		      }
  		    }).then(function(response){
  		    	console.log(response);
				$scope.recordSaved=true;
				
  		    	//$location.path('/')  			
  		    	}
  		    );
            
    	};
		
       //alert($routeParams.id)
        if ($routeParams.id == undefined) {
        	console.log('No ID.. Do nothing')
        } else {
        	console.log('Fetch ID')
        
       
     	return $http.get('http://template-141501.appspot.com/template?action=searchById', {
		      params: {
		        q: $routeParams.id,
		      }
		    }).then(function(response){

		    	console.log(response);

		    	console.log(response.data);
		    	console.log(response.data.projectOwner);
		    	console.log(response.data.projectName);
		    	$scope.pname=response.data.projectName
		    	$scope.powner=response.data.projectOwner
		    	
		    }
		    );
        	
        	
        }
        
        
        
        });
    
    
    
    workflowApp.controller('TypeaheadCtrl', function($scope, $http, $location) {
    	  $scope.displayAction = "HomePage";
		  $scope.displayResults=false;
		  $scope.searchresults;
    	  var _selected;

    	  $scope.selected = undefined;
    	  
    	  $scope.getLocationCust = function(val) {
    		  	
    		  	return $http.get('http://template-141501.appspot.com/template?action=searchPName', {
    		      params: {
    		        pname: val,
    		      }
    		    }).then(function(response){
    		      return response.data.map(function(item){
    		        return item;
    		      });
    		    });
    		  
    		  
    		  
    		 
    		    
    		    
    		    
    		  };


    	  $scope.ngModelOptionsSelected = function(value) {
    	    if (arguments.length) {
    	      _selected = value;
    	    } else {
    	      return _selected;
    	    }
    	  };

    	  $scope.modelOptions = {
    	    debounce: {
    	      default: 500,
    	      blur: 250
    	    },
    	    getterSetter: true
    	  };
    	  
    	  
     	 $scope.searchthis = function() {
    			$scope.displayAction='search';
    			
    			var value ="";
    			
    			if ($scope.asyncSelected != undefined) {
    			 
    				value = $scope.asyncSelected.projectName;
    				
    				if (value == undefined) {
    					value=$scope.asyncSelected;
    				}
    			}
									
				console.log($scope.asyncSelected); 
    			//console.log($scope.asyncSelected.projectName);   	
				console.log("search key -" + value);

				
				return $http.get('http://template-141501.appspot.com/template?action=searchPName', {
    		      params: {
    		        pname: value,
    		      }
    		    }).then(function(response){
					$scope.searchresults = response.data;
					console.log($scope.searchresults)
					$scope.displayResults=true;
    		    });
    	 };
    	 
    	 $scope.editthis = function() {
 			$scope.displayAction='edit';
 			$location.path('/edit')  			
    	 };
    	 
    	 

    	 $scope.statesWithFlags = [{'name':'Alabama','flag':'5/5c/Flag_of_Alabama.svg/45px-Flag_of_Alabama.svg.png'},{'name':'Alaska','flag':'e/e6/Flag_of_Alaska.svg/43px-Flag_of_Alaska.svg.png'},{'name':'Arizona','flag':'9/9d/Flag_of_Arizona.svg/45px-Flag_of_Arizona.svg.png'},{'name':'Arkansas','flag':'9/9d/Flag_of_Arkansas.svg/45px-Flag_of_Arkansas.svg.png'},{'name':'California','flag':'0/01/Flag_of_California.svg/45px-Flag_of_California.svg.png'},{'name':'Colorado','flag':'4/46/Flag_of_Colorado.svg/45px-Flag_of_Colorado.svg.png'},{'name':'Connecticut','flag':'9/96/Flag_of_Connecticut.svg/39px-Flag_of_Connecticut.svg.png'},{'name':'Delaware','flag':'c/c6/Flag_of_Delaware.svg/45px-Flag_of_Delaware.svg.png'},{'name':'Florida','flag':'f/f7/Flag_of_Florida.svg/45px-Flag_of_Florida.svg.png'},{'name':'Georgia','flag':'5/54/Flag_of_Georgia_%28U.S._state%29.svg/46px-Flag_of_Georgia_%28U.S._state%29.svg.png'},{'name':'Hawaii','flag':'e/ef/Flag_of_Hawaii.svg/46px-Flag_of_Hawaii.svg.png'},{'name':'Idaho','flag':'a/a4/Flag_of_Idaho.svg/38px-Flag_of_Idaho.svg.png'},{'name':'Illinois','flag':'0/01/Flag_of_Illinois.svg/46px-Flag_of_Illinois.svg.png'},{'name':'Indiana','flag':'a/ac/Flag_of_Indiana.svg/45px-Flag_of_Indiana.svg.png'},{'name':'Iowa','flag':'a/aa/Flag_of_Iowa.svg/44px-Flag_of_Iowa.svg.png'},{'name':'Kansas','flag':'d/da/Flag_of_Kansas.svg/46px-Flag_of_Kansas.svg.png'},{'name':'Kentucky','flag':'8/8d/Flag_of_Kentucky.svg/46px-Flag_of_Kentucky.svg.png'},{'name':'Louisiana','flag':'e/e0/Flag_of_Louisiana.svg/46px-Flag_of_Louisiana.svg.png'},{'name':'Maine','flag':'3/35/Flag_of_Maine.svg/45px-Flag_of_Maine.svg.png'},{'name':'Maryland','flag':'a/a0/Flag_of_Maryland.svg/45px-Flag_of_Maryland.svg.png'},{'name':'Massachusetts','flag':'f/f2/Flag_of_Massachusetts.svg/46px-Flag_of_Massachusetts.svg.png'},{'name':'Michigan','flag':'b/b5/Flag_of_Michigan.svg/45px-Flag_of_Michigan.svg.png'},{'name':'Minnesota','flag':'b/b9/Flag_of_Minnesota.svg/46px-Flag_of_Minnesota.svg.png'},{'name':'Mississippi','flag':'4/42/Flag_of_Mississippi.svg/45px-Flag_of_Mississippi.svg.png'},{'name':'Missouri','flag':'5/5a/Flag_of_Missouri.svg/46px-Flag_of_Missouri.svg.png'},{'name':'Montana','flag':'c/cb/Flag_of_Montana.svg/45px-Flag_of_Montana.svg.png'},{'name':'Nebraska','flag':'4/4d/Flag_of_Nebraska.svg/46px-Flag_of_Nebraska.svg.png'},{'name':'Nevada','flag':'f/f1/Flag_of_Nevada.svg/45px-Flag_of_Nevada.svg.png'},{'name':'New Hampshire','flag':'2/28/Flag_of_New_Hampshire.svg/45px-Flag_of_New_Hampshire.svg.png'},{'name':'New Jersey','flag':'9/92/Flag_of_New_Jersey.svg/45px-Flag_of_New_Jersey.svg.png'},{'name':'New Mexico','flag':'c/c3/Flag_of_New_Mexico.svg/45px-Flag_of_New_Mexico.svg.png'},{'name':'New York','flag':'1/1a/Flag_of_New_York.svg/46px-Flag_of_New_York.svg.png'},{'name':'North Carolina','flag':'b/bb/Flag_of_North_Carolina.svg/45px-Flag_of_North_Carolina.svg.png'},{'name':'North Dakota','flag':'e/ee/Flag_of_North_Dakota.svg/38px-Flag_of_North_Dakota.svg.png'},{'name':'Ohio','flag':'4/4c/Flag_of_Ohio.svg/46px-Flag_of_Ohio.svg.png'},{'name':'Oklahoma','flag':'6/6e/Flag_of_Oklahoma.svg/45px-Flag_of_Oklahoma.svg.png'},{'name':'Oregon','flag':'b/b9/Flag_of_Oregon.svg/46px-Flag_of_Oregon.svg.png'},{'name':'Pennsylvania','flag':'f/f7/Flag_of_Pennsylvania.svg/45px-Flag_of_Pennsylvania.svg.png'},{'name':'Rhode Island','flag':'f/f3/Flag_of_Rhode_Island.svg/32px-Flag_of_Rhode_Island.svg.png'},{'name':'South Carolina','flag':'6/69/Flag_of_South_Carolina.svg/45px-Flag_of_South_Carolina.svg.png'},{'name':'South Dakota','flag':'1/1a/Flag_of_South_Dakota.svg/46px-Flag_of_South_Dakota.svg.png'},{'name':'Tennessee','flag':'9/9e/Flag_of_Tennessee.svg/46px-Flag_of_Tennessee.svg.png'},{'name':'Texas','flag':'f/f7/Flag_of_Texas.svg/45px-Flag_of_Texas.svg.png'},{'name':'Utah','flag':'f/f6/Flag_of_Utah.svg/45px-Flag_of_Utah.svg.png'},{'name':'Vermont','flag':'4/49/Flag_of_Vermont.svg/46px-Flag_of_Vermont.svg.png'},{'name':'Virginia','flag':'4/47/Flag_of_Virginia.svg/44px-Flag_of_Virginia.svg.png'},{'name':'Washington','flag':'5/54/Flag_of_Washington.svg/46px-Flag_of_Washington.svg.png'},{'name':'West Virginia','flag':'2/22/Flag_of_West_Virginia.svg/46px-Flag_of_West_Virginia.svg.png'},{'name':'Wisconsin','flag':'2/22/Flag_of_Wisconsin.svg/45px-Flag_of_Wisconsin.svg.png'},{'name':'Wyoming','flag':'b/bc/Flag_of_Wyoming.svg/43px-Flag_of_Wyoming.svg.png'}];
    	
    

    
    
    });

    workflowApp.controller('aboutController', function($scope) {
        $scope.message = 'Look! I am an about page.';
    });

    workflowApp.controller('contactController', function($scope) {
        $scope.message = 'Contact us! JK. This is just a demo.';
    });
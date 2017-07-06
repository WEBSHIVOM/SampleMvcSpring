<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"> 
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script> 
    <script>
    // Defining angularjs application.
    var postApp = angular.module('postApp', []);
    // Controller function and passing $http service and $scope var.
    postApp.controller('LoginController', function($scope, $http) {
      // create a blank object to handle form data.
     
        $scope.user = {};
      // calling our submit function.
        $scope.submitForm = function() {
        	 alert($scope.user)
        // Posting data to jsp file
        $http({
          method  : 'POST',
          url     : 'log',
          data    : $scope.user, //forms user object
          //headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
         })
          .success(function(data) {
            if (data.errors) {
              // Showing errors.
              $scope.errorName = data.errors.name;
              $scope.errorPassword = data.errors.password;
              
            } else {
              $scope.message = data.message;
            }
          });
        };
    });
</script>
</head>
<body ng-app="postApp" ng-controller="LoginController">
<div class="container">
<div class="col-sm-8 col-sm-offset-2">
    
    <!-- FORM -->
    <form name="userForm" ng-submit="submitForm()">
    <div class="form-group">
        <label>Name</label>
        <input type="text" name="name" class="form-control" ng-model="user.name">
        <span ng-show="errorName">{{errorName}}</span>
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="text" name="password" class="form-control" ng-model="user.password">
        <span ng-show="errorPassword">{{errorPassword}}</span>
    </div>
   
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</div>
</body>
</html>
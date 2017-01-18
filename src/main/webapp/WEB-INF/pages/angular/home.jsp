<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test</title>
	<style>
	.name.ng-valid {
		background-color: lightgreen;
	}
	
	.name.ng-dirty.ng-invalid-required {
		background-color: red;
	}
	
	.name.ng-dirty.ng-invalid-minlength {
		background-color: yellow;
	}
	
	
	</style>
<link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
<!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp">
	<div class="generic-container" ng-controller="StudentController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading"><span class="lead">Student Registration Form</span></div>
			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
					<input type="hidden" ng-model="ctrl.student.id"/>
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="file">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.name" name ="name" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.name.$error.required">This is a required field</span>
									<span ng-show="myForm.name.$error.minlength">Minimum length required is 3</span>
									<span ng-show="myForm.name.$error.invalid">This field is invalid</span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="file">Age</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.age" name="age" class=" form-control input-sm" placeholder="Enter your Age" required/>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.age.$error.required">This is a required field</span>								
									<span ng-show="myForm.age.$error.invalid">This field is invalid</span>								
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-label" for="file">Birth</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.student.birth" name="birth" class=" form-control input-sm" placeholder="Enter your birth" required/>
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.birth.$error.required">This is a required field</span>								
									<span ng-show="myForm.birth.$error.invalid">This field is invalid</span>								
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-actions floatRight" >
							<input type="submit" value="{{! ctrl.student.id ? '添加':'修改'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid"/>
							<button type="submit" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">重置</button>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	
	
		<div class="panel panel-default" >
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead">List of Users </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Age</th>
							<th>Birthday</th>
							<th width="20%">operation</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="s in ctrl.students">
							<td><span ng-bind="s.id"></span></td>
							<td><span ng-bind="s.name"></span></td>
							<td><span ng-bind="s.age"></span></td>
							<td><span ng-bind="s.birth"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(s.id)" class="btn btn-success custom-width">修改</button>
								<button type="button" ng-click="ctrl.remove(s.id)" class="btn btn-success custom-width">删除</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script src="<c:url value ='/static/js/angular.min.js'/>"></script>
	<script src="<c:url value='/static/js/init.js' />"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>  
	    <title>Weather Forecast</title>  
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <link href="<c:url value='/static/css/weather.css' />" rel="stylesheet"></link>
	</head>
  	<body ng-app="myApp" class="background">
		<div class="generic-container" ng-controller="WeatherController as wCtrl">
	    	<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand"><img class="icon" src="<c:url value='/static/images/weather-headicon.png' />"></a>
					</div>
					<div class="navbar-header">
						<span class="navbar-brand">{{ wCtrl.appTitle }}</span>
					</div>
					<ul class="nav navbar-nav">
						<li ng-class="{active : wCtrl.activeItem === 'Home'}"><a ng-click="wCtrl.menuItemClick('Home')">Home</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Adelaide'}"><a ng-click="wCtrl.menuItemClick('Adelaide')">Adelaide</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Alice Springs'}"><a ng-click="wCtrl.menuItemClick('Alice Springs')">Alice Springs</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Brisbane'}"><a ng-click="wCtrl.menuItemClick('Brisbane')">Brisbane</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Broome'}"><a ng-click="wCtrl.menuItemClick('Broome')">Broome</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Canberra'}"><a ng-click="wCtrl.menuItemClick('Canberra')">Canberra</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Cairns'}"><a ng-click="wCtrl.menuItemClick('Cairns')">Cairns</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Darwin'}"><a ng-click="wCtrl.menuItemClick('Darwin')">Darwin</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Hobart'}"><a ng-click="wCtrl.menuItemClick('Hobart')">Hobart</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Perth'}"><a ng-click="wCtrl.menuItemClick('Perth')">Perth</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Melbourne'}"><a ng-click="wCtrl.menuItemClick('Melbourne')">Melbourne</a></li>
						<li ng-class="{active : wCtrl.activeItem === 'Sydney'}"><a ng-click="wCtrl.menuItemClick('Sydney')">Sydney</a></li>
					</ul>
					
					<form class="navbar-form navbar-left" ng-if="wCtrl.showForecast">
		                <div class="form-group">
		                    <input type="text" class="form-control" placeholder="Enter Date dd-MM-yyyy" ng-model="wCtrl.requiredDate">
		                </div>
		                <button type="submit" class="btn" ng-click="wCtrl.buttonClick()">Get Forecast</button>
		            </form>
				</div>
			</nav>
			
			<div id="homePage" class="flex-container" ng-if="wCtrl.showDefault">
				<header>
					<h1 style="color: beige; text-shadow: 2px 1px lightgrey; ">Welcome to Weather Forecast Application</h1>
				</header>
				<article class="article">
					<h3>Weather Forecast Application</h3>
					<h4>This is a simple web application built using Java 1.8, Spring MVC 4.3.1 and Angular JS 1.6.4</h4>
					<h4>The application is for iterating through a sample of recorded temperatures in cities of Australia (Ref:http://www.bom.gov.au/)</h4>
					<h4>and manipulate the various weather parameters using them. The values rendered will be exposed in a rest service and a </h4>
					<h4>JSP will consume the data to be reproduced in the UI using HTML and Angular JS</h4>
					<h5>Further enhancements to this application will be made to fill the areas of improvement</h5>
				</article>
				<footer style="text-align: right;">Designed and Developed by Ganesh Kumar Vellaichamy; Email: ganeshkumar12883@gmail.com</footer>
			</div>
		
			<div ng-if="wCtrl.showForecast">
				<div class="forecastBox" id="forecastContent">
					<div style="float: left" id="forecastContent">
						<div>
							<h2 class="forecastLocation">{{ wCtrl.locationForecast.location }}</h2>
							<h5 class="forecastTime">Location time: {{ wCtrl.locationForecast.localTime }}</h5>
						</div>
						<div id="coordinates" class="coordinates" style="padding-top: 6px">
							<h5 class="forecastCoordinates">{{ wCtrl.locationForecast.latitude }} | {{ wCtrl.locationForecast.longitude }}</h5>
							<h5 class="forecastCoordinates">{{ wCtrl.locationForecast.elevation }}m above sea-level</h5>
						</div>
					</div>
					<div class="conditionIconBox" id="conditionContent">
						<span ng-show="wCtrl.locationForecast.condition=='Rain'">
							<img class="conditionIcon" src="<c:url value='/static/images/condition-rain.png' />"/>
						</span>
						<span ng-show="wCtrl.locationForecast.condition=='Snow'">
							<img class="conditionIcon"  src="<c:url value='/static/images/condition-snow.png' />"/>
						</span>
						<span ng-show="wCtrl.locationForecast.condition=='Sunny'">
							<img class="conditionIcon"  src="<c:url value='/static/images/condition-sunny.png' />"/>
						</span>
					</div>
					<div style="clear: both;"></div>
				</div>
		
				<div class="temperatureBox">
					<div style="float: left;">
						<h1 class="forecastTemperature">{{ wCtrl.locationForecast.temperature }}</h1>
					</div>
		
					<div style="float: left;">
						<h3 style="color: silver; padding-left: 10px; padding-top: 20px;"> {{ wCtrl.locationForecast.condition | uppercase }} IN</h3>
						<h3 style="color: silver; padding-left: 10px;">{{ wCtrl.locationForecast.location | uppercase }}</h3>
					</div>
		
					<div style="clear: both;"></div>
		
					<div>
						<div id="humidity"
							style="float: left; border-right: 0.5px solid; border-color: silver">
							<h2 class="humidPressTitle">Humidity</h2>
							<h2 class="humidPressValue">{{ wCtrl.locationForecast.humidity }}%</h2>
						</div>
						<div id="pressure" style="float: left;">
							<h2 class="humidPressTitle">Pressure</h2>
							<h2 class="humidPressValue">{{ wCtrl.locationForecast.pressure }} hPa</h2>
						</div>
					</div>
		
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>
      
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
		<script src="<c:url value='/static/js/app.js' />"></script>
		<script src="<c:url value='/static/js/service/weather_service.js' />"></script>
		<script src="<c:url value='/static/js/controller/weather_controller.js' />"></script>
	</body>
</html>
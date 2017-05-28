'use strict';

angular.module('myApp').factory('WeatherService', ['$http', '$q', function($http, $q){

	var REST_SERVICE_URI = "http://localhost:8080/WeatherForecast/wf/";

    var factory = {
    	fetchForecastForLocation: fetchForecastForLocation
    };

    return factory;

    function fetchForecastForLocation(location, inputDate) {
    	var WEATHER_SERVICE_URI = REST_SERVICE_URI + location + "/" + inputDate;
    	var deferred = $q.defer();
        $http.get(WEATHER_SERVICE_URI)
            .then(
            function (response) {
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching forecast for location');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);

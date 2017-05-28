'use strict';

angular.module('myApp').controller('WeatherController', ['$scope', 'WeatherService', function ($scope, WeatherService) {
    var self = this;

    self.appTitle = "Weather Forecast Application";
    self.activeItem = 'Home';
    self.showDefault = true;
    self.showForecast = false;
    self.requiredDate = "";
    self.locationForecast = {};

    self.menuItemClick = function (item) {
        if (self.activeItem == item) {
            return;
        }
        self.requiredDate = "";
        self.activeItem = item;
        if (item == "Home") {
            $window.location.reload();
        } else {
            self.showDefault = false;
            self.showForecast = true;

            self.serviceCall(self.activeItem, self.requiredDate);
        }
    }

    self.buttonClick = function () {
        self.showDefault = false;
        self.showForecast = true;

        self.serviceCall(self.activeItem, self.requiredDate);
    }

    self.serviceCall = function (item, inputDate) {
        return WeatherService.fetchForecastForLocation(item, inputDate).then(
            function (weatherdata) {
                self.locationForecast = weatherdata;
            },
            function (errResponse) {
                console.error('Error while fetching weather data for ' + item);
            }
        );
    }
}]);

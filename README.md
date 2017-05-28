# WeatherForecastWeb
Iterate over a sample dataset of temperatures records to determine the weather forecast in a particular location

A Java application written in Java 1.8 to calculate the weather conditions of a place from a pre-calculated data readings over the place for a year. The application is written in specific modules serving each of their purpose in the flow. Since the pre-determined test data is used froma file, the moduels fall as belwo:

  * reader - to read the file data line by line.
  * parser - parse the read data into a pojo for each line.
  * generator - parsed data is used to generate forecast of all available data in a file on current date or any given date.
  * utils - Static Utility functions to assist in the generation process.
  * model - Underlying model objects used to traverse the data and generate the forecast output

Algorithm Used for weather parameters determination:
The yearly summary data of 11 cities of Australia is chosen for this application.
Assuming that the temperature recorded for every months falls on 15th of month, the days in between 15th of two months will have a temperature between the recorded temperatures.
That change (rise/fall) in temeprature is the devaition value of the temperature per day ie, (Tp - Tc)/30 where Tp - Recorded temperature of previous month; Tc - Recorded temperature of current month of the day to be forecasted; 30 - Number of days between the two days of recorded temperature.
Each location will have yearly summary readings of both Highest and Lowest recorded temperature of the month. These two temperatures will be used calculate temperature on a particular hour of the day by applying similar to step 3 between the min and max temperatures. Assumption is that 00:00:01 hrs has the lowest temperature and 12:00:00 hrs has the maximum temeprature.
Weather condition is derived based on the temperature and month of the date required.
Pressure is calculated based on the elevation of the location and temperature recorded at an instance.
Humidity is calcualted based on the condition - Sunny, Rain or Snow.
All the generated location specific forecast for any date required/current date will be written to an output file provided by user while running the program.
Reference Data and Idea: Reference data has been taken from the site: http://www.bom.gov.au/ to design a simple algorithm to calculate the weather forecast of any area. The reference site has Summary statistics of All Years. This data can be a reference data to calculate an approximate temperature on any day of the year for the location.

Architecute Model:
The application runs as a Spring MVC application with the presentation layer on Angular JS + JSP.

Requirements & Running the application:
The application is written in Java 1.8, Spring 4.3.1, Angular 1.4.6 and maven 3.3.9. So you will need Java 1.8 or more. Any IDE will suffice to download the source code and deploy loaclly to test/debug the application functionality.

The war file(WeatherForecast.war) is available in the target folder. Download the war file and deploy the same locally in Tomcat server to access the application via: http://localhost:8080/WeatherForecast/.

The application initially displays a home page and various cities of australia in the navigation menu bar at the top of the page. Click on each link to get the weather conditions of current date-time. If you want to specifically look for a date on which you need the forecast, enter the date in the textbox in format dd-MM-yyyy and click Get Forecast to get that day's forecast for the city now selected.

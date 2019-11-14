# Parkson
Sample Springboot based Backend App enabled with JSON Web Token authentication and uses pgsql as database.

The technology stack it uses is mentioned below;
1- Spring Data
2- JWT Token
3- Postgresql
4- Logaback as logger
5- Maven as build tool
6- React for backend
7- NPM for building front end app
8- Junit 4 for test cases
9- Spring Security for Authentication and Authorization

Please note down the following steps to run application;
1- Create database parkson in pgsql
2- Configure database as per credential in pgsql.properties file
3- Build the app using maven command "mvn clean install" or "mvn clean install -DskipTests" to skip tests
4- To setup first user, either run the test case placed under AuthControllerTests or through auth/signup API
5- React App is placed under parkson-react folder and set it up through following steps:
	i- npm install
	ii- npm run build
	iii- npm start (optional in case you want to run it seperately)
	Note: React app does not work when run using child domain or subdirectory like http://localhost:8080/parkson. I am yet unable to set it up correctly for child domains. React routes or configuration seems to be the problem
6- App is accessed http://localhost:8080/ or http://localhost:8080/parkson/ depending on how you set up the server

Please have a look at the code for more info.








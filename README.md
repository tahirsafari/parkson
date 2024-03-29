# Parkson
## Note: Springboot version downgraded to 1.5 from 2 to make it work on Wildfly9. Springboot 2 was working fine with Wildfly 18.  
### Please make sure you copy the build folder (after running npm run build) of react app located under parkson-frontend, to src/main/webapp/demo.

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
2- Configure database as per local credential in pgsql.properties file
3- Build the app using maven command "mvn clean install" or "mvn clean install -DskipTests" to skip tests
4- To setup first user, either run the test case placed under AuthControllerTests or through auth/signup API
5- React App is placed under parkson-react folder and set it up through following steps:
	i- npm install
	ii- npm run build
	iii- npm start (optional in case you want to run it seperately)

6- App is accessed http://localhost:8080/ or http://localhost:8080/parkson/ depending on how you set up the server

Please have a look at the code for more info.

There are still couple of things left and due to lack of time, I had to compromise on certain standards. I deliberately did not use some of the proposed technologies like jsp or jsf for views. Despite I am pretty much familiar with them but I chose to use latest technologies since I wanted to try them out too. 
There are certain better alternate like using Spring JdbcTemplate instead of Spring Data. Just to save time, I went with Spring Data. I could also add things like Swagger documentation for APIs but due to lack of time I could not.








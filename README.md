# Project Title
**Login-and-SignUp-Implementation-using-JWT**

# Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Authorization with Spring Security
* Customized access denied handling

# Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven

# Getting Started
**To get started with this project, you will need to have the following installed on your local machine:** 
* JDK 17+
* Maven 3+

**To build and run the project, follow these steps:**

* Clone the repository: git clone https://github.com/BibekPrasadYadav/Login-and-SignUp-Implementation-using-JWT.git
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run
-> The application will be available at http://localhost:8080.


# Implementation and API
## For SignUP
### POST Method : http://localhost:8080/api/v1/auth/signup
* Provide the json data in body
  {
  "firstname":"",
  "lastname":"",
  "email":"",
  "password":""
}
## For Login 
### POST Method : http://localhost:8080/api/v1/auth/login
* Provide the json data in body
  {
  "email":"",
  "password":""
  }
## You will receive the token and Copy the token  
## For Demo 
### GET Method: http://localhost:8080/api/v1/demo-controller
* Go To the Authorization -> Choose the Bearer Token -> Paste the token received from login in Token field
* Then it will authenticate and print. 







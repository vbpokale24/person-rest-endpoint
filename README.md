#Project Title:: rest-api
 This is a Spring Boot REST API application for CRUD operation.This project consist of three maven module(Resources,Service and persistence). For maven clean build, please always use **org-Parent** module.If you are using STS/eclipse to run the project, please run the Application.java class as spring boot application.Or you can follow the step given in Getting started and installment.

#Prerequisites
JDK 8
Maven
STS[Version3.8.4](optional)

#**Getting Started and Installment**
Copy this project folder in your workspace or you can clone by using <<URL>>

Make sure your workspace is JDK/JRE Enabled.

Do maven build on parent folder.

**For maven build.** \
a)Go to cmd ->go to folder_name of project\
b)Type mvn clean install or mvn install \
 note::(Need to set maven home first if installing mvn)
c)Go to resources module target folder \
d)type java -jar resources-0.0.1-SNAPSHOT.jar note:(Check the execute permission)\

Your development server is ready on the console.

**Another way to generate jar.** \
a)import project in IDE. \
b)right click on project(org-parent)->Run as->maven build -> in Goals type clean install. \
c)Now jar is generated in target folder of resources module.\
 


#Deployment \
To deploy the project on the production, Follow same run rules mentioned above.


#**API Description**

 This Api is secured by **JWT**.To access the api, user need to pass the Authotization header in request.\
 **Import the postman collection name embl-rest-Api.postman_collection.json in postman for getting all API in postman.Json collection   present in project itself.** \
 

##1)Get the authorization token API:
  Url::http://localhost:8080/securitymanagement/v1/getJwtToken
  Method Type :: GET
  Header Parameter:: 
      1)Content-Type : application/json
	  2)Basic		 : c2VjdXJpdHlBZG1pbg==
  Response ::
       ```json
       {
  "subjectToken": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTgwMTQwMDU3LCJzdWIiOiJBdXRob3JpemF0aW9uIiwiaXNzIjoiQWRtaW4iLCJleHAiOjE1ODAxNDM1MTR9.GybmBJIovQi7HAR5MeooI5cbrnDMg5_q7fRgYMTPAdo"
      }
      ```
  ###Note: above token will be used to access the API.If token gets expired, user needs to generate the new token.

##2)Create the persons API::
  Url::http://localhost:8080/personmanagement/v1/person
  Method Type:: POST
  Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
  Request Payload:: 
    ```json
    {
	"person": [
    {
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
				"Foorball"
      ]
		},
		{
      "firstName": "Bajirao",
      "lastName": "Pokale",
      "age": "30",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
				"Foorball"
			]
		}
	]
	}
	```
  
   Response Payload:: 
    ```json
      {
  "person": [
    {
      "id": 1,
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
      ]
    },
    {
      "id": 2,
      "firstName": "Bajirao",
      "lastName": "Pokale",
      "age": "30",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
				]
		}
	]
	}
	```

##3) Get all person API:
   Url::  http://localhost:8080/personmanagement/v1/person
   Method Type:: GET
   Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
   Response Payload::
     {
  "person": [
    {
      "id": 1,
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
      ]
    },
    {
      "id": 2,
      "firstName": "Bajirao",
      "lastName": "Pokale",
      "age": "30",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
				]
		}
	]
	}
	
##4) Get person against person_id API::	
    Url::http://localhost:8080/personmanagement/v1/person/1
	Method Type:: GET
    Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
    
	Response Payload::
   {
  "person": [
    {
      "id": 1,
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "Vaibhav",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
			]
		}
	]
	}

## 5) Patch the person data API::
    Url:: http://localhost:8080/personmanagement/v1/person/1
	Method Type:: Patch
    Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
	
	Request Payload::
		{
	"person": [
    {
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
				"Foorball"
      ]
    }
  ]
  }
  
  Response Payload:: 
  {
  "person": [
    {
      "id": 1,
      "firstName": "Vaibhav",
      "lastName": "Pokale",
      "age": "29",
      "favouriteColor": "Blue",
      "hobby": [
        "Chess",
        "Cricket",
        "Foorball"
      ]
    }
  ]
}

##6) Delete the person against the person_id API::
    Url:: http://localhost:8080/personmanagement/v1/person/1
	Method Type:: Delete
    Header Parameter:: 
      1)Content-Type : application/json
	  2)Authorization		 : << Above api token generated i.e. subjectToken >>
	
	Response Body:: HTTP STATUS code 204.

#**Testing**
 1) At parent project, postman collection is present.Please import that collection into postman.\
 2) RestAssured Testing::\
    a) CT(component testing)is written for this project. \
    b)To generate the CT report, please run the ExecuteAPITester.java class. It is present in resource module test. This class wil generate the report.(Please make sure service is up and running before running the class)\
 3) Junit is written for this project in resource module.\	
	   

Built With
[CMD] Command Client
Maven - Dependency Management
Versioning
1.0.0

Authors
Vaibhav Pokale

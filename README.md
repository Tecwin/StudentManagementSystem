 StudentManagementSystem

This project is based on the concept of student management system and is currently under development.Development is done using Java Spring Boot and Hibernate , it includes spring security and there are API designed which are secured based on roles in the system like Admin, Professor and Student.Also implemented Swagger , Liquibase and H2 database 

SWAGGER Screencshort
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/861527f7-d854-473b-b059-b7a9ae96773c)

H2-Console Screenshot
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/37a3110e-a7f4-4b4e-9b40-8ffea96c013b)

API to Save a Student (executed using swagger-ui)
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/97f8cba9-4136-4e5b-833c-789055f41236)

Screenshot of student saved in H2 database , the password is encrypted and stored 
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/2f50c052-3da8-4863-b376-b6f9462a99cd)

API to Save a Professor(executed using Postman)
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/7ada0737-6f60-4072-ad95-b531264eb68f)

Screenshot of professor saved in H2 database
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/ef09ac25-69e0-4791-8c59-10655bdbbffc)

Screenshot Login API which provides the token which will be used for authorization 
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/ab7e3d08-03dc-43f8-b1ff-ed583d8e1990)

Delete Professor API is accessible only to user with ADMIN role , if any other user tries to use the API 401 unauthorized response will be retured due to Spring security
Screenshot when User with Student Role tries to use the Delete professor API
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/28ebc93c-ac96-42a1-88b7-e97498e8d458)

Scrrenshort when User with ADMIN role tries to use the Delete professor API , it returns 200 code and the professor is deleted successfully
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/228c268b-2863-4365-be8e-cdadda2be12a)

Screenshot of H2 Database showing that the professor is deleted 
![image](https://github.com/Tecwin/StudentManagementSystem/assets/49346742/6b73a9a9-160a-4ce4-8224-73ea7a3e595c)


In this project my  aim was to use spring boot and hibernate to  design API's , by including  the concepts of spring security,Swagger,H2 database,liquibase.

Thank You.


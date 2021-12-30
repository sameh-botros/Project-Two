# Project-Two

Reimbursement request project
===
Project Description
---
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can log in and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement and the system automatically sends an email to the employee when the manager approves or rejects his request.

## Features

As a Employee:
---
1.	Can login and logout
2.	Can view home page
3.	Can submit a reimbursement request
4.	Can view their pending reimbursement requests
5.	Can view their resolved  reimbursement requests
6.	Can View their information
7.	Can update their information
8.	Can reset their password

As a Manager: 
-----
1.	Can login and logout
2.	Can view home page
3.	Can approve / reject pending reimbursement requests and send email after
4.	Can view their pending reimbursement requests of all employee
5.	Can view their resolved  reimbursement requests of all employee
6.	Can view reimbursement requests of a specific employee 
7.	Can view all employee
8.	Can register an employee 

Technologies Used  
---
Java, Maven, Spring Boot, Spring Data JPA, Logging, Ajax, JDBC ,PostgreSQL, JUnit, HTML, CSS, Angular

Environmentes  
---
Eclipse IDE and Visual Studio

Getting Started
---
How to install the Project

Open the GitHub Link [github](https://github.com/SamehBotros/Project-One.git) and then extract all folders
*  For Angular 
1.	Open the CLI or Terminal and choose the angular folder
2.	Write the command  ` npm install `
3.	After the installation write the command > ng serve
*  For Java : 
1.	Open the folder on your IDE
2.	To receive an email from `src/main/resources` open `application.properties` file and add your email `spring.mail.username=xxxx@gmail.com` and to genarate app password open the link  https://myaccount.google.com/security go to Signing in to Google and choose App passwords enter your gmail password then press the select app and choose other to enter your app password any name you want then press generate and copy the app password do to  `application.properties` past in `spring.mail.password=xxxxxx` 
3.	Open file `RequestControlerMain.java`
4.	Run the Javalin 4 server
*  For PostgreSQL :
5.	From the Java folder open the file dbscript.sql on any editor 
6.	Open the Postgres CLI and create database name `reimbursement`
7.	Inside the reimbursement start copy and past from file dbscript.sql file as the same order

Usage
---
To use the project you 
1.	From browser open the link http://localhost:4200/ or add your port number
2.	Chooser Login from the menu bar to enter as Employee (user name = sam , password =123) and as Manager(user name = joe , password =456)
3.	Enter as an employee to submit requests, view  your pending requests, view your resolved request, view your profile and edit your profile  
4.	Enter as a manager so can see all pending requests, all resolved request, specific employee history, you can approve or reject requests, see all the employee information and make rejestration for an employee


Contributors
-----
Worked in group of two developers I did 80% of the project all backend exept the upload image and most of the frontend exept the upload image, Jasmine and Karma test 

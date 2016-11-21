COMPARATOR RMI SERVICE
----------------------

Student: Ciaran Whyte
Student Id: G00254624
College: Galway-Mayo Institute of Technology
Course: Software Development Year 4
Module: Distributed Systems
Professor: Dr John Healy

INTRODUCTION
----------------------
The Comparator RMI service is an application written using Java RMI technology. Its purpose is to compare two string using a selection of different algorithms. The application is
built as part of a distributed systems project and shows an example of how an asynchronous service can be built by queuing requests. Threads can also be used in this process but in
purposely kept this to a minimum so the user could see the application working a little slower thus getting a feel for whats happening.

TECHNICAL SUMMARY AND SUBMISSION INFORMATION
----------------------

The JSP Page
----------------------
The application consists of a single JSP page (Java Server Page). This essentially provides the view of the application and consists mainly of HTML. This page came as part
the starter code for the project and was written by John Healy. I have included it in the submission even though i think the only code needed is the Java source code i wrote, 
nevertheless i included it anyway.

The WEB-INF File (Main Source Code)
----------------
The main source code of the project are in this folder. It contains the following:
Web.xml - This is a deployment descriptor for a Servlet-based Java web application. Can be used to map url paths to be taken care of by certain Java classes (servlets).
lib folder - This folder contains any .jar files needed by the application. In my instance i included one jar which has libraries for certain string comparision algorithms.

classes folder - This contains the main code of the application and consists of five packages, these are as follows:
ie.gmit.middleService - This package contains one class called ServiceHandler and it is bassically a http servlet which handles posts/gets from the .jsp page.
ie.gmit.queueModels - This package contains any classes and interfaces that were used to create the in-queue of the application. This queue is created by a factory class
					  and is responable for holding all incoming string tasks from the client that await processing. This package also contains the StringTask class which
					  is the object type the in-queue is composed of.
ie.gmit.rmiModels - This package consists of anything RMI service related. It contains the Resultator and StringService interfaces and both their implementations which are 
					well commented. There are also two runnables, one for the client and one for the string compare service. This allows the client to constantly run in its
					own thread and for the string service to return the resultator while it also processes the strings and updates that same resultator.
ie.gmit.stringAlgos - Contains string algorithm classes, three are provided with the starter code and the other is from github. The other algorithms are in an imported .jar 
					  that is found in the lib folder mentioned earlier.
ie.gmit.sw - Lastly this package contains one class which is the RMI server.

The comparator.war file
-----------------------
This is the deployment file to be ran on a tomcat server and contains all the source code of the application.

The string-service.jar
----------------------
This is a runnable .jar file which runs the RMI server

RUNNING THE APPPLICATION
------------------------
Now....to the fun stuff. ;) Lets run this thing. The instuctions are simple and as follows:

1. Navigate to the directory that the string-service.jar file is located in and run the following command in the terminal: java –cp ./string-service.jar ie.gmit.sw.Servant
   The RMI server is now running.
2. Install a Tomcat server if you dont already have one (preferably at least v8.0) ...then start it up. 
3. Navigate to localhost:8080 in your browser
4. Click on the "Manager App" button on the top right of the page. You will be asked for login permissions. If you dont have any permissions set already then open the tomcat-users.xml
   file located inside: where_tomcat_installed/tomcat/conf ....then add this between the <tomcat-users></tomcat-users> tags.
   <role rolename="manager-gui"/>
   <user username="user1" password="pass1" roles="manager-gui"/>
   .....Save the file and restart the server. You now have a username and password of user1 and pass1.
5. Once inside the "Manager App" area scroll down to the "WAR file to deploy" section. Choose the Comparator.war file from your computer and then click "Deploy"
6. If you now scroll back up you will now see /Comparator in your list of applications. Click on /Comparator and you will be navigated to the page.
7. You can now use the application. Enjoy :) ...Thanks for the module John, interesting stuff.





## Techolution Assignment-2

HashMap as a Service :
 
 Write a service broker for cloud foundry that provides a HashMap to be used by CF applications.
Once the service broker is registered on CF any application developed for CF should be able to use the service.
More information about CF services brokers, see here: https://docs.cloudfoundry.org/services/api.html.
 
 
In addtion, the hashmap that supports the service should not be based on any Map implementation provided by the Java 8 language.
You must to write your own Hashmap as part of this exercise.
 
To have higher scores, please write tests for all your code and provide a maven based project where I can easily compile and deploy your service broker.
 
Please, also provide a CF client application that I can use for testing your service broker.
 
Instructions on how to the deployment should also be provided.
 
Note:
1.       Make sure to follow TDD Approach for the Test.
2.       Follow Standard Coding Practices( Naming Conventions, Class/Method/In-line Comments etc..)
3.       Time given for the test to complete is 1 day.
4.       Share the source code solution using Git Hub Link.
5.       Brief the approach you took to solve the problem and how you achieved the end result.
6.       Provide a CF client application for testing the Service Broker that you built.


### Build and run


#### Prerequisites

- Java 8
- Maven > 3.0

#### Using the terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

### Steps to deploy using CF commandline

$ cf push -m 512m -p C:\Users\GULAPALLY\Documents\workspace-sts-3.8.1.RELEASE\techolution-test\techolution-test\custom-map-service-broker\target\custom-map-service-broker-0.0.1-SNAPSHOT.jar customhashmap-broker

$ cf create-service-broker customhashmap-broker scott tiger https://customhashmap-broker.cfapps.io --space-scoped

$ cf create-service CustomHashMap basic CustomHashMap1

$ cf bind-service customhashmap-broker CustomHashMap1

$ cf create-service-key CustomHashMap1 CustomHashMapKeys

$ cf service-key CustomHashMap1 CustomHashMapKeys

{
 "password": "tiger",
 "uri": "http://customhashmap-broker.cfapps.io/customhashmap/420a5027-41b7-47c3-9410-8b8ce57231cc",
 "username": "scott"
}

Restart the service
<!--cf restage customhashmap-broker -->


$ cf push -m 512m -p C:\Users\GULAPALLY\Documents\workspace-sts-3.8.1.RELEASE\techolution-test\techolution-test\custom-map-service-broker-client\target\custom-map-service-broker-client-0.0.1-SNAPSHOT.jar customhashmap-broker-client



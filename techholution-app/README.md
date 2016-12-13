## Techolution Assignment

Gordon Ramsey, a very smart guy, like seating food. Now, Gordon is at a restaurant and he has many different types of food to choose from. Gordon gets x amount of satisfaction and requires y amount of time to eat an item from the menu. Given t minutes, write a java program that reads the text file and ﬁnds out the maximum satisfaction that Gordon can get from eating at the restaurant. You will be given a text file with the following format:

{<t><Number of items on menu>}
{<amount of satisfaction from eating dish 1><time taken for dish 1>}
{<amount of satisfaction from dish 2><time taken for dish 2>}
 

Notes :
 
1.             Make sure to setup Spring Boot Application & follow TDD Approach for the Assignment.
2.             Follow Standard Coding Practices( Naming Conventions, Class/Method/In-line Comments etc..)
3.             Share the source code solution using Git Hub Link.
4.             Brief the approach you took to solve the problem and how you achieved the end result.
5.             Provide Maximum Satisfaction Value you achieved in your response.


### Build and run


#### Prerequisites

- Java 8
- Maven > 3.0

#### Using the terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.

### Solution

#### Assumptions
Considered max weight in minutes and also time taken to eat the dish is also in minutes, hence no conversion has been done to the time.

#### Approach
- I have implemented the knapsack alogorithm here to solve this problem, as knapsack alogorithm helps to maximize the sum of the values of the items in the knapsack so that the sum of the weights is less than or equal to the knapsack's capacity. 

- As Current problem is also to maximise the satisfaction values for the provided time. Hence considered "satisfaction value" as value, and "time taken to eat dish" as weight. 10000 minutes is considered as maximium weight.

#### Output
The satisfaction value using this approach comes out to be 2493893

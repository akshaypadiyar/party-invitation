# Party Invitation

List all customers within 100kms for an event

## Development environment

Project was built using following tools & framework

* **Java** (version 16 : latest)
* **Spring Boot** (version 2.4.5 : latest)
* Maven (3.6.3)
* IntelliJ Community Edition 2020.3.x

## Minimum system requirements

### Java
Run '*java --version*' to check version of java installed. Version should be >v14.

> java --version<br>
java 16 2021-03-16<br>
Java(TM) SE Runtime Environment (build 16+36-2231)<br>
Java HotSpot(TM) 64-Bit Server VM (build 16+36-2231, mixed mode, sharing)<br>

If not installed, it can downloaded from [here](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html)

Once installed add JAVA_HOME environment variable pointing to installation home directory and %JAVA_HOME%\bin to PATH.

### Maven
Run '*mvn --version*' to check version of maven installed. Version should be >v3.5.  

If not installed, it can be downloaded from [here](https://maven.apache.org/download.cgi)

Once installed add M2_HOME environment variable pointing to installation home directory and %M2_HOME%\bin to PATH

> mvn --version <br>
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)  
Maven home: C:\Tools\Maven\3.8.1\bin\..  
Java version: 16, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-16  
Default locale: en_IN, platform encoding: Cp1252  
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

## Execution Steps

### Run the application

#### Option 1: Console Mode
1. Navigate to project root (pom.xml) in terminal
2. Run '**mvn install spring-boot:run -P console**'
3. Output is generated in **output.json** file.

#### Option 2: REST endpoint
1. Navigate to project root (pom.xml) in terminal
2. Run '**mvn install spring-boot:run -P web**'
3. Open http://localhost:9090/invitees in Chrome / Firefox

### Run unit tests
1. Navigate to project root (pom.xml) in terminal
2. Run '**mvn test -P console**'

> Note: Above steps can also be performed in IntelliJ (or any IDE supporting Java+Maven)

## Output
When running in console mode, the output is generated in **\<project_dir\>/output.json**.
>A sample output with default configuration.  
(All customer names & ID within 100kms of given location, sorted by customer id ascending order)

## Output

```
[
  {
    "user_id": 4,
    "name": "Ian Kehoe"
  },
  {
    "user_id": 5,
    "name": "Nora Dempsey"
  },
  {
    "user_id": 6,
    "name": "Theresa Enright"
  },
  {
    "user_id": 8,
    "name": "Eoin Ahearn"
  },
  {
    "user_id": 11,
    "name": "Richard Finnegan"
  },
  {
    "user_id": 12,
    "name": "Christina McArdle"
  },
  {
    "user_id": 13,
    "name": "Olive Ahearn"
  },
  {
    "user_id": 15,
    "name": "Michael Ahearn"
  },
  {
    "user_id": 17,
    "name": "Patricia Cahill"
  },
  {
    "user_id": 23,
    "name": "Eoin Gallagher"
  },
  {
    "user_id": 24,
    "name": "Rose Enright"
  },
  {
    "user_id": 26,
    "name": "Stephen McArdle"
  },
  {
    "user_id": 29,
    "name": "Oliver Ahearn"
  },
  {
    "user_id": 30,
    "name": "Nick Enright"
  },
  {
    "user_id": 31,
    "name": "Alan Behan"
  },
  {
    "user_id": 39,
    "name": "Lisa Ahearn"
  }
]
```

## Playground (Optional - Not part of requirements)
Some behaviours are made configurable. One can play around by modifying them before running application.
Configuration can be found at *\<projectdir\>/src/main/resources/application.yml*

### Full Configuration

Below is the configuration file for the application along with default values

```
application:
  measurement:
    units: kms #Measurement units [Values: kms (default) / miles]

server:
  port: 9090 #Server port (only used when application is run in web mode)

customers:
  data:
    source: data/customers.txt #Data source for all customers

  sorting:
    strategy: user-id #Sort strategy [Values: user-id (default) / name / proximity]
    reversed: false #Reverse the direction of sorting. [Values: true / false (default - ascending)]

  filtering:
    location:
      range: 100 #Filter distance from event location

event:
  location:
    latitude: 53.339428 #Latitude of event location
    longitude: -6.257664 #Longitude of event location

```


#### Filter Radius
Filter radius can be updated by changing '**range**' config. *(default: 100)*.
*Note: Negative numbers are updated to 0.*

#### Party Location
Base / Event location can be changed by updating '**latitude**' and '**longitude**' accordingly. *(default: latitude = 53.339428 and longitude = -6.257664)*

#### Sorting
As per requirements, default sorting is based on customer ID. This can be changed by updated **strategy** to any of below values *(default: user-id)*:


| Sorting | Behaviour | 
| -------- | -------- |
| user-id (default)     | Sorts by customer ID (ASC) |
| name | Name of the customer (ASC) |
| proximity | Sorts by how distant is customer from event location |


> Note: Sorting order can be toggled by setting '*reversed*' to 'true' for descending order. *(default: false)*

#### Customer List
List of customers can loading from *\<project_dir\>/src/main/resources/data/customers.txt* by default. This can be changed to another file name in same location.

#### Measurement Units
Distance unit can be changed between kilometres and miles. This can be done by updating 'units' to either 'kms' or 'miles'. *(default: kms)*.

### Overriding default values without changing config file

To run application without changing config yml file, you can append below

Command
> mvn install spring-boot:run -P console -Dspring-boot.run.arguments="--customers.sorting.strategy=proximity"

* Multiple arguments can be provided separated by space.

Some sample arguments:  
"--customers.sorting.strategy=name --customers.sorting.reversed=true"  
--customers.filtering.location.range=200  
--event.location.latitude=50.121  
--application.measurement.units=miles


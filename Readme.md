# Automation Test Framework

### Prerequisites

* Installed [Java](https://www.java.com/download/) JDK version 9+ 
* Make sure environment path to Java JDK is set as JAVA_HOME
* Download and unpack [Maven](https://maven.apache.org/download.cgi)
* Set environment path to Maven bin as MAVEN_HOME
* Installed GIT available in command line
* Installed newest version of Chrome browser 

### Downloading project
* Create new folder where you wish to keep the project
* Navigate to folder using `cd <folder address>` in command line
* Run git command 
 
```
 git clone https://github.com/vofj123/srcCrp_recruitation.git
```

### Running



* Move to directory with downloaded and unpacked project
* Execute `runner.bat` file 
 or:
* Run command line in project directory
* Starting Maven and building project at desired location with following command:

```sh
$ mvn clean test 
```

### Results

* Results are saved as html file and can be view in `test_output` directory inside project

>> @ Damian Łęgowski
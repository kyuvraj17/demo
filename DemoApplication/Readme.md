Demo Application
=================================================


Table of contents
-----------------

* [Introduction](#introduction)
* [Installation](#installation)
* [Usage](#usage)
* [Known issues and limitations](#known-issues-and-limitations)
* [License](#license)
* [Authors and history](#authors-and-history)



Introduction
------------
This application will provide the feature's of a person. By using this application we can perform the curd operations like save, get, update and delete.


Installation
------------
1. Required java 1.8 and above
2. Required Maven 3.x and above
3. Run the below commands
```bash
> mvn clean install
> java -jar  target/<name file the file>.jar
```


Usage
-----

This section explains the principles behind this README file.  If this repository were for actual _software_, this [Usage](#usage) section would explain more about how to run the software, what kind of output or behavior to expect, and so on.

### This application will perform the curd operations.

1. Create Operation: This Api will save the data of a person 
URL: http://locathost:8080/person
Method: POST 
Content-Type: application/json 
<b>RequestBody</b>: 
{
	
	"firstName": "Virat",
	"surname": "Kholi"
}

<b>Response</b>: 
{
    "person": {
        "id": 1,
        "firstName": "Virat",
        "surname": "Kholi"
    }
}

2. Update Operation: This Api will modify the existing resource
URL: http://locathost:8080/person/{id}
Method: PUT
<b>RequestBody</b>: {
	
	"firstName": "Rohit",
	"surname": "Sharma"
}

<b>Response</b>: 
{
    "person": {
        "id": 1,
        "firstName": "Rohit",
        "surname": "Rohit"
    }
}

3. Get Operation: This Api will return the available persons list
URL: http://locathost:8080/person
Method: GET
<b>Response</b>:
{
    "persons": [
        {
            "id": 1,
            "firstName": "Rohit",
            "surname": "Sharma"
        }
    ]
}

4. Count Operation: Get the counts of records
URL: http://locathost:8080/person/count
Method: GET
<b>Response</b>:
{
    "count": 1
}

5. Delete Operation: This Api will delete a person from the system
URL: http://locathost:8080/person/{id}
Method: DELETE
<b>Response</b>:
No content with 204 status code


Known issues and limitations
----------------------------

None are known at this time.


License
-------
Copyright @demoapplication.com

Authors
---------------------------
Author: Raju

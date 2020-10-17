# sms-group 

This project is an implementation of the assignment given by sms-group.

## Pre-requisites:
* [Docker](https://docs.docker.com/engine/install/) and [docker-compose](https://docs.docker.com/compose/install/) should be install in your system.

## System Compatibility
This assignment can run on:
* Linux
* MacOs
* Windows

## Usage

```
git clone https://github.com/ravinamotwani11/sms-group
cd sms-group/userProfile
mvn clean install -DskipTests=true
cd ..
docker-compose up
```

## Load data to postgres database
Before going to run web UI and performing CRUD operation, user must load data into the database.  
Use POST method and hit the following URL.  
http://localhost:8010/sms-group/insertJSON  
For example, open postman=> select POST method => type the above URL=> hit send button=> You will get success response

## web UI
Open the following link from your web browser.  
```
http://localhost:3000
```

# CRUD
To fetch all data, hit the following URL.  
method: GET  
URL: http://localhost:8010/sms-group/fetchCities  

To fetch data by id, hit the following URL.  
method: GET  
URL: http://localhost:8010/sms-group/fetchCities?id=<id>  

To add new record, hit the following URL.  
method: POST  
URL: http://localhost:8010/sms-group/add  
Payload/Body: [{  
"id": "1001",  
"city": "Tangxi",  
"status": "Seldom",  
"start_date": "10/16/2020",  
"end_date": "10/17/2020",  
"color": "#7ed096",  
"price": "97.35"  
}
] 
  
  
To update existing record, hit the following URL.  
method: PUT  
URL: http://localhost:8010/sms-group/update   
Payload/Body: [{  
"id": "1001",  
"city": "Tangxi",  
"status": "Seldom",  
"start_date": "10/16/2020",  
"end_date": "10/17/2020",  
"color": "#7ed096",  
"price": "98.35"  
}
]  
  
To delete any record, hit the following URL.  
method: DELETE  
URL: http://localhost:8010/sms-group/delete  
Payload/Body: {  
"id": 1001  
}
 


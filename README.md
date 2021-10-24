# Lab-App

## Table of contents

* [Background](#background)
* [Solution](#solution)
* [Setup](#setup)
* [Documentation](#documentation)
* [Usage](#usage)
* [Examples](#examples)
* [Improvements](#improvements)

## Background

Scientists need a digital solution to store and identify various items used in their experiments.
These items come in different forms and might include samples, chemicals, devices, etc...

A usual practice in laboratories is to store the items in categories. Each category has attribute definitions and each item in those categories should fit into those definitions. You can imagine categories as database tables while attribute definitions are columns. Items are, in this case, the rows in those database tables.

## Solution 

Lab-App is a Spring Boot REST API application which provides the following functionality:

* Creating categories with attribute definitions
* Creating items in those categories
* Updating items
* Getting items of a category

The application uses an in-memory database, please be aware that the data will be lost on every restart.

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

## Setup

Clone this repo to your drive:

```bash
git clone https://github.com/nfgc00/lab-app.git
cd lab-app
```

And run the application with Maven:

```bash
.\mvnw spring-boot:run
```

Or you can run the application as a Stand-Alone application:

```bash
.\mvnw clean package
java -jar target/lab-0.0.1-SNAPSHOT.jar
```

The application will start on the port 8080 by default, but if the port is used, you can change it to an available port, you can provide the port with the following command, in this case the application will start on the port 8083, please provide your prefered port:

```bash
java -jar target/lab-0.0.1-SNAPSHOT.jar --server.port=8083
```

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

## Documentation

You can see the documetation of the API on your browser, [please click here](http://localhost:8080/swagger-ui.html), you'll see the following screen where you can review how the API works:

![api-docs](https://i.ibb.co/kMpcV8c/swagger-doc.png)

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

## Usage

Postman Collection to test the REST Interface:

[Postman Collection](lab-app.postman_collection.json) 

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

## Examples

* Create Category with attributes: POST Request to [http://localhost:8080/categories](http://localhost:8080/categories) passing the body:

```json
{
    "name": "Devices",
    "attributes": [
        {
            "name": "NAME"
        },
        {
            "name": "PRICE"
        },
        {
            "name": "MANUFACTURER"
        }
    ]
}
```

* Get Category and its attributes: GET Request to: [http://localhost:8080/categories/5](http://localhost:8080/categories/5), you will get the Category and Attributes details:

```json
{
    "id": 5,
    "name": "Devices",
    "attributes": [
        {
            "id": 6,
            "name": "NAME"
        },
        {
            "id": 7,
            "name": "PRICE"
        },
        {
            "id": 8,
            "name": "MANUFACTURER"
        }
    ]
}
```

* Create Item into a Category: POST Request to: [http://localhost:8080/items](http://localhost:8080/items) passing the body:

```json
{
    "categoryId": 5,
    "attributesValues": [
        {
            "attributeId": 6,
            "value": "Monitor"
        },
        {
            "attributeId": 7,
            "value": "500"
        },
        {
            "attributeId": 8,
            "value": "LG"
        }
    ]
}
```

* Get Category Items: GET Request to: [http://localhost:8080/categories/5/items](http://localhost:8080/categories/5/items)

```json
[
    {
        "id": 9,
        "categoryId": 5,
        "attributesValues": [
            {
                "id": 10,
                "value": "Monitor",
                "attributeId": 6
            },
            {
                "id": 11,
                "value": "500",
                "attributeId": 7
            },
            {
                "id": 12,
                "value": "LG",
                "attributeId": 8
            }
        ]
    }
]
```

* Update an Item: PUT Request to: [http://localhost:8080/items/9](http://localhost:8080/items/9) passing the new values:

```json
{
    "attributesValues": [
        {
            "attributeId": 6,
            "value": "Ultra Wide Monitor"
        },
        {
            "attributeId": 7,
            "value": "700"
        },
        {
            "attributeId": 8,
            "value": "Samsung"
        }
    ]
}
```

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

## Improvements

There is a lot of room for improvement, some points are:

* Validations
* Handle response errors
* Loggin
* Use another database
* Add Tests
* Security
* App Containerization

<div align="right">
    <b><a href="#lab-app">↥ back to top</a></b>
</div>

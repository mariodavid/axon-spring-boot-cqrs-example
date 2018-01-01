## Spring Boot Axon Example - Policy

This example uses [Axon](axonframework.org) together with Spring Boot to create
an API for creating, updating and cancelling policies.

It uses the CQRS architecture pattern as well as event sourcing to store the whole
history of changes that have taken place over the lifetime of the application.


## Using the API

Here are some example on how to consuming the API through [HTTPie](https://httpie.org/):

#### Create a policy

````
$ http POST localhost:8080/policies

HTTP/1.1 200 
Content-Type: text/plain;charset=UTF-8

85835917-7476-42fd-b759-485eeed48751

````

#### Cancel a policy
````
$ http POST localhost:8080/policies/85835917-7476-42fd-b759-485eeed48751/cancellation cancelDate=2018-05-01
````

#### Retrieving a policy in its latest state
````
$ http localhost:8080/policies/85835917-7476-42fd-b759-485eeed48751  
                                 
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8

{
    "apartmentSize": 0,
    "coverEndDate": "2018-05-01",
    "coverStartDate": "2018-01-01",
    "id": "85835917-7476-42fd-b759-485eeed48751",
    "state": "CANCELED"
}
````
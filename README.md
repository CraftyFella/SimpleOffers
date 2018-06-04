# SimpleOffers

## Required tools

  - Java JDK 8
  
## Build (Gradle)

the project makes use of gradle. YOu can build it from the command line using the following:

Linux:

    ./gradlew clean build
    
## Run

    java -jar build/libs/simpleoffers-1.0-SNAPSHOT.jar
    
## Test

### create an offer

    curl -X POST \
    http://localhost:8080/offers \
    -H 'content-type: application/json' \
    -d '{ 
      "friendlyDescription" : "Daves offer",
      "amount" : { "value"  : "12.34", "currency" : "GBP" },
      "expiryDate" : "2019-04-25T08:02:52Z"
    }'
    
#### response
    status 200
    location /offers/1aee6d89-a770-429d-87ad-7a62e6f5f572
    
### query an offer

    curl -X GET http://localhost:8080/offers/1aee6d89-a770-429d-87ad-7a62e6f5f572
    
#### response
    status 200
    
### cancel an offer

    curl -X DELETE http://localhost:8080/offers/1aee6d89-a770-429d-87ad-7a62e6f5f572
    
#### response
    status 200

# SimpleOffers

## Required tools

  - Java JDK 8
  
## Build (Gradle)

the project makes use of gradle. You can build it from the command line using the following:

Linux:

    ./gradlew clean build
    
## Run

    java -jar build/libs/simpleoffers-1.0-SNAPSHOT.jar
    
## Test

The solution makes use of end to end tests. (my prefered approach) however if you want to test manaully here is a few curl commands you can run:

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

## Limitations

I ran out of time to start the links (HATEOS) work as you can see from the commit stamps.
As this mentions enterprise... I wouldnt normally create an api without metrics and tracing.

Also before adding the metrics etc.. I would start to move some of the logic in the controllers (which are simple enough at the momenet) out into handlers.

Do please ask me any questions if you have some... very happy to talk you through this.

Dave

# BackendTest

Create a REST service that takes IP addresses, checks which countries they are 
from and if there are any returns list of countries from the northern hemisphere. 


In order to get information about IP addresses please use 

## IP Vigilante API: https://www.ipvigilante.com/api-developer-docs/ ## 


Use latitude value to decide if IP address comes from a country from the northern hemisphere.

It must be positive number if the country come from northern hemisphere.

## To use the service

1. make update maven project 
2. build maven 
3. run testcases to make sure service is working fine 


# Requirements for the service


●The service has to be written in a JVM language of your choice

●	You can use any frameworks of your choice

●	The service should have ability to be started with a single Gradle or Maven command

●	The service should run on port 8888

# Requirements for the service API:

●	Service should expose one endpoint for a GET request

●	The endpoint should accept list of IP addresses passed as request parameters

●	The endpoint should accept at least 1 and maximum 5 ip addresses

●	If among the passed IP addresses there are addresses from countries from the northern hemisphere, service should return these country names.

●	Response should contain list of unique names (no repetitions of names) sorted alphabetically


## Example use case:Request:

curl 
```
"http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"
```

Response: 
```
{  
   "northcountries":[  
      "Colombia",
      "Japan",
      "United States"
   ]
}
```


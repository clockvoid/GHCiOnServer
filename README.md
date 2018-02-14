# GHCi on Server
[![Build Status](https://travis-ci.org/clockvoid/GHCiOnServer.svg?branch=master)](https://travis-ci.org/clockvoid/GHCiOnServer)

The Spring Boot Application for running GHCi on Tomcat Server

## Description
This program is a Java Servlet program to run GHCi on Tomcat Server powered by Spring Boot.

This program has a lot of problems especially about security. 
DO NOT INSTALL YOUR Tomcat YET.

## Installation
Building and installation is very easy. You can use Gradle.

```
git clone https://github.com/clockvoid/GHCiOnServer
cd GHCiOnServer
./gradlew build war
```

And install your war file in `$TOMCAT_HOME/webapps`!

## REST APIs
* `POST /ghci`

    You can send JSON describing programs in request body.
    The JSON must form as follows:
    ```
    {
      "type": (Int value),
      "sessionID": (String value describes sessionId),
      "program": (String value describes Haskell program)
    }
    ```
    Type is request type. 0 means standard request, 1 means first connection.
    When type is 1, the server makes session.
    
    This API returns a JSON as follows:
    ```
    {
      "type": (Int value),
      "status": (Int value describes HTTP Status code),
      "body": (String value describes message body)
    }
    ```
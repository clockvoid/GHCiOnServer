# GHCi on Server
[![Build Status](https://travis-ci.org/clockvoid/GHCiOnServer.svg?branch=master)](https://travis-ci.org/clockvoid/GHCiOnServer)

The Spring Boot Application for running GHCi on Tomcat Server

## Description
This program is a Java Servlet program to run GHCi on Tomcat Server powered by Spring Boot.

This program has a lot of problems especially about security. 
DO NOT INSTALL TO YOUR Tomcat YET.

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
      "type": 0,
      "sessionId": (String value describes sessionId),
      "program": (String value describes Haskell program)
    }
    ```
    
    This API returns a JSON as follows:
    ```
    {
      "type": (Int value),
      "body": (String value describes message body),
      "status": (String value describes HTTP Status)
    }
    ```
    
* `POST /createSession`

    When you connect the server at the first time, you have to send JSON like:
    ```
    {
       "type": 1,
       "sessionId": (String value that is able to any value),
       "program": (String value that is able to any value)
    }
    ```
    
    This API returns a JSON like:
    ```
    {
       "type": 1,
       "body": (String value means sessoinId),
       "status": (String value means HTTP status)
    }
    ```
# GHCi on Server
[![Build Status](https://travis-ci.org/clockvoid/GHCiOnServer.svg?branch=master)](https://travis-ci.org/clockvoid/GHCiOnServer)

The Spring Boot Application for running GHCi on Tomcat Server

It works on `clockvoid.tk/api`!

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

## APIs
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
    
* `POST /get_functions`

    This service has a functions to save functions.
    In program, you can use `let` keyword to register functions.
    To get these functions, this API is pretty better.

* `GET /create_session`

    When you connect the server at the first time, you have to send `GET /createSession`
    
    This API returns a JSON like:
    ```
    {
       "type": 1,
       "body": (String value means sessoinId),
       "status": (String value means HTTP status)
    }
    ```

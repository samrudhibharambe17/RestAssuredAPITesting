package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class PingRequest
{
    public static void main(String args[])
    {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/ping");

        //TC#1: To verify a simple health check endpoint to confirm
        // whether the API is up and running.
        r.when().get();
        r.then().statusCode(201);


    }

}

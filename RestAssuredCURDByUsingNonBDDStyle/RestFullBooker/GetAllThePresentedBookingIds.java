package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class GetAllThePresentedBookingIds
{
    public static void main(String args[])
    {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");

        Response response= r.when().get();
        System.out.println(response.asString());
        ValidatableResponse vr=response.then();

        //TC#1: status code should be 200
        vr.statusCode(200);

        //How to validate the response time of a request in Rest Assured?
        //1.time(matcher) - it verifies the response time in milliseconds with the matcher passed as
        // a parameter to the method.
        //2.time(matcher, time unit) - it verifies the response time with the
        // matcher and time unit is passed as parameters to the method.

        //TC#2: Response time should be less than 5000ms
       vr.time(Matchers.lessThan(5000L));

    }
}

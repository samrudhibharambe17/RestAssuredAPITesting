package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetBookingThroughID
{
    public static void main(String args[])
    {
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/4111");

        Response response=r.when().get();
        ValidatableResponse vr=response.then();
        System.out.println(response.asString());
       // vr.contentType(ContentType.JSON);
        vr.statusCode(200);
    }
}

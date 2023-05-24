package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class PostRequestCreateBookingID
{
    public static void main(String[] args)
    {
        //new created bookingId "bookingid": 4111,
        String payloadPostRequest="{\n" +
                "    \"firstname\" : \"Deepak\",\n" +
                "    \"lastname\" : \"Bharambe\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2021-12-10\",\n" +
                "        \"checkout\" : \"2022-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";
        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking");
        r.contentType(ContentType.JSON);
        r.body(payloadPostRequest);

        Response response=r.when().post();
        ValidatableResponse vr= response.then().log().all();

        vr.statusCode(200);

        vr.body("bookingid", Matchers.notNullValue());

    }
}

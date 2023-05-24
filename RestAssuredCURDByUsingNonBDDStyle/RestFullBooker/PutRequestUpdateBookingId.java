package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class PutRequestUpdateBookingId
{
    public static void main(String args[]) {

        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");

         //1. crate Auth for token using post request
        String paylodAuth="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(paylodAuth);

        Response response=r.when().post();
        ValidatableResponse vr= response.then().log().all();

        vr.statusCode(200);
        //Extract the created token
        String token=response.then().extract().path("token");
        System.out.println(token);

        //post request fo getting booking id
        String payloadPostRequest = "{\n" +
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


       r.basePath("booking");
       r.cookie("token",token);
        r.body(payloadPostRequest);

        response=r.post();
        vr= response.then().log().all();

        vr.statusCode(200);

        Integer bookingID=response.then().extract().path("bookingid");

        //put request

        String payloadPutRequest="{\n" +
                "    \"firstname\" : \"Samrudhi\",\n" +
                "    \"lastname\" : \"Bharambe\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        r.basePath("booking/"+bookingID);
        r.cookie("token",token);
        r.body(payloadPutRequest);

        response=r.put();
        vr=response.then().log().all();

        vr.statusCode(200);

        vr.body("firstname", Matchers.equalTo("Samrudhi"));

    }
}

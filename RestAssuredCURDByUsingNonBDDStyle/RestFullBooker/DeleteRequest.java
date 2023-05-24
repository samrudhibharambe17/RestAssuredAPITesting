package com.thetestingacadamey.RestAssuredCURDByUsingNonBDDStyle.RestFullBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

public class DeleteRequest
{
    public static void main(String args[])
    {
        //Make a post request to the /auth to get a token
        //Pass the token to put request
        //get id - 4566
        //Make a put request with token and validate teh response

        RequestSpecification r= RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");

        //Auth to get token through post request
        String payloadAuth="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        r.basePath("/auth");
        r.contentType(ContentType.JSON);
        r.body(payloadAuth);

        Response response=r.when().post();
        ValidatableResponse vr= response.then().log().all();

        //TC1: Status Code
        vr.statusCode(200);
        //Extract the token
        String token=response.then().extract().path("token");
        System.out.println(token);

        //post request for getting booking id
        String payloadPostRequest="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
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
                "    \"firstname\" : \"Deepak\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
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

        //TC1:
        vr.statusCode(200);

        //TC: validate updated first and last name
        vr.body("firstname", Matchers.equalTo("Deepak"));
        // vr.body("lastname",Matchers.equalTo("Bharambe"));

        //Delete request
        r.basePath("booking/"+bookingID);
        r.cookie("token",token);

        response=r.delete();
        vr=response.then().log().all();

        vr.statusCode(201);
    }
}

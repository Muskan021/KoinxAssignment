package com.app.KoinxAssignment;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.support.FileReader;
import io.restassured.response.Response;



public class Task1Post 
{
	static int id;
    
	@Test
	public static Response getPostResponse()  throws IOException{
		
		Random random = new Random();
		
		String payload="{\"coin1\": \"INR\",\"coin2\": \"USDT\",\"coin1Amount\": "+(random.nextInt(100-10)+10)+",\"coin2Amount\": "+(random.nextInt(100-10)+10)+"}";
		 
		Response response= RestAssured.
				
				given()
				.auth().none().urlEncodingEnabled(false)
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when().body(payload).post("https://x8ki-letl-twmt.n7.xano.io/api:gHPd8le5/transaction");
		
		System.out.println(response.asString());
		
		assertThat(response.prettyPrint(),matchesJsonSchemaInClasspath("resources/PostRepoSchema.json"));

		return response;
	
	}
	
	@Test
	public static int setID() throws IOException{
		id=getPostResponse().then().contentType(ContentType.JSON).extract().path("id");
		return id;
	}
}

package com.app.KoinxAssignment;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Task2Get {
	@Test
	public void getReq()  throws IOException{

	 int id= Task1Post.setID();
	 RestAssured.baseURI="https://api.github.com/";
		RestAssured.basePath="users/Muskan021/repos";
		
		Response response=RestAssured
				.given()
				.auth().none().urlEncodingEnabled(false)
				.header("Content-Type","application/json")
				.contentType(ContentType.JSON)
				.when().get("https://x8ki-letl-twmt.n7.xano.io/api:gHPd8le5/transaction/"+id+"");
		
		response.then().time(Matchers.lessThan(10000L));
		
		System.out.println(response.asString());
		
		assertThat(response.prettyPrint(),matchesJsonSchemaInClasspath("resources/GetRepoSchema.json"));

		int sentCoinAmount=response.then().contentType(ContentType.JSON).extract().path("sentCoinAmount");
		int receivedCoinAmount=response.then().contentType(ContentType.JSON).extract().path("receivedCoinAmount");
		float actualreceivedCoinMarketPrice=response.then().contentType(ContentType.JSON).extract().path("receivedCoinMarketPrice");
		float expectedreceivedCoinMarketPrice=(float)sentCoinAmount/receivedCoinAmount;
		
		assertEquals(actualreceivedCoinMarketPrice, expectedreceivedCoinMarketPrice,0.0000);
		
	}
}

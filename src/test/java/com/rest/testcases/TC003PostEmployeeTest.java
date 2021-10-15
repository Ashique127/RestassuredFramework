package com.rest.testcases;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003PostEmployeeTest {

	@Test(dataProvider = "empdataprodiver")

	public void PostData(String ename, String esal, String eage) {

		// Specify base URI
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();

		// Created data and send post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "ename");
		requestParams.put("salary", "esal");
		requestParams.put("age", "eage");
		httpRequest.header("Content-Type", "application/json");
		// Add request body in json format
		httpRequest.body(requestParams.toJSONString());
		// POST Request
		Response response = httpRequest.request(Method.POST, "/create");
		// Capture response body to perform validation
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" + responseBody);
		Assert.assertEquals(responseBody.contains("ename"), true);
		Assert.assertEquals(responseBody.contains("esal"), true);
		Assert.assertEquals(responseBody.contains("eage"), true);

		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@DataProvider(name = "empdataprodiver")
	String[][] getEmpData() {

		String empdata[][] = { { "Jui", "1120", "22" }, { "Kam", "1021", "24" }, { "Kanai", "1065", "29" } };
		return (empdata);

	}
}

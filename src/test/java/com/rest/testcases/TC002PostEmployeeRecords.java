package com.rest.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rest.testbase.TestBase;
import com.rest.utilities.UtilityRestAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002PostEmployeeRecords extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	String empName = UtilityRestAPI.empName();
	String empSal = UtilityRestAPI.empSal();
	String empAge = UtilityRestAPI.empAge();

	@BeforeClass
	public void createEmployee() throws Exception {
		logger.info("****Started TC002PostEmployeeRecords****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.request(Method.POST, "/create");
		Thread.sleep(2000);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("empName"), true);
		Assert.assertEquals(responseBody.contains("empSal"), true);
		Assert.assertEquals(responseBody.contains("empAge"), true);
	}

	@Test
	public void checkstatusCode() {
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkstatusLine() {
		String statusLine = response.statusLine();// Getting status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkcontentType() {
		String conetentType = response.header("Content-Type");
		Assert.assertEquals(conetentType, "application/json");
	}

	@Test
	public void checkcontentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@AfterTest
	public void teardown() {
		logger.info("**** TC002PostEmployeeRecords ***** ");

	}

}

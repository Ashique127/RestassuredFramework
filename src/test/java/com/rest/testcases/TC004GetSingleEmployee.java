package com.rest.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rest.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004GetSingleEmployee extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void getSingleempoyee() throws Exception {

		logger.info("****Started TC004GetSingleEmployee****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);
		Thread.sleep(2000);

	}

	@Test
	public void checkresponseBody() {
		logger.info("**** Checking Response Body ***** ");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("**** Checking status code ***** ");
		int statusCode = response.getStatusCode();// Getting status code
		logger.info("Status Code==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkResponseTime() {
		logger.info("**** Check ResponseTime ***** ");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime);
		Assert.assertTrue(responseTime < 8000);
	}

	@Test
	public void checkStatusLine() {
		logger.info("**** Checking status Line ***** ");
		String statusLine = response.statusLine();// Getting status Line
		logger.info("Status Line is==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkContentType() {
		logger.info("**** Checking Content Type ***** ");
		String conetentType = response.header("Content-Type");
		logger.info("Content type is==>" + conetentType);
		Assert.assertEquals(conetentType, "application/json");
	}

	@Test
	public void checkcontentEncoding() {
		logger.info("**** Checking content Encoding ***** ");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	public void checkcookies() {
		logger.info("**** Checking cookies ***** ");
		String cookie = response.getCookie("PHPSESSID");
	}

	@AfterTest
	public void teardown() {
		logger.info("**** Finsih TC004GetSingleEmployee ***** ");

	}

}

package com.rest.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rest.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001GetAllEmployees extends TestBase {

	@BeforeClass
	public void getAllempoyees() throws Exception {
		logger.info("****Started TC001GetAllEmployees****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(2000);
	}

	@Test
	public void checkResponseBody() {
		logger.info("**** Checking Response Body ***** ");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	public void checkstatusCode() {
		logger.info("**** Checking status code ***** ");
		int statusCode = response.getStatusCode();// Getting status code
		logger.info("Status Code==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkresponseTime() {
		logger.info("**** Check ResponseTime ***** ");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime);
		if (responseTime > 2000)
			logger.warn("Response Time is greater than 2000");
		Assert.assertTrue(responseTime < 10000);
	}

	@Test
	public void checkstatusLine() {
		logger.info("**** Checking status Line ***** ");
		String statusLine = response.statusLine();// Getting status Line
		logger.info("Status Line is==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	public void checkcontentType() {
		logger.info("**** Checking Content Type ***** ");
		String conetentType = response.header("Content-Type");
		logger.info("Content type is==>" + conetentType);
		Assert.assertEquals(conetentType, "application/json");
	}

//	@Test
//	public void checkserver() {
//		logger.info("**** Checking server Type ***** ");
//		String server = response.header("Server");
//		logger.info("Server is==>" + server);
//		Assert.assertEquals(server, "cloudflare");
//	}

	@Test
	public void checkcontentEncoding() {
		logger.info("**** Checking content Encoding ***** ");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

//	@Test
//	public void checkcontentLength() {
//		logger.info("**** Checking content Length ***** ");
//		String contentLength = response.header("Content-Encoding");
//		logger.info("Content Length is==>" + contentLength);
//		if (Integer.parseInt(contentLength) < 100)
//			logger.warn("Content Length is less than 100");
//		Assert.assertTrue(Integer.parseInt(contentLength) > 100);
//	}

	@Test
	public void checkcookies() {
		logger.info("**** Checking cookies ***** ");
		String cookie = response.getCookie("PHPSESSID");
	}

	@AfterTest
	public void teardown() {
		logger.info("**** Finsih TC001GetAllEmployees ***** ");

	}
}

package com.rest.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rest.testbase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006DeleteInformation extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteEmpoyee() throws Exception {

		logger.info("****Started TC006GetSingleEmployee****");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		// First get the jsonpath object instance from the Resonse interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID);// Pass id to delete
		Thread.sleep(2000);

	}

	@Test
	public void checkresponseBody() {
		logger.info("**** Checking Response Body ***** ");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>" + responseBody);
		Assert.assertEquals(responseBody.contains("successfully ! deleted Records"), true);
	}

	@Test
	public void checkStatusCode() {
		logger.info("**** Checking status code ***** ");
		int statusCode = response.getStatusCode();// Getting status code
		logger.info("Status Code==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
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
		Assert.assertEquals(conetentType, "text/html; charset=UTF-8");
	}

	@Test
	public void checkcontentEncoding() {
		logger.info("**** Checking content Encoding ***** ");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@AfterTest
	public void teardown() {
		logger.info("**** Finsih TC006GetSingleEmployee ***** ");

	}

}

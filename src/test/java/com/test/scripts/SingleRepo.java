package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.constants.EndPoints;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SingleRepo extends UserServiceHelper {

	static RequestSpecification myspec;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = getBaseURI();
		// myspec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
	}

	@Test
	private static void getSingleRepo_TC01() {
		// TODO Auto-generated method stub
		Response response = getSingleRepoTestCase1();
		ReusableMethods.verifyStatusCodeis(response, 200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		// extracting data from JSon
		JsonPath jsonObj = response.body().jsonPath();
		System.out.println("full Name " + jsonObj.get("full_name"));
		Assert.assertEquals(jsonObj.get("default_branch"), "master");
	}

	@Test
	private static void getSingleRepo_TC02() {
		// TODO Auto-generated method stub9
		Response response = getSingleRepoNegative();
		ReusableMethods.verifyStatusCodeis(response, 404);
		JsonPath jsonObj = response.body().jsonPath();
		Assert.assertEquals(jsonObj.get("default_branch"), "Not Found");

	}

	@Test
	private static void createSingleRepo_TC04() {
		// TODO Auto-generated method stub

		Response response = createRepoTestCase4();
		ReusableMethods.verifyStatusCodeis(response, 201);
		JsonPath jsonObj = response.body().jsonPath();
		System.out.println("Repository Name " + jsonObj.get("name"));
		Assert.assertEquals(jsonObj.get("name"), "Hello-World");
		Assert.assertEquals(jsonObj.get("owner.login"), getOwnerName());
		Assert.assertEquals(jsonObj.get("owner.type"), "User");
	}

	// -ve Scenarios for creating Test Cases

	@Test
	private static void createSingleRepo_TC05() {
		// TODO Auto-generated method stub
		Response response = createRepoTestCase4();
		ReusableMethods.verifyStatusCodeis(response, 422);
		JsonPath jsonObj = response.body().jsonPath();
		Assert.assertEquals(jsonObj.get("errors[0].message"), getErrorMsg());

	}

	@Test
	private static void updateSingleRepo_TC06() {
		// TODO Auto-generated method stub
		Response response = updateRepo();
		ReusableMethods.verifyStatusCodeis(response, 200);
		JsonPath jsonObj = response.body().jsonPath();
		Assert.assertEquals(jsonObj.get("name"), getNewRepoName());

	}

	@Test
	private static void updateSingleRepo_TC07() {
		// TODO Auto-generated method stub;
		Response response = deleteRepo();
		ReusableMethods.verifyStatusCodeis(response, 204);

		//

	}

	// -ve scenario
	@Test
	private static void validateTestCase8() {
		// TODO Auto-generated method stub
		Response response = updateRepo();
		ReusableMethods.verifyStatusCodeis(response, 404);
		JsonPath jsonObj = response.body().jsonPath();
		Assert.assertEquals(jsonObj.get("message"), getMessageNotFound());

	}

}

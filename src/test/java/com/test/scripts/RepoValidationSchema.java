package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.constants.EndPoints;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RepoValidationSchema extends UserServiceHelper {

	static RequestSpecification myspec;

	@BeforeTest
	public void setUp() {
		RestAssured.baseURI = getBaseURI();
		myspec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
	}

	@Test()
	public void getAllReposSchema() {
		String mytoken = UserServiceHelper.gettoken();
	//	Header header = new Header("token", mytoken);
		System.out.println(myspec);
		System.out.println(mytoken);
	
		Response res=RestAssured
		.given().log().all()   //logging the info
		.header("Authorization","Bearer" +mytoken)
		.spec(myspec)
		.when()
		.get(EndPoints.GET_ALL_REPOS);
			res.then().log();
		res.prettyPrint();
		ReusableMethods.verifyStatusCodeis(res, 200);
		Assert.assertEquals(res.getContentType(), "application/json; charset=utf-8");
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetAllReposSchema.json"));
		
		
		
		
	}
}

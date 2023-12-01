package com.test.helpers;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.test.constants.EndPoints;
import com.test.utils.Utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class UserServiceHelper {

	static RequestSpecification myspec;
	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = getBaseURI();
		myspec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
	}

	
	public static String getBaseURI() {
		String baseURI = Utils.getConfigProperty("baseUrl");
		return baseURI;
	}

	public static String gettoken() {
		String token = Utils.getConfigProperty("token");
		return token;
	}

	public static String getOwnerName() {
		String owner = Utils.getConfigProperty("owner");
		return owner;
	}

	public static String getRepoName() {
		String owner = Utils.getConfigProperty("repoName");
		return owner;
	}

	public static String getErrorMsg() {
		String message = Utils.getConfigProperty("errorMessage");
		return message;
	}
	
	public static String getNewRepoName() {
		String message = Utils.getConfigProperty("repoupDateName");
		return message;
	}

	public static String getMessageNotFound() {
		String message = Utils.getConfigProperty("message");
		return message;
	}
	
	public static Response getSingleRepoTestCase1() {
		String mytoken = UserServiceHelper.gettoken();
		Header header = new Header("token", mytoken);
		System.out.println(mytoken);
		Response response = RestAssured.given()
				.contentType("application/json")
                 .header(header)
                 .when().get(EndPoints.GET_SINGLE_REPOS);


		response.prettyPrint();
		return response;

	}

	public static Response getSingleRepoNegative() {
		String mytoken = UserServiceHelper.gettoken();
		Header header = new Header("token", mytoken);
		System.out.println(mytoken);
		Response response = RestAssured.given()
				.contentType("application/json")
				.header(header)

				.when().get(EndPoints.GET_SINGLE_REPOS_NEG);
		response.prettyPrint();
		return response;

	}

	public static Response createRepoTestCase4() {
		String mytoken = UserServiceHelper.gettoken();
		Header header = new Header("token", mytoken);
		System.out.println(mytoken);

		Response response = RestAssured.given().contentType("application/json").header(header).body(ReusableMethods.getCreateSingleRepo())

				.when().post(EndPoints.CREATE_REPOS);
		response.prettyPrint();
		return response;
		

	}
	
	public static Response updateRepo() {
		String mytoken = UserServiceHelper.gettoken();
		Header header = new Header("token", mytoken);
		System.out.println(mytoken);

		Response response = RestAssured.given().contentType("application/json").header(header).body(ReusableMethods.getUpdateSingleRepo())

				.when().patch(EndPoints.UPDATE_REPOS);
		response.prettyPrint();
		return response;
	}
	

	public static Response deleteRepo() {
		String mytoken = UserServiceHelper.gettoken();
		Header header = new Header("token", mytoken);
		System.out.println(mytoken);

		Response response = RestAssured.given().contentType("application/json").header(header)

				.when().delete(EndPoints.DELETE_REPOS);
		response.prettyPrint();
		return response;
	}


}






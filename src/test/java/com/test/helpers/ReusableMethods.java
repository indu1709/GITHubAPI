package com.test.helpers;

import com.test.POJO.CreateRepoPOJO;
import com.test.POJO.SingleRepoPOJO;

import io.restassured.response.Response;

public class ReusableMethods {

	public static CreateRepoPOJO getuser;

	public static CreateRepoPOJO getCreateSingleRepo() {
		getuser = new CreateRepoPOJO();
		getuser.setName("Hello");
		getuser.setDesc("This is your first repo");
		getuser.setLink("https://github.com");
		getuser.setVarData(false);
		return getuser;
	}
	
	public static CreateRepoPOJO getUpdateSingleRepo() {
		getuser = new CreateRepoPOJO();
		getuser.setName("Hello-World");
		getuser.setDesc("This is your update repo");
		getuser.setLink("https://github.com");
		
		return getuser;
	}


	public int getStatusCode(Response response) {
		return response.getStatusCode();

	}

	public String getContentType(Response response) {
		return response.getContentType();

	}

	public static void verifyStatusCodeis(Response response, int statuscode) {
		response.then().statusCode(statuscode);

	}

}

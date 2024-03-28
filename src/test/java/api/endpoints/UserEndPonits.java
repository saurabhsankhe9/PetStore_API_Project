package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//These are not Test Method These all endpoints method we are call from test case/method 
//PUT Request
public class UserEndPonits {
	//PUT Request
	public static Response createNewUser(User payload)
	{
		 Response response=given()
		.contentType(ContentType.JSON)
		//.contentType("application/json")
		.accept(ContentType.JSON)
		//.accept("application/json")
		.body(payload)
		.when()
		.post(Routes.post_url);
		 return response;
	}
	
	//GET Request
	public static Response getUser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
		
		.when()
		.get(Routes.get_url);
		return response;
	}

	//PUT Request
public static Response updateUser(User payload,String userName)
	{
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(Routes.put_url);
		return response;
		
	}
//DELETE Request
 public static Response deleteUser(String userName)
 {
	 Response response = given()
	 .pathParam("username", userName)
	 .when()
	 .delete(Routes.delete_url);
	 return response;
 }
}

package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//These are not Test Method These all endpoints method we are call from test case/method 
//PUT Request
public class UserEndPonitsProperty {
	
//Create method to get url & configure with properties file
	
	static ResourceBundle geturl()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("routes");
		return bundle;
	}
	
	//PUT Request
	public static Response createNewUser(User payload)
	{
		String post_url = geturl().getString("post_url");
		
		 Response response=given()
		.contentType(ContentType.JSON)
		//.contentType("application/json")
		.accept(ContentType.JSON)
		//.accept("application/json")
		.body(payload)
		.when()
		.post(post_url);
		 return response;
	}
	
	//GET Request
	public static Response getUser(String userName)
	{
		String get_url = geturl().getString("get_url");
		Response response=given()
				.pathParam("username", userName)
		
		.when()
		.get(get_url);
		return response;
	}

	//PUT Request
public static Response updateUser(User payload,String userName)
	{
		String put_url = geturl().getString("put_url");
		
		Response response =given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username", userName)
		
		.when()
		.put(put_url);
		return response;
		
	}
//DELETE Request
 public static Response deleteUser(String userName)
 {
	 String delete_url = geturl().getString("delete_url");
	 
	 Response response = given()
	 .pathParam("username", userName)
	 .when()
	 .delete(delete_url);
	 return response;
 }
}

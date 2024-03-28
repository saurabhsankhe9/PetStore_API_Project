package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.i18n.phonenumbers.Phonenumber;

import api.endpoints.UserEndPonits;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserTests {
	Faker fakeData;
	User userpayload;
	Logger log;
	
	@BeforeClass
	void setUpData()
	{
		fakeData= new Faker();
		userpayload= new User();
		
		userpayload.setId(fakeData.idNumber().hashCode());
		userpayload.setUsername(fakeData.name().username());
		userpayload.setFirstName(fakeData.name().firstName());
		userpayload.setLastName(fakeData.name().lastName());
		userpayload.setEmail(fakeData.internet().emailAddress());
		userpayload.setPassword(fakeData.internet().password(5,10));
		userpayload.setPhone(fakeData.phoneNumber().phoneNumber());	
		
		 log =  (Logger) LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1,enabled = true)
	public void testPostUser()
	{    
		log.info("***********Creating User***********");
		Response response = UserEndPonits.createNewUser(userpayload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.contentType(),"application/json");
		log.info("***********User is Created***********");
	}
	
	@Test(priority = 2,enabled = true)
	public void testGetUser()
	{	log.info("***********Reading User Info***********");
		Response response = UserEndPonits.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		log.info("***********User info is displayed***********");
	}
	@Test(priority = 3,enabled = true)
	public void testPutUser()
	{	
		log.info("***********Updating User Info***********");
		
		//Update or Regenerate data using payload
		userpayload.setFirstName(fakeData.name().firstName());
		userpayload.setLastName(fakeData.name().lastName());
		userpayload.setEmail(fakeData.internet().emailAddress());
		
		Response response=UserEndPonits.updateUser(userpayload, this.userpayload.getUsername());
		response.then().log().all();
		//response.then().log().body().statusCode(200);//chai asseration comes from RestAssured
		Assert.assertEquals(response.getStatusCode(),200);
		log.info("***********User Upadted***********");
		//Validate data after Updation
		Response responseAfterUpdation = UserEndPonits.getUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdation.getStatusCode(),200);
		
	}
	
	@Test(priority = 4,enabled = true)
	public void testDeleteUser()
	{
		log.info("***********Deleting User***********");
		Response response=UserEndPonits.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		log.info("***********User Deleted***********");
	}
}

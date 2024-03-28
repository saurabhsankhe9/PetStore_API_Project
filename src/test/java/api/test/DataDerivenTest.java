package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPonits;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDerivenTest {
	User payload;
	
	@Test(priority = 1,enabled = true,dataProvider = "AllUserData",dataProviderClass = DataProviders.class)
	public void createNewUsers(String userid,String userName,String firstName,String lastName,String email,String password,String phone)
	{
		payload = new User();
		
		payload.setId(Integer.parseInt(userid));
		payload.setUsername(userName);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response response = UserEndPonits.createNewUser(payload);
	    Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2,enabled = true,dataProvider = "UserNameDataProvider",dataProviderClass = DataProviders.class)
	public void DeleteUser(String userName)
	{
		Response response = UserEndPonits.deleteUser(userName);
		 Assert.assertEquals(response.getStatusCode(), 200);
	}
}

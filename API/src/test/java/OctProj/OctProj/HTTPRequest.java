package OctProj.OctProj;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;


import org.testng.annotations.Test;

/*
 * All the prerequisites will be stored in the given() #contenttype ,
 * setcookies,add auth,add param,set headersinfo etc
 * 
 * 
 * when() get, post,delete,patch ,put
 * 
 * 
 * then() validate status code , extract response,extract headers cookies and
 * response body
 */

public class HTTPRequest {
	int id;

	@Test(priority = 1)
	void ListgetUsers()

	{
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void createUser()

	{
		HashMap data = new HashMap();
		data.put("name", "morpheus");
		data.put("job", "leader");

		id=given()
			.contentType("application/json")
			.body(data)

				.when()
					.post("https://reqres.in/api/users")
					.jsonPath().getInt("id");
		
//
//				.then()
//					.statusCode(201).log().all();

	}

@Test(priority = 3,dependsOnMethods = {"createUser"})
	void UpdateUser() 
	
	{
		HashMap data = new HashMap();
		data.put("name", "mansoor");
		data.put("job","Tester");
		
			
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/" +id)
			
		
		.then()
			.statusCode(200)
			.log().all();

	
	}

@Test(priority = 4)

void deleteUser()
{
	given()
		.when()
		.delete("https://reqres.in/api/users/"+id)

		.then()
			.statusCode(204)
			.log().all();

}
	
}

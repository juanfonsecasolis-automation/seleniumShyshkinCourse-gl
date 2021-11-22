package testsAPI;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.hamcrest.Matchers;

import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class APITest extends RestAssured{
	
	protected Logger logger;

	@BeforeMethod(alwaysRun = true)
	public void SetUp(ITestContext ctx) {
		String testName = ctx.getCurrentXmlTest().getName();
		logger = LogManager.getLogger(testName);
	}
	
	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		// TODO: for some reason the log4j is not working, find out why
		logger.info("Closing driver...");
	}
	
	@Test(groups = {"acceptance"})
	public void VerifyPaginationReturnsTheSameNumberOfItemsSpecifiedOnThePerPageProperty() {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.assertThat().statusCode(Matchers.equalTo(200))
		.body("per_page", Matchers.equalTo(6))
		.body("data.size()", Matchers.equalTo(6));
	}	
	
	@Test
	public void VerifyThatEachUserReturnedContainsPersonalInformation () {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.assertThat().statusCode(Matchers.equalTo(200))
		.body("data[0].id", Matchers.equalTo(1))
		.body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"))
		.body("data[0].first_name", Matchers.equalTo("George"))
		.body("data[0].last_name", Matchers.equalTo("Bluth"))
		.body("data[0].avatar", Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"));
	}	
	
	@Test
	public void VerifyThatTheEachEmailFollowsTheFormatExpected() {
		
		Response response = given()
		.when().get("https://reqres.in/api/users?delay=3")
		.then().assertThat().statusCode(Matchers.equalTo(200))
		.extract().response();
		
		String email = response.body().path("data[0].email");
		Pattern emailPattern = Pattern.compile("[a-zA-Z0-9]@[a-zA-Z0-9].[a-z]+");
		org.testng.Assert.assertTrue(emailPattern.matcher(email).find());
	}
	
	@Test
	public void VerifyUsersCanbeCreatedUsingPost() { 
		// Arrange
		String name = "cypress_test", job = "tester";
		
		// Act
		Response response = 
			given().body(String.format("{'name':'%s', 'job':'%s'}", name, job))
			.when()
			.request("POST", "https://reqres.in/api/users")
			.then().assertThat().statusCode(Matchers.equalTo(201))
			.extract().response();
		System.out.println(response.asPrettyString());
		
		// Assert
		org.testng.Assert.assertTrue(0 < Integer.parseInt((String)response.path("id")));
		org.testng.Assert.assertTrue(!((String)response.path("createdAt")).equals(""));
	}	
	
	@Test
	public void VerifyUserInformationCanBePartiallyUpdated() { 
		// Arrange
		String name = "cypress_test";
		
		// Act
		Response response = 
			given().body(String.format("{'name':'%s'}", name))
			.when()
			.request("PATCH", "https://reqres.in/api/users/0")
			.then().assertThat().statusCode(Matchers.equalTo(200))
			.extract().response();
		System.out.println(response.asPrettyString());
		
		// Assert
		org.testng.Assert.assertTrue(!((String)response.path("updatedAt")).equals(""));
	}	
	
	@Test
	public void VerifyUserInformationCanBeReplaced() { 
		// Arrange
		String name = "cypress_test", job = "job_updated";
		
		// Act
		Response response = 
			given().body(String.format("{'name':'%s', 'job': '%s'}", name, job))
			.when()
			.request("PUT", "https://reqres.in/api/users/0")
			.then().assertThat().statusCode(Matchers.equalTo(200))
			.extract().response();
		System.out.println(response.asPrettyString());
		
		// Assert
		org.testng.Assert.assertTrue(!((String)response.path("updatedAt")).equals(""));
	}	
	
	@Test
	public void VerifyUserCanBeDeleted() { 
		when()
		.request("DELETE", "https://reqres.in/api/users/0")
		.then().assertThat().statusCode(Matchers.equalTo(204));
	}	
	
}

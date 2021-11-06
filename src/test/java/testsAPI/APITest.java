package testsAPI;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.hamcrest.Matchers;
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
	
	@Test
	public void VerifyPaginationReturnsTheSameNumberOfItemsSpecifiedOnThePerPageProperty() {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("per_page", Matchers.equalTo(6))
		.body("data.size()", Matchers.equalTo(6));
	}	
	
	@Test
	public void VerifyThatEachUserReturnedContainsPersonalInformation () {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("data[0].id", Matchers.equalTo(1))
		.body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"))
		.body("data[0].first_name", Matchers.equalTo("George"))
		.body("data[0].last_name", Matchers.equalTo("Bluth"))
		.body("data[0].avatar", Matchers.equalTo("https://reqres.in/img/faces/1-image.jpg"));
	}	
	
	@Test
	public void VerifyThatTheEachEmailFollowsTheFormatExpected() {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("data[0].email", Matchers.equalTo("george.bluth@reqres.in"));
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
			.then().extract().response();
		System.out.println(response.asPrettyString());
		
		// Assert
		org.testng.Assert.assertTrue(0 < Integer.parseInt((String)response.path("id")));
		org.testng.Assert.assertTrue(!((String)response.path("createdAt")).equals(""));
	}	
	
}

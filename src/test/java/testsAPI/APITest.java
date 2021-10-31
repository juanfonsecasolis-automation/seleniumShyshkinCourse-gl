package testsAPI;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;

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
		.body("per_page", equalTo(6))
		.body("data.size()", equalTo(6));
	}	
	
	@Test
	public void VerifyThatEachUserReturnedContainsPersonalInformation () {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("data[0].id", equalTo(1))
		.body("data[0].email", equalTo("george.bluth@reqres.in"))
		.body("data[0].first_name", equalTo("George"))
		.body("data[0].last_name", equalTo("Bluth"))
		.body("data[0].avatar", equalTo("https://reqres.in/img/faces/1-image.jpg"));
	}	
	
	@Test
	public void VerifyThatTheEachEmailFollowsTheFormatExpected() {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("data[0].email", equalTo("george.bluth@reqres.in"));
		
		//Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
	    //Matcher matcher = pattern.matcher("Visit W3Schools!");
	    //boolean matchFound = matcher.find();
	}	
	
}

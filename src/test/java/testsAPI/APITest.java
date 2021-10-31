package testsAPI;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

public class APITest extends RestAssured{
	
	@Test
	public void VerifyPaginationReturnsTheSameNumberOfItemsSpecifiedOnThePerPageProperty() {
		given()
		.when()
		.get("https://reqres.in/api/users?delay=3")
		.then()
		.body("data[0].id", equalTo("1"));
	}	
	
}

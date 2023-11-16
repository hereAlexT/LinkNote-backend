import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import Dto.SignupRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;

import java.util.Random;

public class AuthTest {

    private final String baseUrl = TestSettings.baseUrl;
    private final String authUrl = TestSettings.authUrl;

    private  Faker faker;

    @Before
    public void setUp() {
        faker = new Faker(new Random(1234346435));
    }


    @Test
    public void GivenValidEmailPassword_WhenRequest_GetOK() {



        SignupRequest signupRequest = SignupRequest.builder()
                .email(faker.internet().emailAddress())
                .password("as2SDvx!sSD221")
                .displayName(faker.name().name())
                .build();


        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(1))
                .extract().response();

    }



    public String ValidPasswordGenerator() {
        return faker.internet().password(9,
                31,
                true,
                true,
                true);
    }
}

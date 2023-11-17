import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Dto.LoginRequest;
import Dto.SignupRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class AuthTest {

    private final String baseUrl = TestSettings.baseUrl;
    private final String authUrl = TestSettings.authUrl;

    private final String noteUrl = TestSettings.noteUrl;

    private Faker faker;
    private final Set<String> generatedEmails = new HashSet<>();

    @Before
    public void setUp() {
        faker = new Faker();
    }

    public String getUniqueEmail() {
        String email;
        do {
            email = faker.internet().emailAddress();
        } while (generatedEmails.contains(email));
        generatedEmails.add(email);
        return email;
    }

    public String generateValidPassword() {
        return faker.internet().password(9,
                31,
                true,
                true,
                true);
    }

    @Test
    public void GivenValidEmailPassword_WhenSignup_ReturnOK() {


        SignupRequest signupRequest = SignupRequest.builder()
                .email(faker.internet().emailAddress())
                .password("as2SDvx!sSD221")
                .displayName(faker.name().name())
                .build();


        Response response = given()
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

    @Test
    public void GivenInvalidEmail_WhenSignup_ReturnCode606() {
        //Not Email
        SignupRequest signupRequest = SignupRequest.builder()
                .email("helloworls")
                .password("as2SDvx!sSD221")
                .displayName(faker.name().name())
                .build();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .body("code", equalTo(606))
                .extract().response();

        // Email too long
        signupRequest = SignupRequest.builder()
                .email("dhsdhuashdiuashduasidaidhuahsduaisudhuiashdiuasuidaisdhaiushdiuadiadjasidjaiujduiajsdiuahduiahisdhiudaiuhdiuahsdiuahsduhaidhasuidhiaushdiahsud@asiudhasuidhasiudhasuidhiausdhiausdhiaudhiuashdiu.com")
                .password("as2SDvx!sSD221")
                .displayName(faker.name().name())
                .build();
        response = given()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .body("code", equalTo(606))
                .extract().response();
    }

    @Test
    public void GivenExistedEmail_WhenSignup_ReturnCode605() {
        SignupRequest signupRequest = SignupRequest.builder()
                .email("hello@gmail.com")
                .password("as2SDvx!sSD221")
                .displayName(faker.name().name())
                .build();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .body("code", equalTo(1))
                .extract().response();

        response = given()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .body("code", equalTo(605))
                .extract().response();
    }

    @Test
    public void GivenExistedCredential_WhenLogin_ShouldReturnCode1() {
        String email = getUniqueEmail();
        String password = "as2SDvx!sSD221";
        String displayName = faker.name().name();

        //signup
        SignupRequest signupRequest = SignupRequest.builder()
                .email(email)
                .password(password)
                .displayName(displayName)
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(signupRequest)
                .when()
                .post(authUrl + "/signup")
                .then()
                .log().all()
                .body("code", equalTo(1))
                .extract().response();

        //login
        LoginRequest loginRequest = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        response = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post(authUrl + "/login")
                .then()
                .log().all()
                .statusCode(200) // assuming that the expected status code is 200 OK
                .body("data.token", notNullValue()) // Check if token field is present
                .body("data.token.token", not(emptyOrNullString()))  // Check if token field has a token value
                .body("data.user", notNullValue()) // Check if user field is present
                .body("data.user.id", instanceOf(String.class)) // Check if user id is a string
                .body("data.user.dis_name", equalTo(displayName)) // Check if dis_name matches the expected value
                .body("code", equalTo(1)) // Check if the code field is equal to 1
                .extract().response();

    }


}

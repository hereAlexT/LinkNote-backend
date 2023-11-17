import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Dto.CreateNoteRequest;
import Dto.LoginRequest;
import Dto.SignupRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;

public class NoteTest {
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

    /**
     * @return Jwt token of default 1.
     */
    public String loginDefaultUser1() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email(TestSettings.defaultUserEmail1)
                .password(TestSettings.defaultUserPassword1)
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post(authUrl + "/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(1))
                .extract().response();
        return response.path("data.token.token");
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
    public void GivenValidNote_WhenFetchTheNote_ShouldGetTheSameBody() {
        String body = "Hello World";

        CreateNoteRequest createNoteRequest1 = CreateNoteRequest.builder()
                .body(body)
                .build();

        String token = loginDefaultUser1();

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(createNoteRequest1)
                .when()
                .post(noteUrl)
                .then()
                .log().all()
                .statusCode(201)
                .body("code", equalTo(1))
                .extract().response();

        String noteId = response.path("data.id");
        String thisNoteUrl = noteUrl + "/" + noteId;
        System.out.println("sadjoiasjdioasjdoiasjiod");

        // Get the note by ID and verify the body and ID are the same
        response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(thisNoteUrl) // Use path parameter to pass the note ID
                .then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(1))
                .body("data.id", equalTo(noteId)) // Verify the note ID
                .body("data.body", equalTo(body))
                .extract().response(); // Verify the note body

    }


}

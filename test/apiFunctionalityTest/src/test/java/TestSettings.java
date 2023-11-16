

public class TestSettings {
    public static final String baseUrl = System.getenv("TEST_BASE_URL");
//    public static final String baseUrl = "http://localhost:8080";
    public static final String authUrl = baseUrl + "/api/v1/auth";
    public static final String noteUrl = baseUrl + "/api/v1/note";

}



public class TestSettings {
    public static final String baseUrl = System.getenv("TEST_BASE_URL");
//    public static final String baseUrl = "http://localhost:8080";
    public static final String authUrl = baseUrl + "/api/v1/auth";
    public static final String noteUrl = baseUrl + "/api/v1/note";


    //todo: should be init-ed in test db.
    public static final String defaultUserEmail1 = "link@link.link";
    public static final String defaultUserPassword1 = "@Password@1";
    public static final String getDefaultUserDisplayName1 = "defaultLinkUserName";

    public static final String defaultUserEmail2 = "link@link.link";
    public static final String defaultUserPassword2 = "@Password2";
    public static final String getDefaultUserDisplayName2 = "defaultLinkUserName2";

}

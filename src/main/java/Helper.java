public class Helper {
    static String username = "JuanSandroxd";
    static String password = "JtEPshHpXEi_jPu@854";
    static String userId;
    static String token;
    public static String getBaseUrl() {
        return "https://bookstore.toolsqa.com";
    }

    public static String getAccountUrl() {
        return getBaseUrl() + "/Account/v1/User";
    }
    public static String getTokenUrl() {
        return getBaseUrl() + "/Account/v1/GenerateToken";
    }
    public static String getAuthorizedUrl() {
        return getBaseUrl() + "/Account/v1/Authorized";
    }
    public static String getUserByUserIDUrl() { return getBaseUrl() + "/Account/v1/User/"; }


}

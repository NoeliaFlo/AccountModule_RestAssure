public class Helpers {
    static String username = "LuisOropeza";
    static String password = "JtEPshHpXEi_jPu@854";
    static String userId;
    static String token;
    public static String getBaseUrl() {
        return "https://bookstore.toolsqa.com";
    }

    public static String getAccountUrl() {
        return getBaseUrl() + "/Account/v1/User";
    }

    public static void setUserID(String userIdFromRequest){
        userId = userIdFromRequest;
    }

    public static String getUserID(){
        return userId;
    }
    public static String getUsername(){
        return username;
    }

    public static String getPassword(){
        return password;
    }
}

package api.routes;

public class Routes {
    //User Module Implementation
    public static String baseUrl ="https://petstore.swagger.io/v2";
    public static String createUserUrl =baseUrl+"/user";
    public static String getUserUrl =baseUrl+ "/user/{username}";
    public static String updateUserUrl =baseUrl+ "/user/{username}";
    public static String deleteUserUrl =baseUrl+ "/user/{username}";
}

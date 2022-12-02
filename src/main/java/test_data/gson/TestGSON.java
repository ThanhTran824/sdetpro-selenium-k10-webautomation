package test_data.gson;

import com.google.gson.Gson;

public class TestGSON {

    public static void main(String[] args) {
        Gson gson = new Gson();
        User user;
        // From JSON(String) to object
        String jsonStr = "{\n" +
                "  \"name\": \"Thanh\",\n" +
                "  \"age\" : 34\n" +
                "}";


        user = gson.fromJson(jsonStr, User.class);
        System.out.println(user);

        // From object to JSON
        String convertedJSON = gson.toJson(user);
        System.out.println(convertedJSON);
    }
}

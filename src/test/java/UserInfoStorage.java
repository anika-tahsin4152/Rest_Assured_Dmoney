import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserInfoStorage {
    private JSONArray users;

    public UserInfoStorage() throws IOException, ParseException {
        users = new JSONArray();
        loadUserDataFromFile();
    }

    public void saveUserData(UserModel userModel, String role) throws IOException {
        JSONObject userObject = new JSONObject();
        userObject.put("name", userModel.getName());
        userObject.put("email", userModel.getEmail());
        userObject.put("password", userModel.getPassword());
        userObject.put("phone_number", userModel.getPhone_number());
        userObject.put("nid", userModel.getNid());
        userObject.put("role", role);

        users.add(userObject);
        saveUserDataToFile();
    }

    public void saveUserDataToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("./src/test/resources/dataInfo.json");
        fileWriter.write(users.toJSONString());
        fileWriter.close();
    }

    public void loadUserDataFromFile() throws IOException, ParseException {

        try (FileReader fileReader = new FileReader("./src/test/resources/dataInfo.json")) {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
            users.addAll(jsonArray);

        }

    }
}



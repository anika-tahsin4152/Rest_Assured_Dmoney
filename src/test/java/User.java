import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class User {
    public String baseUrl;
    public static String partnerKey;
    public UserInfoStorage userInfoStorage;

    public User(String baseUrl, String partnerKey) throws IOException, ParseException {
        this.baseUrl = baseUrl;
        this.partnerKey = partnerKey;
        this.userInfoStorage = new UserInfoStorage();
    }

    public String callingLoginAPI(UserModel userModel) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response res = given()
                .contentType("application/json")
                .body(userModel)
                .when()
                .post("/user/login")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = res.jsonPath();
        System.out.println(res.asString());
        String token = jsonPath.get("token");
        return token;
    }

    public void createAgent(String token, UserModel userModel) throws ConfigurationException, IOException {
        RestAssured.baseURI = baseUrl;
        Response res = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());
        userInfoStorage.saveUserData(userModel, "Agent");

    }

    public void createUser1(String token, UserModel userModel) throws ConfigurationException, IOException {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();

        System.out.println(res.asString());
        JsonPath jsonPath = res.jsonPath();
        int user1Id = jsonPath.get("user.id");
        System.out.println(user1Id);
        Utils.SetEnvVar("user1Id", String.valueOf(user1Id));
        userInfoStorage.saveUserData(userModel, "Customer");
    }

    public void createUser2(String token, UserModel userModel) throws ConfigurationException, IOException {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/user/create")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        int user2Id = jsonPath.get("user.id");
        System.out.println(user2Id);
        Utils.SetEnvVar("user2Id", String.valueOf(user2Id));
        userInfoStorage.saveUserData(userModel, "Customer");
    }

    public void FromSystemToAgent(String token, String agentPhoneNumber, int amount,UserModel userModel) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response tRes = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/transaction/deposit")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println(tRes.asString());
    }
    public void FromAgentToCustomer(String token, String agentPhoneNumber, String customerPhoneNumber, int amount) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        String requestBody = String.format(
                "{\"from_account\":\"%s\", \"to_account\":\"%s\", \"amount\":%d}",
                agentPhoneNumber, customerPhoneNumber, amount
        );

        Response tRes = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(requestBody)
                .when()
                .post("/transaction/deposit")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println(tRes.asString());
    }
    public void Withdraw (String token, UserModel userModel) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response tRes = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/transaction/deposit")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        System.out.println(tRes.asString());
    }
    public void SendMoney (String token, UserModel userModel) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response tRes = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(userModel)
                .when()
                .post("/transaction/deposit")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        System.out.println(tRes.asString());
    }
    public void PaymentToMerchant(String token, String customerPhoneNumber, int amount, String merchantPhoneNumber) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        String requestBody = String.format(
                "{\"from_account\":\"%s\", \"to_account\":\"%s\", \"amount\":%d}",
                customerPhoneNumber, merchantPhoneNumber, amount
        );

        Response tRes = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(requestBody)
                .when()
                .post("/transaction/payment")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

    System.out.println( tRes.asString());
    }
    public int checkCustomerBalance(String token, String customerPhoneNumber) throws ConfigurationException {
        RestAssured.baseURI = baseUrl;
        Response res = given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .when()
                .get("/user/balance/" + customerPhoneNumber)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = res.jsonPath();
        int balance = jsonPath.get("balance");
        System.out.println("Customer Balance: " + balance);
        return balance;
    }


    public void searchUser(String token, String user2Id) throws InterruptedException {
        Thread.sleep(3000);
        RestAssured.baseURI = baseUrl;
        Response res1 = given().contentType("application/json")
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .when()
                .get("/user/search/id/" + user2Id);
        System.out.println(res1.asString());
    }

    public String getAgentPhoneNumber(String dataFilePath) {
        return dataFilePath;
    }

    public void FromSystemToAgent(String token, String agentPhoneNumber, int amount) {
    }

    public String getCustomerPhoneNumber(String dataFilePath) {
        return dataFilePath;
    }

    public String getAnotherCustomerPhoneNumber(String dataFilePath) {
        return null;
    }

    public void SendMoneyToAnotherCustomer(String token, String senderPhoneNumber, String recipientPhoneNumber, int amount) {
    }

    public void PaymentToMerchant(String token, String merchantPhoneNumber, int amount) {
    }

    public void CheckCustomerBalance(String token, String customerPhoneNumber) {
    }

    public void FromAgentToCustomer(String token, String customerPhoneNumber, int amount) {
    }

    public void FromCustomerToAgent(String token, String customerPhoneNumber, int amount) {
    }

    public void Withdraw(String token, String customerPhoneNumber, int amount) {
    }
}

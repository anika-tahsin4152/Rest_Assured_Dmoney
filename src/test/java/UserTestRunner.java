import com.github.javafaker.Faker;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner extends Setup {
    User user;

    public UserTestRunner() throws IOException, ConfigurationException, ParseException {
        initConfig();
        String partnerKey = prop.getProperty("partnerKey");
        user = new User(prop.getProperty("baseUrl"), partnerKey);
    }

    @Test(priority = 1)
    public void AdminLogin() throws IOException, ConfigurationException {
        UserModel userModel = new UserModel();
        userModel.setEmail("salman@roadtocareer.net");
        userModel.setPassword("1234");
        String token = user.callingLoginAPI(userModel);
        Utils.SetEnvVar("token", token);

    }

    @Test(priority = 2)
    public void CreateAgent() throws ConfigurationException, IOException {
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("01" + Utils.generateRandomiD(100000000, 999999999));
        userModel.setNid(Utils.generateRandomniD());
        userModel.setRole("Agent");
        user.createAgent(prop.getProperty("token"), userModel);

    }

    @Test(priority = 3)
    public void CreateUser1() throws ConfigurationException, IOException {
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1243");
        userModel.setPhone_number("01" + Utils.generateRandomiD(100000000, 999999999));
        userModel.setNid(Utils.generateRandomniD());
        userModel.setRole("Customer");
        user.createUser1(prop.getProperty("token"), userModel);
    }

    @Test(priority = 4)
    public void CreateUser2() throws ConfigurationException, IOException {

        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("01" + Utils.generateRandomiD(100000000, 999999999));
        userModel.setNid(Utils.generateRandomniD());
        userModel.setRole("Customer");
        user.createUser2(prop.getProperty("token"), userModel);
    }


    @Test(priority = 5)
    public void depositSystemToAgent() throws ConfigurationException, IOException, ParseException {
        String dataFilePath = "./src/test/resources/dataInfo.json";
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
        String agentPhoneNumber = user.getAgentPhoneNumber(dataFilePath);
        int amount = 2000;
        user.FromSystemToAgent(prop.getProperty("token"), agentPhoneNumber, amount);
    }
    @Test(priority = 6)
    public void depositFromAgentToCustomer() throws ConfigurationException, IOException, ParseException {
        String dataFilePath = "./src/test/resources/dataInfo.json";
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
        String customerPhoneNumber = user.getCustomerPhoneNumber(dataFilePath);
        int amount = 1500;
        user.FromAgentToCustomer(prop.getProperty("token"), customerPhoneNumber, amount);
    }

    @Test(priority = 7)
    public void withdrawFromCustomerToAgent() throws ConfigurationException, IOException, ParseException {
        String dataFilePath = "./src/test/resources/dataInfo.json";
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
        String customerPhoneNumber = user.getCustomerPhoneNumber(dataFilePath);
        int amount = 500;
        user.Withdraw(prop.getProperty("token"), customerPhoneNumber, amount);
    }

    @Test(priority = 8)
    public void sendMoneyToAnotherCustomer(UserModel userModel) throws ConfigurationException, IOException, ParseException {
        String dataFilePath = "./src/test/resources/dataInfo.json";
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
        String senderPhoneNumber = user.getCustomerPhoneNumber(dataFilePath);
        String recipientPhoneNumber = user.getAnotherCustomerPhoneNumber(dataFilePath);
        int amount = 500;
        user.SendMoney(prop.getProperty("token"), userModel);}

    @Test(priority = 9)
    public void paymentToMerchant() throws ConfigurationException, IOException {
        String merchantPhoneNumber = "01686606905";
        int amount = 100;
        user.PaymentToMerchant(prop.getProperty("token"), merchantPhoneNumber, amount);
    }

    @Test(priority = 10)
    public void checkCustomerBalance() throws ConfigurationException, IOException, ParseException {
        String dataFilePath = "./src/test/resources/dataInfo.json";
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
        String customerPhoneNumber = user.getCustomerPhoneNumber(dataFilePath);
        user.CheckCustomerBalance(prop.getProperty("token"), customerPhoneNumber);
    }


    @Test(priority = 6)
   public void searchUser() throws IOException, InterruptedException, ParseException {
        User user = new User(prop.getProperty("baseUrl"), User.partnerKey);
       user.searchUser(prop.getProperty("token"), (prop.getProperty("user2Id")));
    }

}
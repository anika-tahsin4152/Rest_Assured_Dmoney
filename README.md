# Rest_Assured_Dmoney Project :
## 1. Login as Admin: Send a POST request with admin credentials to obtain an authentication token.
## 2. Create New Customers and Agent: Use POST requests to create customers and an agent.
## 3. Transfer Funds: Transfer 2000 TK from the System account to the Agent account.
## 4. Deposit Funds: Deposit 1500 TK to a customer's account from the agent account.
## 5. Withdraw Funds: Withdraw 500 TK from a customer's account to the agent account.
## 6. Send Money: Transfer 500 TK from one customer to another.
## 7. Make Payment: Make a 100 TK payment to a merchant "merchant number" using a customer's account.
## 8. Check Balance: Send a GET request to check the balance of a customer's account.

# necessary dependencies :
--// https://mvnrepository.com/artifact/io.rest-assured/rest-assured    
--// https://mvnrepository.com/artifact/org.testng/testng  
--// https://mvnrepository.com/artifact/commons-configuration/commons-configuration   
--// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind   
--// https://mvnrepository.com/artifact/com.github.javafaker/javafaker   
--// https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple 
--// https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
--// https://mvnrepository.com/artifact/org.hamcrest/hamcrest

# Allure Report :
-download the zip from the given link : https://github.com/allure-framework/allure2/releases
-UNzip file , copy the path till bin and Setup home environment on your windows 
-go to your IDE then add this on build.gradle.
-- plugins {
    id 'java'
    id 'io.qameta.allure' version '2.10.0'
}
allure {
    version = '2.10.0'
} 
- 
   

# Allure Report
--![screencapture-192-168-0-104-57179-index-html-2023-10-24-19_32_07](https://github.com/anika-tahsin4152/Rest_Assured_Dmoney/assets/73738319/fa25fa63-5a45-4fc9-81ed-78bb5b04b643)

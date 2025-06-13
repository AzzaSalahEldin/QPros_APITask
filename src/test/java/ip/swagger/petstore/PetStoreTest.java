package ip.swagger.petstore;


import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.controller.UserController;
import io.swagger.petstore.data.UserData;
import io.swagger.petstore.model.User;
import ip.swagger.petstore.config.BaseTest;
import ip.swagger.petstore.config.TestRequestContext;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

@Epic("User Management")
@Feature("User CRUD")
@Listeners({AllureTestNg.class})
public class PetStoreTest  extends BaseTest {
    UserController userController = new UserController();
    TestRequestContext context;
    TestRequestContext context_assert;
    ResponseContext response;

    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Creating user with ID: {user.id}")
    @Test(description = "Test user creation",dataProvider = "UserData", dataProviderClass = UserData.class)
    public void createUserTest(User user) {
        context = new TestRequestContext("POST", MediaType.APPLICATION_JSON,"/user");
        response = userController.createUser(context,user);
        Assert.assertEquals(response.getStatus(), 200);
        User createdUser = (User) response.getEntity();
        String userId = String.valueOf(createdUser.getId());
        Assert.assertNotNull(userId);
    }

    @Story("Read User")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Reading user with ID: {user.id}")
    @Test(description = "Test user reading",dataProvider = "UserData", dataProviderClass = UserData.class, dependsOnMethods = "createUserTest")
    public void readUserTest(User user) {
        context = new TestRequestContext("GET", MediaType.APPLICATION_JSON,String.valueOf(user.getId()));
        response = userController.getUserByName(context,"user1");
        Assert.assertEquals(response.getStatus(), 200);
        User createdUser = (User) response.getEntity();
        Assert.assertNotNull(String.valueOf(createdUser.getId()));
    }

    @Story("Update User")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Updating user with name: {user.name}")
    @Test(description = "Test user updating",dataProvider = "updatedUser", dataProviderClass = UserData.class,dependsOnMethods = "readUserTest")
    public void updateUserTest(String userName, User updatedUser) {
        context = new TestRequestContext("PUT", MediaType.APPLICATION_JSON,"/user");
        response = userController.updateUser(context,userName, updatedUser);
        Assert.assertEquals(response.getStatus(), 200);
        User createdUser = (User) response.getEntity();
        Assert.assertEquals(createdUser.getUsername(), updatedUser.getUsername());
    }

    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Deleting user with name: {user.name}")
    @Test(description = "Test user deletion",dataProvider = "updatedUser", dataProviderClass = UserData.class, dependsOnMethods = "updateUserTest")
    public void deleteUserTest(String userName, User updatedUser) {
        context = new TestRequestContext("DELETE", MediaType.APPLICATION_JSON,String.valueOf(updatedUser.getId()));
        response = userController.deleteUser(context,userName);
        Assert.assertEquals(response.getStatus(), 200);
        context_assert = new TestRequestContext("GET", MediaType.APPLICATION_JSON,String.valueOf(updatedUser.getId()));
        ResponseContext verifyResponse = userController.getUserByName(context_assert,userName);
        Assert.assertEquals(verifyResponse.getStatus(), 404);
    }
}

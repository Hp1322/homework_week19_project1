package in.reqres.reqresinfo;

import in.reqres.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ReqresDataDrivenTest extends TestBase {

    static String name = "morpheus";
    static String job = "leader";
    static String email = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String userID;
    static String id;

    @Steps
    ReqresSteps reqresSteps;

    @Title("This will create new user")
    @Test
    public void createMultipleUsers(){
        ValidatableResponse response = reqresSteps.createUser(name,job);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);

    }
    @Title("Verify if user was added to the list")
    @Test
    public void verifyUsersAddedToList(){
        HashMap<String, Object> userMap = reqresSteps.getUserInfoByID(userID);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println("user map :"+userMap);

    }
    @Title("User will login with credentials")
    @Test
    public void loginWithCredetials(){
        ValidatableResponse response = reqresSteps.createUser(email,password);
        response.log().all().statusCode(201);

    }
    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void deleteUserAndVerify() {
        reqresSteps.deleteUser(userID).statusCode(204);
        reqresSteps.getUserById(userID).statusCode(404);
    }


}

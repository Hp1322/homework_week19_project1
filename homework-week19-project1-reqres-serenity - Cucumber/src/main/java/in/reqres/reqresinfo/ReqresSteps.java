package in.reqres.reqresinfo;

import in.reqres.constants.EndPoints;
import in.reqres.model.ReqresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ReqresSteps {

    @Step("Creating user with name : {0} and id : {1}")
    public ValidatableResponse createUser(String name, String job){
        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setName(name);
        reqresPojo.setJob(job);

        return SerenityRest.given().log().all()
                .header("Connection","keep-alive")
                .body(reqresPojo)
                .when()
                .post(EndPoints.CREATE_USERS_BY_ID)
                .then();
    }
    @Step("Getting the user information with ID : {0}")
    public HashMap<String, Object> getUserInfoByID(String userID){
        HashMap<String, Object> userMap = SerenityRest.given().log().all().
                when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return userMap;
    }
    @Step("login with name : {0} and password : {1}")
    public ValidatableResponse loginWithCredentials(String email, String password){
        ReqresPojo reqresPojo = new ReqresPojo();
        reqresPojo.setEmail(email);
        reqresPojo.setPassword(password);

        return SerenityRest.given().log().all()
                .header("Connection","keep-alive")
                .body(reqresPojo)
                .when()
                .post(EndPoints.LOGIN_SUCCESSFUL)
                .then();
    }
    @Step("Deleting user information with userID : {0}")
    public ValidatableResponse deleteUser(String userID){
        return SerenityRest.given().log().all()
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting user information with userID: {0}")
    public ValidatableResponse getUserById(String userID){
        return SerenityRest.given().log().all()
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
    @Step("Getting all product from application")
    public ValidatableResponse getAllUsers(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then();

    }



}

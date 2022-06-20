package in.reqres.cucumber.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import in.reqres.reqresinfo.ReqresSteps;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;

public class UserStepdefs {

    static String name = "morpheus";
    static String job = "leader";
    static String email = "eve.holt@reqres.in";
    static String password = "cityslicka";
    static String userID;
    static String id;
    static ValidatableResponse response;

    @Steps
    ReqresSteps reqresSteps;

    @Given("^I am on homepage of application of user$")
    public void iAmOnHomepageOfApplicationOfUser() {
    }

    @When("^I send Get request to list endpoint of user$")
    public void iSendGetRequestToListEndpointOfUser() {
        response = reqresSteps.getAllUsers();
    }

    @Then("^I must get back a valid status code (\\d+) of user$")
    public void iMustGetBackAValidStatusCodeOfUser(int code) {
        response.statusCode(code);
        response.assertThat().statusCode(code);
    }

    @When("^I send Post request to list endpoint of user$")
    public void iSendPostRequestToListEndpointOfUser() {
        response = reqresSteps.createUser(name, job);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @When("^I send Delete request to list endpoint of user$")
    public void iSendDeleteRequestToListEndpointOfUser() {
        response = reqresSteps.deleteUser(userID);
        response.assertThat().statusCode(204);
    }

    @And("^I validate if user is deleted$")
    public void iValidateIfUserIsDeleted() {
        response = reqresSteps.getUserById(userID);
        response.assertThat().statusCode(404);
    }

    @When("^I send Post request to login endpoint of user$")
    public void iSendPostRequestToLoginEndpointOfUser() {
        response = reqresSteps.createUser(email, password);
        response.log().all().statusCode(201);

    }

    @Then("^I validate page \"([^\"]*)\"$")
    public void iValidatePage(int expected) {
        response = reqresSteps.getAllUsers();
        int page = response.log().all().extract().path("page");
        Assert.assertEquals(expected, page);
    }

    @And("^I validate per_page \"([^\"]*)\"$")
    public void iValidatePer_page(int expected) {
        int perPage = response.log().all().extract().path("per_page");
        Assert.assertEquals(expected, perPage);
    }

    @And("^I validate totalID \"([^\"]*)\"$")
    public void iValidateTotalID(int expected) {
        int totalID = response.log().all().extract().path("data[1].id");
        Assert.assertEquals(expected, totalID);
    }

    @And("^I validate name of Id \"([^\"]*)\"$")
    public void iValidateListOfTotalData(String expected) {
        String firstName = response.log().all().extract().path("data[3].first_name");
        Assert.assertEquals(expected, firstName);
    }

    @And("^I validate list of data 6$")
    public void iValidateListOfData() {
        List<?> listOfTotalData = response.log().all().extract().path("data");
        Assert.assertEquals(6, listOfTotalData.size());
    }

    @And("^I validate avatar \"([^\"]*)\"$")
    public void iValidateAvatar(String expected) {
        String avatar = response.log().all().extract().path("data[5].avatar");
        Assert.assertEquals(expected, avatar);
    }

    @And("^I validate supportUrl \"([^\"]*)\"$")
    public void iValidateSupportUrl(String expected) {
        String supportUrl = response.log().all().extract().path("support.url");
    }

    @And("^I validate supportText \"([^\"]*)\"$")
    public void iValidateSupportText(String expected) {
        String supportText = response.log().all().extract().path("support.text");
    }
}

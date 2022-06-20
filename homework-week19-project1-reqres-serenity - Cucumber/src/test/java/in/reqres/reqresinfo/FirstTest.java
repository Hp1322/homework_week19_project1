package in.reqres.reqresinfo;


import in.reqres.constants.EndPoints;
import in.reqres.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class FirstTest extends TestBase {

    @Test
    public void getAllProduct(){
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .log().all()
                .statusCode(200);
    }

}

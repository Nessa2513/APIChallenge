package requests;

//import org.asynchttpclient.Response;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetDataProperties;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;
public class GetRequest extends SetDataProperties {
    Logger logger = Logger.getLogger("logs"); // It is use for create messages to execution of an application
    java.lang.String requestToken;
    JSONObject jsonObject = new JSONObject();

    public GetRequest(){super();}

    public void tokenGeneration(){
        Response response = given()
                .queryParam("apiKey",getApiKey())
                .when()
                .get(getBaseURL()+ "/authentication/token/new")
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        this.requestToken = response.jsonPath().getString("requestToken");
    }
    public void getListDetails (int idList){

        Response response = given()
                .queryParam("api_key",getApiKey())
                .queryParam("list_id",idList)
                .when()
                .get(getBaseURL() + "/list/" + idList)
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("true",response.jsonPath().getString("list_id"));

    }
    public void getMovieDetails (int idMovie){

        Response response = given()
                .queryParam("api_key",getApiKey())
                .queryParam("movie_id",idMovie)
                .when()
                .get(getBaseURL() + "/movie/" + idMovie)
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
        Assert.assertEquals("true",response.jsonPath().getString("list_id"));

    }
}

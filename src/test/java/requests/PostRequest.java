package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.SetDataProperties;
import org.json.JSONObject;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class PostRequest extends SetDataProperties {
    Logger logger = Logger.getLogger("logs"); // It is use for create messages to execution of an application
    String request_token;
    JSONObject jsonObject = new JSONObject();
    GetRequest getRequest = new GetRequest();
    String idSession;
    String idList;

    private String validateTokenPath = "/authentication/token/validate_with_login";
    private String createSession = "/authentication/session/new";
    private String rateMovie = "/movie/";
    private String list = "/list";

    public void tokenValidation(){
        getRequest.tokenGeneration();
        jsonObject
                .put("username",getUser())
                .put("password",getPassword())
                .put("request_token",getRequest.requestToken);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApiKey())
                .body(jsonObject.toString())
                .when()
                .post(getBaseURL() + "/authentication/token/validate_with_login")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        this.request_token = response.jsonPath().getString("request_token");

    }

    public void createSession() {
        tokenValidation();
        jsonObject
                .put("request_token", this.request_token);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApiKey())
                .body(jsonObject.toString())
                .when()
                .post(getBaseURL() + "/authentication/session/new")
                .then()
                .statusCode(200)
                .extract()
                .response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        this.idList = response.jsonPath().getString("session_id");
    }
        public void rateMovie(int idMovie, int rate){
            createSession();
            jsonObject
                    .put("value",rate);
            Response response = given()
                    .contentType(ContentType.JSON)
                    .queryParam("api_key",getApiKey())
                    .queryParam("session_id",this.idList)
                    .body(jsonObject.toString())
                    .when()
                    .post(getBaseURL() + "/movie/" + idMovie + "/rating")
                    .then()
                    .statusCode(201)
                    .extract().response();
            Assert.assertEquals("true", response.jsonPath().getString("success"));
        }
    public void createList(String listName, String listDescription, String listLanguage){
        createSession();
        jsonObject
                .put("name",listName)
                .put("description",listDescription)
                .put("language",listLanguage);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApiKey())
                .queryParam("session_id",this.idList)
                .body(jsonObject.toString())
                .when()
                .post(getBaseURL() + list)
                .then()
                .statusCode(201)
                .extract().response();
        this.idList = response.jsonPath().getString("list_id");
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.", response.jsonPath().getString("status_message"));

    }

    public void addMovie(int idMovie, String idList){
        createSession();

        jsonObject
                .put("media_id",idMovie);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApiKey())
                .queryParam("session_id",this.idList)
                .body(jsonObject.toString())
                .when()
                .post(getBaseURL() + "/list/"+ idList + "/add_item")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));

    }

    public void clearList(String idList){
        Response response = given()
                .queryParam("api_key",getApiKey())
                .queryParam("session_id",this.idList)
                .queryParam("confirm",true)
                .post(getBaseURL() + "/list/" + idList + "/clear")
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was updated successfully.", response.jsonPath().getString("status_message"));

    }

    public String getIDList() {
        return idList;
    }
}

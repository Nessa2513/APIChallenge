package requests;

import utils.SetDataProperties;
import org.json.JSONObject;
import java.util.logging.Logger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends SetDataProperties {
    Logger logger = Logger.getLogger("logs"); // It is use for create messages to execution of an application
    JSONObject jsonObject = new JSONObject();
    PostRequest postRequest = new PostRequest();


    public DeleteRequest(){
        super();
    }

    public void deleteList(int idList){
        jsonObject
                .put("session_id", idList);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("apiKey",getApiKey())
                .body(jsonObject.toString())
                .when()
                .delete(getBaseURL()+ "/list/"+idList)
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertEquals("true",response.jsonPath().getString("success"));

    }
}

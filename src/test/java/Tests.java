import org.testng.annotations.Test;
import requests.DeleteRequest;
import requests.GetRequest;
import requests.PostRequest;

public class Tests {
    GetRequest getRequest = new GetRequest();
    PostRequest postRequest = new PostRequest();

    // Authentication
    @Test
    public void token(){
        getRequest.tokenGeneration();
    }
    @Test
    public void TokenValidation(){
        postRequest.tokenValidation();
    }
    @Test
    public void createSession(){
        postRequest.createSession();
    }

    // Lists
    @Test
    public void createList(){
        postRequest.createList("Action movies","Here are action movies","en");
    }
    @Test
    public void addMovie(){
        postRequest.createList("Action movies","Here are action movies","en");
        postRequest.addMovie(300671,postRequest.getIDList());
    }
    @Test
    public void getListDetails(){
        getRequest.getListDetails(8222303);
    }
    @Test
    public void clearList(){
        int idList = 8222303;

        postRequest.createList("Action movies","Here are action movies","en");
        postRequest.addMovie(idList,postRequest.getIDList());
        getRequest.getListDetails(Integer.parseInt(postRequest.getIDList()));
        postRequest.clearList(postRequest.getIDList());
    }
    @Test
    public void deleteList(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteList(8222303);
    }


    // Movies
    @Test
    public void getMovieDetails(){
        getRequest.getMovieDetails(300671);
    }
    @Test
    public void rateMovie(){
        postRequest.rateMovie(300671, 5);
    }
}

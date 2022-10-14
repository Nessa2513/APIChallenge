package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetDataProperties {
    private String baseURL;
    private String apiKey;
    private String authRequestToken;
    private String user;
    private String password;
    private String token;
    private int listID;
    private String listName;
    private int movieID;

    public SetDataProperties(){
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("data.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }

        setBaseURL(properties.getProperty("baseUrl"));
        setApiKey(properties.getProperty("apiKey"));
        setAuthRequestToken(properties.getProperty("authRequestToken"));
        setUser(properties.getProperty("user"));
        setPassword(properties.getProperty("password"));
        setToken(properties.getProperty("token"));
        setListID(Integer.parseInt(properties.getProperty("listID")));
        setListName(properties.getProperty("listName"));
        setMovieID(Integer.parseInt(properties.getProperty("movieID")));

    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getAuthRequestToken() {
        return authRequestToken;
    }

    public void setAuthRequestToken(String authRequestToken) {
        this.authRequestToken = authRequestToken;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}

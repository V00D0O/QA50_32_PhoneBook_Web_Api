package api_tests;

import dto.User;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.UserFactory.positiveUser;

public class RegistrationApiTests implements BaseApi {

    @Test
    public void registrationPositiveApiTest(){
        User user = positiveUser();
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void registrationNegative_Wrong_Password_ApiTest(){
        User user = positiveUser();
        user.setPassword("wrong password");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_Wrong_Email_ApiTest(){
        User user = positiveUser();
        user.setUsername("Marat1990mal.com");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_DuplicateUser_ApiTest(){
        User user = positiveUser();
        user.setUsername("Marat199000@mail.com");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 409);
    }

    @Test
    public void registrationNegative_EmptyPassword_ApiTest(){
        User user = positiveUser();
        user.setPassword("");
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_SpaceInPassword_ApiTest(){
        User user = positiveUser();
        user.setPassword("          "); // 10
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNegative_Wrong_Email_WithCapsLock_ApiTest(){
        User user = positiveUser();
        user.setUsername(user.getUsername().toUpperCase());
        System.out.println(user);
        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);
        Request request = new Request.Builder().url(BASE_URL + REGISTRATION_URL).post(requestBody).build();
        Response response;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(response.code());
        Assert.assertEquals(response.code(), 400); // Actual 200
    }

}
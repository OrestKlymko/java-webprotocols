package crudOperations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import crudOperations.user.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class CRUDMethods {
    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String getAllUsers(String URL) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body().toString();
    }

    public int lastId() throws IOException, InterruptedException {
        String allUsers = getAllUsers(Main.URL);


        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> userList = gson.fromJson(allUsers, userListType);

        return userList.stream().max(Comparator.comparingDouble(User::getId)).get().getId();
    }

    public String createUser(String URL, User user) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.statusCode()==201?"crudOperations.user.User created":"Something wrong...";
    }



    public String updateUser(String URL, User user) throws IOException, InterruptedException {


        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body().toString();
    }

    public String getUserById(String URL,int id) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL+"/"+id))
                .GET()
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
       return response.body().toString();
    }

    public String getUserByUserName(String URL,String username) throws IOException, InterruptedException {

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(URL+"?username="+username))
                .GET()
                .build();

        HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response.body().toString();
    }

public String deleteUser(String URL, int userId) throws IOException, InterruptedException {

    HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(URL+"/"+userId))
            .DELETE()
            .build();
    HttpResponse<?> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    return response.statusCode()==201?"crudOperations.user.User deleted":"Something wrong...";
}

}

package comment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;

public class GetComment {

    public static int maxId=0;
    public void getAndWriteComment(int id) throws IOException, InterruptedException {


        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        String URL = "https://jsonplaceholder.typicode.com/users/" + id + "/posts";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body().toString();

        Type userListType = new TypeToken<List<UserPosts>>() {
        }.getType();
        List<UserPosts> userList = gson.fromJson(body, userListType);
        maxId = userList.stream().max(Comparator.comparingDouble(UserPosts::getId)).get().getId();

        HttpRequest getComments = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + maxId + "/comments"))
                .GET()
                .build();


        WriterFile writerFile = new WriterFile();
        writerFile.writeToFile(client.send(getComments, HttpResponse.BodyHandlers.ofString()).body(),id);

    }
}

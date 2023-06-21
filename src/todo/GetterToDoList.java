package todo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import comment.UserPosts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

public class GetterToDoList {

    public void getOpenTask(int id) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/"+id+"/todos"))
                .GET()
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<ToDo> todoList = new Gson().fromJson(response.body().toString(), new TypeToken<List<ToDo>>() {
        }.getType());
        List<ToDo> filterList = todoList.stream().filter(i -> !i.isCompleted()).collect(Collectors.toList());
        for (ToDo toDo : filterList) {
            System.out.println("Open task: " + toDo.getTitle());
        }
    }

}

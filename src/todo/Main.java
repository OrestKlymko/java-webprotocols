package todo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new GetterToDoList().getOpenTask(2);
    }
}

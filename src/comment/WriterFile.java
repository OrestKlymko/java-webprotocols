package comment;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriterFile {
    public void writeToFile(String responseComments,int id) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./resources/user-" + id + "-post-" + GetComment.maxId + "-comments.json");
        try {
            fileOutputStream.write(responseComments.getBytes(StandardCharsets.UTF_8));
            System.out.println("File created!");
        }
            catch (IOException e) {
            System.out.println("File didn't created");
            throw new RuntimeException(e);
        }
        fileOutputStream.close();
    }
}

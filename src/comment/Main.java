package comment;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        GetComment getComment = new GetComment();
        getComment.getAndWriteComment(1);
    }
}


package BackEndSide;

import java.io.FileWriter;
import java.io.IOException;

public class File_Worker {
    
    public static void create_file() {
        try {
            FileWriter file = new FileWriter("user.json");

            String content = "This is some text written to the file.";
            file.write(content);
      
            System.out.println("Successfully wrote to the file!");
            file.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to write to the file!");
            e.printStackTrace();
        }
    }

}
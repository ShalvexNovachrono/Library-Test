package BackEndSide;

import static FrontEndSide.GUI_WORKER.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.mindrot.BCrypt;

public class File_Worker {

    private static String FileName_U_P = "user.txt";

    public static boolean isValid(String text) {
        if (text == null || text.isEmpty()) {
            return false; // Handle empty strings
        }

        // Use regular expression for efficient character matching
        return text.matches("^[a-zA-Z]+$");
    }

    public static String hashPassword(String password) {
        // Generate a random salt
        String salt = BCrypt.gensalt(12); // Adjust cost factor as needed
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static Boolean Check_if_user_exists(String Username) {
        Boolean returnThis = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FileName_U_P))) {
            String line;
            Loop:
            while ((line = reader.readLine()) != null) {
                String[] UsernameFromFile = line.split("[-/00/-]");
                try {
                    if (UsernameFromFile[0].substring(0, UsernameFromFile[0].length() - 1).equals(Username)) {
                        returnThis = true;
                        break Loop;                  
                    }
                } catch (Exception e) {
                    if (UsernameFromFile[0].equals(Username)) {
                        returnThis = true;
                        break;      
                    }
                }
            }
        } catch (IOException e) {
            Panel_Error_Message("Error reading file", e.getMessage());
        }
        return returnThis;
    }
    
    public static void Register(String Username, String Password) {
        if (isValid(Username)) { 
            if (!Check_if_user_exists(Username)) {
                try {
                    FileWriter file = new FileWriter(FileName_U_P, true);

                    String content = Username + "[-/00/-]" + hashPassword(Password) + "\n";
                    file.write(content);
                    Panel_Number_2();
                    file.close();
                } catch (IOException e) {
                    Panel_Error_Message("Error writing file", e.getMessage());
                    e.printStackTrace();
                }
            } else {
                Panel_Error_Message("User details", "Username is name is taken.");
            }
        } else {
            Panel_Error_Message("Invalid characters", "Username has invalid characters only english alphabets allowed.");
        }
    }

    public static void Login(String Username, String Password) {
        if (isValid(Username)) { 
            if (Check_if_user_exists(Username)) {
                try (BufferedReader reader = new BufferedReader(new FileReader(FileName_U_P))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] UserDetailsFromLine = line.split("[-/00/-]");
                        // System.out.println(UserDetailsFromLine[0].substring(0, UserDetailsFromLine[0].length() - 1));
                        // System.out.println(line);
                        // System.out.println(line.substring(UserDetailsFromLine[0].length() + 7, line.length()));
                        try {
                            if (UserDetailsFromLine[0].substring(0, UserDetailsFromLine[0].length() - 1).equals(Username) && verifyPassword(Password, line.substring(UserDetailsFromLine[0].length() + 7, line.length()))) {
                                FrontEndSide.GUI_WORKER.Username = Username;
                                isLoggedIn = true;
                                Panel_Number_4();
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("lines are not working.");
                        }
                    }
                } catch (IOException e) {
                    Panel_Error_Message("Error reading file", e.getMessage());
                }
            } else {
                Panel_Error_Message("User details", "User details dont match.");
            }
        } else {
            Panel_Error_Message("Invalid characters", "Username has invalid characters only english alphabets allowed.");
        }
    }

}
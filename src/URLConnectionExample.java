import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class URLConnectionExample {
    public static void main(String[] argv) {
        boolean programRunning = true;
        Scanner myScanner = new Scanner(System.in);
        String userString;

        // URL objects
        URL url;
        HttpURLConnection connection;

        // In this example we're sending a GET request to a user supplied address and showing the user
        // the HTTP status code of that request
        // Try: https://aftonbladet.se and https://dn.se
        // For fun: Try sending a request to a random address that doesn't exist: http://lökasdf328asdflj2.se

        while (programRunning) {

            System.out.println("[Example 1] What URL would you like to send a request to?");
            System.out.print("> ");
            userString = myScanner.nextLine();

            System.out.println("Sending a request to " + userString);

            try {
                url = new URL(userString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // See https://developer.mozilla.org/en-US/docs/Web/HTTP/Status for information about status codes
                int status = connection.getResponseCode();

                System.out.println("Got status: " + status);
            } catch (Exception e) {
                System.out.println("Something bad happened!");
                e.printStackTrace();
            }
        }
    }
}

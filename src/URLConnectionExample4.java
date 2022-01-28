import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionExample4 {
    public static void main(String[] argv) {
        boolean programRunning = true;
        Scanner myScanner = new Scanner(System.in);
        String userString;

        // URL objects
        URL url;
        HttpURLConnection connection;

        // In this example we're not downloading the body of the response - just the headers,
        // and displaying it to the user
        // Try the same requests as before.

        while (programRunning) {
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();

            System.out.println("[Example 4] What URL would you like to send a request to?");
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

                System.out.println("Headers for request sent to " + connection.getURL());
                System.out.println("-------------------------------------------------------");
                Map<String, List<String>> headerFields = connection.getHeaderFields();

                for (Map.Entry<String, List<String>> set : headerFields.entrySet()) {
                    String header = set.getKey();
                    for (String value : set.getValue()) {
                        System.out.println(header + ": " + value);
                    }
                }
                System.out.println("-------------------------------------------------------");
            } catch (UnknownHostException e) {
                System.out.println("Something bad happened!");
                System.out.println("Unknown host: " + e);
            } catch (Exception e) {
                System.out.println("Something bad happened!");
                e.printStackTrace();
            }
        }
    }
}

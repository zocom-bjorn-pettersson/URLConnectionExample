import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class URLConnectionExample2 {
    public static void main(String[] argv) {
        boolean programRunning = true;
        Scanner myScanner = new Scanner(System.in);
        String userString;

        // URL objects
        URL url;
        HttpURLConnection connection;

        // This example is the same as the previous one but we've added some
        // more exception handling - try sending a request to https://akjfdoi32oasldksadlökfjasdölfkj.se

        while (programRunning) {

            System.out.println("[Example 2] What URL would you like to send a request to?");
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

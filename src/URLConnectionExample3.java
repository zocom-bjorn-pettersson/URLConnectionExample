import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class URLConnectionExample3 {
    public static void main(String[] argv) {
        boolean programRunning = true;
        Scanner myScanner = new Scanner(System.in);
        String userString;

        // URL objects
        URL url;
        HttpURLConnection connection;

        // In this example we're using StringBuilder, BufferReader, getInputStream() and getErrorStream()
        // to read the body of the HTTP response
        // Basically: We are downloading a lot of text, and using BufferReader and StringBuilder is a lot
        // faster than adding text to the end of a String, like:
        // String myDownloadedPage = "";
        // myDownloadedPage += moreDownloadedText; // ... a few thousand times

        while (programRunning) {
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();

            System.out.println("[Example 3] What URL would you like to send a request to?");
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

                // If everything went well, read page using getInputStream()
                // HTTP codes up to 299 generally means everything went well
                // and codes from 300 and up means something else.
                // 300-399 are redirects, 400-499 are "user issues" and 500-599 are server issues
                if (status < 300) {
                    if (connection.getInputStream() != null) {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        reader.close();
                    }
                } else {
                    if (connection.getErrorStream() != null) {
                        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        while ((line = reader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        reader.close();
                    }
                }

                if (responseContent.isEmpty()) {
                    System.out.println("responseContent is empty - request returned nothing in body");
                } else {
                    System.out.println(responseContent);
                }

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

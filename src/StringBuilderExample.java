public class StringBuilderExample {
    public static void main(String[] args) {
        StringBuilder myStringBuilder = new StringBuilder();

        long startTimeInMilliseconds = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            myStringBuilder.append("Message " + i + "! ");
        }

        long endTimeInMilliseconds = System.currentTimeMillis();
        long diff = endTimeInMilliseconds - startTimeInMilliseconds;
        System.out.println("[StringBuilder] Time taken: " + diff + " ms");

        // If you want to print the ridiculously long string:
        // System.out.println(myStringBuilder);

        // Then the same thing with adding strings after each other
        String myString = null;

        startTimeInMilliseconds = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            myString += "Message " + i + "! ";
        }

        endTimeInMilliseconds = System.currentTimeMillis();
        diff = endTimeInMilliseconds - startTimeInMilliseconds;
        System.out.println("[String] Time taken: " + diff + " ms");

        // If you want to print the ridiculously long string:
        // System.out.println(myString);

        System.out.println("The length of the string is: " + myString.length() + " characters");

        // In conclusion: We use StringBuilder because it's a lot more efficient when adding a lot of data to it.
        // When we're using this for HTTP connections we don't really know for sure how much information we're
        // downloading.
        // The end result is the same: We get data we can handle in our code.
    }
}

package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpUtils {
    public static String fetchData(String _url) throws IOException {
        URL url = new URL(_url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "server");

        Scanner scan = new Scanner(con.getInputStream());

        String jsonString = null;
        if (scan.hasNext()) {
            jsonString = scan.nextLine();
        }
        scan.close();
        System.out.println("\n" + jsonString + "\n");
        return jsonString;
    }
}

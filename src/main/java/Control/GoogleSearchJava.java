package Control;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleSearchJava {
    public static void main(String[] args) {
        try {
        	String apiKey = "AIzaSyBOnSAuSkYJttXij0JgLpwm15SfmcPej5c";
            String searchEngineId = "d187f3d40d0174fdd";
            String query = "berlusconi è morto";

            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String url = "https://www.googleapis.com/customsearch/v1?key=" + apiKey +
                    "&cx=" + searchEngineId + "&q=" + encodedQuery;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Gson gson = new Gson(); // Instantiate the Gson object

                JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
                JsonArray items = jsonObject.getAsJsonArray("items");
                for (JsonElement item : items) {
                    JsonObject result = item.getAsJsonObject();
                    String title = result.get("title").getAsString();
                    String link = result.get("link").getAsString();
                    System.out.println("Title: " + title);
                    System.out.println("Link: " + link);
                    System.out.println();
                }
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
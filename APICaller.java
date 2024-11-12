import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APICaller {

    public static void main(String[] args) {
        try {
            // JSON body
            String jsonBody = "{\n" +
                "    \"regionSearch\": {\n" +
                "        \"types\": [\n" +
                "            \"autonomous community\",\n" +
                "            \"borough\",\n" +
                "            \"canton\",\n" +
                "            \"city\",\n" +
                "            \"congressional district\",\n" +
                "            \"country\",\n" +
                "            \"county\",\n" +
                "            \"department\",\n" +
                "            \"governorate\",\n" +
                "            \"municipality\",\n" +
                "            \"neighborhood\",\n" +
                "            \"prefecture\",\n" +
                "            \"province\",\n" +
                "            \"postal code\",\n" +
                "            \"region\",\n" +
                "            \"state\",\n" +
                "            \"territory\",\n" +
                "            \"tv region\",\n" +
                "            \"union territory\"\n" +
                "        ]\n" +
                "    },\n" +
                "    \"priority\": \"NORMAL\",\n" +
                "    \"asynchronous\": true,\n" +
                "    \"isMobile\": false,\n" +
                "    \"saveRawData\": false,\n" +
                "    \"voiceSearchHighFidelity\": false,\n" +
                "    \"keyword\": \"pizza\",\n" +
                "    \"region\": \"Romania\",\n" +
                "    \"searchEngine\": \"google\"\n" +
                "}";

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.highvolume.georanker.com/serp/new?apikey="))
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody)) 
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Print response
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // Handle status code
            if (response.statusCode() == 200) {
                System.out.println("Success: " + response.body());
            } else {
                System.out.println("Request failed: " + response.statusCode());
            }

        } catch (IOException e) {
            System.out.println("Request failed due to an IOException: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Request was interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
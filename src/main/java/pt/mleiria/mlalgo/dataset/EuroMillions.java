package pt.mleiria.mlalgo.dataset;

import pt.mleiria.mlalgo.utils.WriteUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class EuroMillions {
    private static final Logger LOG = Logger.getLogger(EuroMillions.class.getName());

    public static void main(String[] args) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            for (int i = 2004; i < 2024; i++) {
                final String GET_URL = "https://www.lottology.com/europe/euromillions/past-draws-archive/?as=TXT&year=" + i;
                final HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(GET_URL))
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
                        .GET()
                        .build();

                final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                LOG.info("Response Code: " + response.statusCode());
                final String content = response.body();
                LOG.info("Response: " + content);
                WriteUtils.writeToFile("src/main/resources/csveuromillions_" + i + ".txt", content);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package zad02.model;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class Client {
    private static final String URL = "https://api.apilayer.com/exchangerates_data/";
    private static final String API_KEY = "z2jBVXU4tx8AREf4YaoKL5j0aBOQ7THK";
    private final OkHttpClient client = new OkHttpClient();

    public String getJson(double amount, String from, String to) {
        String url = URL + "convert?amount=" + amount + "&from=" + from + "&to=" + to;
        return fetchJson(url);
    }

    private String fetchJson(String url) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("apiKey", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                return responseBody.string();
            } else {
                throw new RuntimeException("Failed to fetch data. HTTP status code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error fetching JSON", e);
        }
    }
}

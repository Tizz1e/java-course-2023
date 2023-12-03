package edu.hw6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

public class HackerNews {
    private static final String TOP_NEWS_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String GET_MESSAGE_URL = "https://hacker-news.firebaseio.com/v0/item/";

    private HackerNews() {
    }

    @SuppressWarnings("MagicNumber")
    public static long[] hackerNewsTopStories() {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI(TOP_NEWS_URL))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();

            var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            return Arrays.stream(body.substring(1, body.length() - 1).split(","))
                .mapToLong(Long::parseLong)
                .toArray();

        } catch (IOException e) {
            return new long[0];
        } catch (InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Can't make GET request to Hacker News");
        }
    }

    @SuppressWarnings("MagicNumber")
    public static Optional<String> news(long id) {
        try {
            var request = HttpRequest.newBuilder()
                .uri(new URI(GET_MESSAGE_URL + id + ".json"))
                .GET()
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();

            var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            Pattern pattern = Pattern.compile("title\":\"(.+)\",\"type\":");
            Matcher matcher = pattern.matcher(body);

            return matcher.find() ? Optional.of(matcher.group(1)) : Optional.empty();

        } catch (URISyntaxException | InterruptedException | IOException e) {
            return Optional.empty();
        }
    }
}

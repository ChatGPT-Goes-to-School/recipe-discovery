package com.chatgptgoestoschool.recipediscovery.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Spoonacular {
  @Value("${spoonacular.api}")
  private String apiKey;

  public List<Recipe> searchRecipes(String query)
      throws JSONException, IOException, InterruptedException {
    var params = new HashMap<String, String>();
    params.put("text", query);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.spoonacular.com/recipes/complexSearch?query=" + query))
        .header("x-api-key", apiKey).GET().headers("Content-Type", "application/json").build();
    HttpResponse<String> response =
        HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    JSONArray food = new JSONObject(response.body()).getJSONArray("results");
    List<Recipe> ans = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    for (int i = 0; !food.isNull(i); i++)
      ans.add(mapper.readValue(food.getJSONObject(i).toString(), Recipe.class));

    return ans;
  }

  private HttpRequest.BodyPublisher getParamsUrlEncoded(Map<String, String> parameters) {
    String urlEncoded = parameters.entrySet().stream()
        .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
        .collect(Collectors.joining("&"));
    return HttpRequest.BodyPublishers.ofString(urlEncoded);
  }
}

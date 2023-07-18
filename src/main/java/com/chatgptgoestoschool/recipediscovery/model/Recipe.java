package com.chatgptgoestoschool.recipediscovery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "recipe")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {
  @JsonProperty("id")
  @Id
  public String id;

  @JsonProperty("title")
  @JsonAlias("annotation")
  public String title;

  @JsonProperty(value = "tag", access = JsonProperty.Access.WRITE_ONLY)
  public String tag;

  @JsonProperty("image")
  public String image;

  @JsonProperty("views")
  public int views;

  @JsonProperty("positive")
  public int positive;

  @JsonProperty("negative")
  public int negative;

  @JsonProperty("owner")
  public String owner;
}

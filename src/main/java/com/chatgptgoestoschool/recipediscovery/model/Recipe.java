package com.chatgptgoestoschool.recipediscovery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "recipe")
public class Recipe {
  @JsonProperty("id")
  @Id
  public String id;

  @JsonProperty("name")
  @JsonAlias("annotation")
  public String name;

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

}

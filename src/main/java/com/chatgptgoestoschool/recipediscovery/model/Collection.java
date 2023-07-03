package com.chatgptgoestoschool.recipediscovery.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "collection")
public class Collection {
  @JsonProperty("id")
  @Id
  public String id;

  @JsonProperty("name")
  @JsonAlias("annotation")
  public String name;

  @JsonProperty("owner")
  public String owner;

  @JsonProperty("recipes")
  public List<Recipe> recipes;
}

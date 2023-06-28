package com.chatgptgoestoschool.recipediscovery.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
  @JsonProperty("id")
  public String id;

  @JsonProperty("name")
  @JsonAlias("annotation")
  public String name;

  @JsonProperty(value = "tag", access = JsonProperty.Access.WRITE_ONLY)
  public String tag;

  @JsonProperty("image")
  public String image;

}

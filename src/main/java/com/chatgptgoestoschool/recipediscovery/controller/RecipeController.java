package com.chatgptgoestoschool.recipediscovery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.chatgptgoestoschool.recipediscovery.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/recipe")
public class RecipeController {
  @Autowired
  private RecipeService recipeService;

  @GetMapping()
  public List<Recipe> getRecipes(@RequestParam String keyword) {
    return recipeService.searchRecipe(keyword);
  }

}

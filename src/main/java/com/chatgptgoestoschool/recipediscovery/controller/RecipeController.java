package com.chatgptgoestoschool.recipediscovery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.chatgptgoestoschool.recipediscovery.exception.JSONHandlingException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotFoundException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotOwnedException;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.chatgptgoestoschool.recipediscovery.service.RecipeService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("/recipe")
public class RecipeController {
  @Autowired
  private RecipeService recipeService;

  @GetMapping
  public ResponseEntity<List<Recipe>> getRecipes(@RequestParam String keyword)
      throws JSONHandlingException {
    List<Recipe> recipes = recipeService.searchRecipe(keyword);
    return ResponseEntity.ok(recipes);
  }

  @PostMapping
  public Recipe createRecipe(@RequestBody Recipe recipe) {
    return recipeService.addRecipe(recipe);
  }

  @PutMapping
  public Recipe updateRecipe(@RequestHeader(value = "Authorization") String auth,
      @RequestBody Recipe recipe) throws RecipeNotFoundException, RecipeNotOwnedException {
    return recipeService.updateRecipe(recipe, auth);
  }

  @DeleteMapping
  public void deleteRecipe(@RequestHeader(value = "Authorization") String auth, String id)
      throws RecipeNotFoundException, RecipeNotOwnedException {
    recipeService.deleteRecipe(id, auth);
  }
}

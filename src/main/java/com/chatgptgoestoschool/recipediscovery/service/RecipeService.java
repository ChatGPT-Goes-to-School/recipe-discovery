package com.chatgptgoestoschool.recipediscovery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.chatgptgoestoschool.recipediscovery.repository.RecipeRepository;
import com.chatgptgoestoschool.recipediscovery.utils.Spoonacular;

@Service
public class RecipeService {
  @Autowired
  private RecipeRepository recipeRepository;

  @Autowired
  private Spoonacular spoonacular;


  public List<Recipe> searchRecipe(String keyword) {
    try {
      List<Recipe> recipes = spoonacular.searchRecipes(keyword);
      recipeRepository.saveAll(recipes);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
    }
    return recipeRepository.searchRecipes(keyword);
  }
}

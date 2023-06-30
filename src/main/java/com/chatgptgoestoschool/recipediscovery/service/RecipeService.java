package com.chatgptgoestoschool.recipediscovery.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.chatgptgoestoschool.recipediscovery.repository.RecipeRepository;

@Service
public class RecipeService {
  @Autowired
  private RecipeRepository recipeRepository;

  public List<Recipe> searchRecipe(String keyword) {
    return recipeRepository.searchRecipes(keyword);
  }
}

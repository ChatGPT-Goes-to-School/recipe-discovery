package com.chatgptgoestoschool.recipediscovery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotFoundException;
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

  public Recipe addRecipe(Recipe recipe) {
    try {
      return recipeRepository.save(recipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
      return null;
    }
  }

  public Recipe updateRecipe(Recipe recipe) {
    try {
      Recipe oldRecipe = recipeRepository.findById(recipe.id).orElse(null);

      if (oldRecipe == null) {
        throw new RecipeNotFoundException(" Recipe can't be found");
      }

      oldRecipe.name = recipe.name;
      oldRecipe.image = recipe.image;
      oldRecipe.tag = recipe.tag;
      return recipeRepository.save(oldRecipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
      return null;
    }
  }

  public void incrementView(String id) {
    try {
      Recipe recipe = recipeRepository.findById(id).orElse(null);

      if (recipe == null) {
        throw new RecipeNotFoundException(" Recipe can't be found");
      }

      recipe.views += 1;
      recipeRepository.save(recipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
    }
  }

  public void updatePositive(String id, boolean isIncrement) {
    try {
      Recipe recipe = recipeRepository.findById(id).orElse(null);

      if (recipe == null) {
        throw new RecipeNotFoundException(" Recipe can't be found");
      }

      if (isIncrement)
        recipe.positive += 1;
      else
        recipe.positive -= 1;

      recipeRepository.save(recipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
    }
  }

  public void updateNegative(String id, boolean isIncrement) {
    try {
      Recipe recipe = recipeRepository.findById(id).orElse(null);

      if (recipe == null) {
        throw new RecipeNotFoundException(" Recipe can't be found");
      }

      if (isIncrement)
        recipe.negative += 1;
      else
        recipe.negative -= 1;

      recipeRepository.save(recipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
    }
  }

  public void deleteRecipe(String id) {
    try {
      Recipe recipe = recipeRepository.findById(id).orElse(null);

      if (recipe == null) {
        throw new RecipeNotFoundException(" Recipe can't be found");
      }

      recipeRepository.delete(recipe);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
    }
  }
}

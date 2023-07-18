package com.chatgptgoestoschool.recipediscovery.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.chatgptgoestoschool.recipediscovery.exception.JSONHandlingException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotFoundException;
import com.chatgptgoestoschool.recipediscovery.exception.RecipeNotOwnedException;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;
import com.chatgptgoestoschool.recipediscovery.repository.RecipeRepository;
import com.chatgptgoestoschool.recipediscovery.utils.JWT;
import com.chatgptgoestoschool.recipediscovery.utils.Spoonacular;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
  @Autowired
  private final RecipeRepository recipeRepository;

  @Autowired
  private final Spoonacular spoonacular;

  @Autowired
  private final S3Service s3Service;

  @Autowired
  private final JWT jwtUtils;

  public List<Recipe> searchRecipe(String keyword) throws JSONHandlingException {
    try {
      List<Recipe> recipes = spoonacular.searchRecipes(keyword);
      log.info("Recipes: " + recipes);
      recipeRepository.saveAll(recipes);
    } catch (JSONException | IOException | InterruptedException ex) {
      throw new JSONHandlingException(ex.getMessage());
    }

    return recipeRepository.searchRecipes(keyword);
  }

  public Recipe addRecipe(Recipe recipe, MultipartFile file) {
    String url = s3Service.putObject(recipe.id, file);
    recipe.image = url;
    return recipeRepository.save(recipe);
  }

  public Recipe updateImage(Recipe recipe, MultipartFile file) {
    s3Service.deleteObject(recipe.id);
    String url = s3Service.putObject(recipe.id, file);
    recipe.image = url;
    return recipeRepository.save(recipe);
  }

  public Recipe updateRecipe(Recipe recipe, String token)
      throws RecipeNotFoundException, RecipeNotOwnedException {
    Recipe oldRecipe = recipeRepository.findById(recipe.id).orElse(null);

    if (oldRecipe == null) {
      throw new RecipeNotFoundException(" Recipe can't be found");
    }

    if (!validateOwner(recipe.owner, token)) {
      throw new RecipeNotOwnedException("Recipe is not owned by the user");
    }

    oldRecipe.title = recipe.title;
    oldRecipe.image = recipe.image;
    oldRecipe.tag = recipe.tag;
    return recipeRepository.save(oldRecipe);
  }

  public void deleteRecipe(String id, String token)
      throws RecipeNotFoundException, RecipeNotOwnedException {
    Recipe recipe = recipeRepository.findById(id).orElse(null);

    if (recipe == null) {
      throw new RecipeNotFoundException(" Recipe can't be found");
    }

    if (!validateOwner(recipe.owner, token)) {
      throw new RecipeNotOwnedException("Recipe is not owned by the user");
    }

    s3Service.deleteObject(id);
    recipeRepository.delete(recipe);
  }

  public boolean validateOwner(String id, String token) {
    try {
      Jws<Claims> claims = jwtUtils.decodeToken(token);
      String uid = claims.getBody().get("userId").toString();

      return id.equals(uid);
    } catch (Exception ex) {
      System.out.println("An error occured " + ex.getMessage());
      return false;
    }
  }

  public void incrementView(String id) throws RecipeNotFoundException {
    Recipe recipe = recipeRepository.findById(id).orElse(null);

    if (recipe == null) {
      throw new RecipeNotFoundException(" Recipe can't be found");
    }

    recipe.views += 1;
    recipeRepository.save(recipe);

  }

  public void updatePositive(String id, boolean isIncrement) throws RecipeNotFoundException {
    Recipe recipe = recipeRepository.findById(id).orElse(null);

    if (recipe == null) {
      throw new RecipeNotFoundException(" Recipe can't be found");
    }

    if (isIncrement)
      recipe.positive += 1;
    else
      recipe.positive -= 1;

    recipeRepository.save(recipe);
  }

  public void updateNegative(String id, boolean isIncrement) throws RecipeNotFoundException {
    Recipe recipe = recipeRepository.findById(id).orElse(null);

    if (recipe == null) {
      throw new RecipeNotFoundException(" Recipe can't be found");
    }

    if (isIncrement)
      recipe.negative += 1;
    else
      recipe.negative -= 1;

    recipeRepository.save(recipe);
  }
}

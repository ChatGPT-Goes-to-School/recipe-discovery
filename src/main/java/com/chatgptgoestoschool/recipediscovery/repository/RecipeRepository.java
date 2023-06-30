package com.chatgptgoestoschool.recipediscovery.repository;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;

public interface RecipeRepository extends ElasticsearchRepository<Recipe, String> {
  @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name\", \"tag\"]}}")
  List<Recipe> searchRecipes(String keyword);

}

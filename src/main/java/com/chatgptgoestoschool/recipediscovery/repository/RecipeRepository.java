package com.chatgptgoestoschool.recipediscovery.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.chatgptgoestoschool.recipediscovery.model.Recipe;

public interface RecipeRepository extends ElasticsearchRepository<Recipe, String> {

}

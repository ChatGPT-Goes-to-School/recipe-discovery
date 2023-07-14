package com.chatgptgoestoschool.recipediscovery.repository;

import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.chatgptgoestoschool.recipediscovery.model.Collection;


public interface CollectionRepository extends ElasticsearchRepository<Collection, String> {
  @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name\", \"tag\"]}}")
  public List<Collection> getCollection();
}

package com.chatgptgoestoschool.recipediscovery.repository;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.chatgptgoestoschool.recipediscovery.model.Collection;


public interface CollectionRepository extends ElasticsearchRepository<Collection, String> {
  public List<Collection> getCollection();
}

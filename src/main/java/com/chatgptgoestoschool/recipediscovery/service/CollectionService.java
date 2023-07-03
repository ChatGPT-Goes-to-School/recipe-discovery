package com.chatgptgoestoschool.recipediscovery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chatgptgoestoschool.recipediscovery.model.Collection;
import com.chatgptgoestoschool.recipediscovery.repository.CollectionRepository;

@Service
public class CollectionService {
  @Autowired
  private CollectionRepository collectionRepository;

  public List<Collection> getCollection() {
    return collectionRepository.getCollection();
  }
}

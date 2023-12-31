package com.chatgptgoestoschool.recipediscovery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chatgptgoestoschool.recipediscovery.model.Collection;
import com.chatgptgoestoschool.recipediscovery.repository.CollectionRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
  @Autowired
  private final CollectionRepository collectionRepository;

  public List<Collection> getCollection() {
    return collectionRepository.getCollection();
  }
}

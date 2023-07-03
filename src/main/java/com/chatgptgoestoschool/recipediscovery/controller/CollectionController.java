package com.chatgptgoestoschool.recipediscovery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.chatgptgoestoschool.recipediscovery.model.Collection;
import com.chatgptgoestoschool.recipediscovery.service.CollectionService;

@Controller("/collection")
public class CollectionController {
  @Autowired
  private CollectionService collectionService;

  @GetMapping
  public ResponseEntity<List<Collection>> getCollection() {
    return ResponseEntity.ok(collectionService.getCollection());
  }
}

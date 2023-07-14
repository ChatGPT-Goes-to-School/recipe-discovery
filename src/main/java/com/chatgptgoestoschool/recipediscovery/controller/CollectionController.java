package com.chatgptgoestoschool.recipediscovery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatgptgoestoschool.recipediscovery.model.Collection;
import com.chatgptgoestoschool.recipediscovery.service.CollectionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {
  @Autowired
  private final CollectionService collectionService;

  @GetMapping
  public ResponseEntity<List<Collection>> getCollection() {
    return ResponseEntity.ok(collectionService.getCollection());
  }
}

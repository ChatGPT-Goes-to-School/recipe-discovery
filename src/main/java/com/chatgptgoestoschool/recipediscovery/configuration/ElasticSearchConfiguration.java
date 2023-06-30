package com.chatgptgoestoschool.recipediscovery.configuration;

import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

public class ElasticSearchConfiguration extends ElasticsearchConfiguration {
  @Override
  public ClientConfiguration clientConfiguration() {
    return ClientConfiguration.builder().connectedTo("localhost:9200").build();
  }

}

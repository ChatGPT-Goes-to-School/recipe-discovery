package com.chatgptgoestoschool.recipediscovery.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

public class ElasticSearchConfiguration extends ElasticsearchConfiguration {
  @Value("${ELASTICSEARCH_HOST}")
  private String hostname;

  @Value("${ELASTICSEARCH_PORT}")
  private String port;


  @Override
  public ClientConfiguration clientConfiguration() {
    return ClientConfiguration.builder().connectedTo(String.format("%s:%s", hostname, port))
        .build();
  }

}

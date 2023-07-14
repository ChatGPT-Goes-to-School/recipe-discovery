package com.chatgptgoestoschool.recipediscovery.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(
    basePackages = "com.chatgptgoestoschool.recipediscovery.repository")
public class ElasticSearchConfiguration extends ElasticsearchConfiguration {
  @Value("${elasticsearch.host}")
  private String elasticsearchHost;

  @Value("${elasticsearch.port}")
  private int elasticsearchPort;

  @Override
  public ClientConfiguration clientConfiguration() {
    HttpHeaders compatibilityHeaders = new HttpHeaders();
    compatibilityHeaders.add("Content-Type", "application/json");
    compatibilityHeaders.add("X-Elastic-Product", "Elasticsearch");

    ClientConfiguration clientConfiguration = ClientConfiguration.builder()
        .connectedTo(String.format("%s:%s", elasticsearchHost, elasticsearchPort))
        .withDefaultHeaders(compatibilityHeaders).build();
    return clientConfiguration;
  }
}

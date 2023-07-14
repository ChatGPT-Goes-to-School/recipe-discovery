package com.chatgptgoestoschool.recipediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
public class RecipeDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeDiscoveryApplication.class, args);
	}

}

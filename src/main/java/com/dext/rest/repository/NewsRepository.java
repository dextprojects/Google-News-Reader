package com.dext.rest.repository;


import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dext.rest.entity.Articles;

@Document(collection = "Articles")
public interface NewsRepository extends MongoRepository<Articles, String> {

	Optional<Articles> findByTitle(String title);
}

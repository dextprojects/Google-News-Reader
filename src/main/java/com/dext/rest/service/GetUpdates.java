package com.dext.rest.service;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dext.rest.entity.Articles;
import com.dext.rest.entity.NewsEntity;
import com.dext.rest.service.repository.NewsRepository;

@Service
public class GetUpdates {
	@Autowired
	NewsRepository newsRepository;

	@Scheduled(cron = "*/10 * * * * *")
	public void getNewsUpdates() {
		System.out.println("::Getting Data from Google News for BBC News::");
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(
				"https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=19c8a10fe7d24f3fb3a7928f1132e84e");

		Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = builder.get();
		System.out.println("Response Status: " + response.getStatus());
		NewsEntity newsEntities = response.readEntity(NewsEntity.class);
		Arrays.stream(newsEntities.getArticles()).forEach(article -> {
			if (!newsRepository.findByTitle(article.getTitle()).isPresent())
				newsRepository.save(article);
		});
		System.out.println("::Data Stored in DB::");
		System.out.println("Total Document stored in DB: " + newsRepository.count());
	}

}

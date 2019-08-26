package com.dext.rest.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dext.rest.entity.Articles;
import com.dext.rest.entity.NewsEntity;

@Service
public class GetUpdates {
	
	@Scheduled(cron = "*/10 * * * * *")
	public void getNewsUpdates() {
		System.out.println("::Cron::");
		Client client = ClientBuilder.newClient();

		WebTarget webTarget = client
				.target("https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=19c8a10fe7d24f3fb3a7928f1132e84e");

		Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = builder.get();
		System.out.println("response: " + response.getStatus());
		System.out.println("response: " + response.getMediaType());
		NewsEntity newsEntities = response.readEntity(NewsEntity.class);
		for(Articles article: newsEntities.getArticles()){
			System.out.println("\nArticle Title: "+article.getTitle());
			System.out.println("Article Description: "+article.getDescription()+"\n");
		}
	}

}

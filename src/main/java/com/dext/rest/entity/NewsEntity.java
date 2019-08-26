package com.dext.rest.entity;

public class NewsEntity {
	String status;
	int totalResults;
	Articles articles[];

	public Articles[] getArticles() {
		return articles;
	}

	public void setArticles(Articles[] articles) {
		this.articles = articles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

}

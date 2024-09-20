package com.microservices.quiz_service.model;

public class QuizDto {
	
	private String categoryName;
	
	private Integer numOfQuestions;
	
	private String title;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getNumOfQuestions() {
		return numOfQuestions;
	}

	public void setNumOfQuestions(Integer numOfQuestions) {
		this.numOfQuestions = numOfQuestions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}

package com.hv.quiz.DTO;

import java.util.List;

public class QuizDTO {
	
	private Integer qid;
	private String name;
	private List<QuestionsDTO> questions;
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QuestionsDTO> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionsDTO> questions) {
		this.questions = questions;
	}
	
	

}

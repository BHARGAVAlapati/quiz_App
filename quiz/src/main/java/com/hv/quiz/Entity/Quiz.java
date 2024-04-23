package com.hv.quiz.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Quiz_Details")
public class Quiz {
	
	@Id
	private Integer id;
	private String name;
	private String questions;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestion(String question) {
		this.questions = question;
	}
	public Quiz(Integer id, String name, String question) {
		super();
		this.id = id;
		this.name = name;
		this.questions = question;
	}
	
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", question=" + questions + "]";
	}
	
	

}

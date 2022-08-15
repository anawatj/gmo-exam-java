package com.gmo.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "answer_desc", nullable = true)
	private String answerDesc;

	@Column(name = "answer_score", nullable = true)
	private Integer answerScore;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}

	public Integer getAnswerScore() {
		return answerScore;
	}

	public void setAnswerScore(Integer answerScore) {
		this.answerScore = answerScore;
	}

}

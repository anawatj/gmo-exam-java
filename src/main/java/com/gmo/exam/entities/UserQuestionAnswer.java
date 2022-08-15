package com.gmo.exam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_question_answers")
public class UserQuestionAnswer {

	@Id
	@Column(name = "id")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="answer_id")
	private Answer answer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	
}

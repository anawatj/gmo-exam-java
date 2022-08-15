package com.gmo.exam.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_questions")
public class UserQuestion {

	@Id
	@Column(name = "id")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = true)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="question_id",nullable = true)
	private Question question;
	
	public Set<UserQuestionAnswer> getUserQuestionAnswers() {
		return userQuestionAnswers;
	}

	public void setUserQuestionAnswers(Set<UserQuestionAnswer> userQuestionAnswers) {
		this.userQuestionAnswers = userQuestionAnswers;
	}

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserQuestionStatus status;
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.DETACH})
	@JoinColumn(name = "user_question_id")
	private Set<UserQuestionAnswer> userQuestionAnswers;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserQuestionStatus getStatus() {
		return status;
	}

	public void setStatus(UserQuestionStatus status) {
		this.status = status;
	}
	
}

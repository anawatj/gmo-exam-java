package com.gmo.exam.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

		@Id
		@Column(name = "id")
		private String id;
		
		
		@Column(name = "question_desc")
		private String questionDesc;
		
		@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL,CascadeType.DETACH})
		@JoinColumn(name = "question_id")
		private Set<Answer> answers;
		


		public Set<Answer> getAnswers() {
			return answers;
		}


		public void setAnswers(Set<Answer> answers) {
			this.answers = answers;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getQuestionDesc() {
			return questionDesc;
		}


		public void setQuestionDesc(String questionDesc) {
			this.questionDesc = questionDesc;
		}
		
	
		
}

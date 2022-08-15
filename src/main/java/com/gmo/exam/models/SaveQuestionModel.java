package com.gmo.exam.models;

import java.util.List;

public class SaveQuestionModel {
		private String id;
		private String userName;
		private String questionId;
		private List<SaveAnswerModel> answers;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getQuestionId() {
			return questionId;
		}
		public void setQuestionId(String questionId) {
			this.questionId = questionId;
		}
		public List<SaveAnswerModel> getAnswers() {
			return answers;
		}
		public void setAnswers(List<SaveAnswerModel> answers) {
			this.answers = answers;
		}
		
}

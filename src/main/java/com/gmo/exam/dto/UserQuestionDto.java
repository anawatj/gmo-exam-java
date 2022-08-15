package com.gmo.exam.dto;

import java.util.List;

public class UserQuestionDto {

		private String id;
		private String questionId;
		private String userId;
		private String userName;
		private List<UserQuestionAnswerDto> userQuestionAnswers;
		public List<UserQuestionAnswerDto> getUserQuestionAnswers() {
			return userQuestionAnswers;
		}
		public void setUserQuestionAnswers(List<UserQuestionAnswerDto> userQuestionAnswers) {
			this.userQuestionAnswers = userQuestionAnswers;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getQuestionId() {
			return questionId;
		}
		public void setQuestionId(String questionId) {
			this.questionId = questionId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
}

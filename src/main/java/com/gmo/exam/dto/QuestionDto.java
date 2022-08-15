package com.gmo.exam.dto;

import java.util.List;

public class QuestionDto {

	private String id;
	private String questionDesc;
	private List<AnswerDto> answers;
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
	public List<AnswerDto> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDto> answers) {
		this.answers = answers;
	}
	
}

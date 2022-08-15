package com.gmo.exam.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmo.exam.models.LoadSaveQuestionModel;
import com.gmo.exam.models.SaveQuestionModel;
import com.gmo.exam.services.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	
	@RequestMapping(method = RequestMethod.GET,path="/api/quiz")
	public @ResponseBody Map<String,Object> getQuiz() throws Exception {
		return questionService.loadQuestion();
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/api/load")
	public @ResponseBody Map<String,Object> loadSaveQuestion(@RequestBody LoadSaveQuestionModel model) throws Exception {
		return questionService.loadSavedQuestion(model);
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/api/save")
	public @ResponseBody Map<String,Object> saveQuestion(@RequestBody List<SaveQuestionModel> models) throws Exception {
		try {
			return questionService.saveQuestion(models);
		}catch(Exception ex) {
			Map<String,Object> map = new HashMap<>();
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error",ex.getMessage());
			return map;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,path="/api/submit")
	public @ResponseBody Map<String,Object> submitQuestion(@RequestBody List<SaveQuestionModel> models) throws Exception {
		try {
			return questionService.submitQuestion(models);
		}catch(Exception ex) {
			Map<String,Object> map = new HashMap<>();
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error",ex.getMessage());
			return map;
		}
	}
	@RequestMapping(method=RequestMethod.POST,path="/api/summary")
	public @ResponseBody Map<String,Object> summaryQuestion(@RequestBody LoadSaveQuestionModel model) throws Exception {
		return questionService.summaryQuestion(model);
	}
	
}

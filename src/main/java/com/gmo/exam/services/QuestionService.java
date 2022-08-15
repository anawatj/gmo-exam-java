package com.gmo.exam.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmo.exam.dao.SummaryDao;
import com.gmo.exam.dto.AnswerDto;
import com.gmo.exam.dto.QuestionDto;
import com.gmo.exam.dto.SummaryDto;
import com.gmo.exam.dto.UserQuestionAnswerDto;
import com.gmo.exam.dto.UserQuestionDto;
import com.gmo.exam.entities.Answer;
import com.gmo.exam.entities.Question;
import com.gmo.exam.entities.User;
import com.gmo.exam.entities.UserQuestion;
import com.gmo.exam.entities.UserQuestionAnswer;
import com.gmo.exam.entities.UserQuestionStatus;
import com.gmo.exam.models.LoadSaveQuestionModel;
import com.gmo.exam.models.SaveQuestionModel;
import com.gmo.exam.repositories.IAnswerRepository;
import com.gmo.exam.repositories.IQuestionRepository;
import com.gmo.exam.repositories.IUserQuestionAnswerRepository;
import com.gmo.exam.repositories.IUserQuestionRepository;
import com.gmo.exam.repositories.IUserRepository;

@Service
public class QuestionService {

	@Autowired
	private IQuestionRepository questionRepository;
	
	@Autowired
	private IUserQuestionRepository userQuestionRepository;
	
	@Autowired 
	private IUserQuestionAnswerRepository userQuestionAnswerRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IAnswerRepository answerRepository;
	
	@Autowired
	private SummaryDao summaryDao;
	
	

	
	public Map<String,Object> loadQuestion() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<Question> questions = questionRepository.findAll();
			List<QuestionDto> dtos = questions.stream().map(question->{
				QuestionDto dto  =new QuestionDto();
				dto.setId(question.getId());
				dto.setQuestionDesc(question.getQuestionDesc());
				List<AnswerDto> items = question.getAnswers().stream().map(answer->{
					AnswerDto item = new AnswerDto();
					item.setId(answer.getId());
					item.setAnswerDesc(answer.getAnswerDesc());
					return item;
				}).toList();
				dto.setAnswers(items);
				return dto;
			}).toList();
			map.put("status",HttpStatus.OK);
			map.put("data", dtos);
			return map;
			
		}catch(Exception ex) {
			map.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error", ex.getMessage());
			return map;
		}
	}
	public Map<String,Object> loadSavedQuestion(LoadSaveQuestionModel model) throws Exception {
		Map<String,Object> map = new HashMap<>();
		try {
			List<UserQuestion> userQuestions = userQuestionRepository.findByUserName(model.getUserName());
			List<UserQuestionDto> dtos = userQuestions.stream().map(userQuestion->{
				UserQuestionDto dto = new UserQuestionDto();
				dto.setId(userQuestion.getId());
				dto.setQuestionId(userQuestion.getQuestion().getId());
				dto.setUserId(userQuestion.getUser().getId());
				dto.setUserName(userQuestion.getUser().getUserName());
				List<UserQuestionAnswer> userQuestionAnswers = userQuestionAnswerRepository.findByUsserQuestionId(dto.getId());
				List<UserQuestionAnswerDto> items = userQuestionAnswers.stream().map(userQuestionAnswer->{
					UserQuestionAnswerDto item = new UserQuestionAnswerDto();
					item.setId(userQuestionAnswer.getId());
					item.setAnswerId(userQuestionAnswer.getAnswer().getId());
					return item;
				}).toList();
				dto.setUserQuestionAnswers(items);
				return dto;
			}).toList();
			if(dtos.size()==0) {
				map.put("status",HttpStatus.NOT_FOUND);
				map.put("error", "not found");
				return map;
			}
			map.put("status", HttpStatus.OK);
			map.put("data", dtos);
			return map;
		
			
		}catch(Exception ex) {
			map.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error", ex.getMessage());
			return map;
		}
	}
	@Transactional(rollbackFor = Exception.class)
	public Map<String,Object> saveQuestion(List<SaveQuestionModel> models) throws Exception {
		System.out.println("Start");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<SaveQuestionModel> results = models.stream().map(m->{
				List<User> users = userRepository.findByUserName(m.getUserName());
				User user = users.get(0);
				Optional<Question> question = questionRepository.findById(m.getQuestionId());
				if(question.isEmpty()) {
					map.put("status", HttpStatus.BAD_REQUEST);
					map.put("error","question not found");
				}else {
					System.out.println("Step1");
					System.out.println(user.getId());
				
					if(m.getId()==null || m.getId().equals("")) {
						System.out.println(question.get().getId());
						UserQuestion userQuestion =  new UserQuestion();
						userQuestion.setId(UUID.randomUUID().toString());
						System.out.println("Step2");
						userQuestion.setUser(user);
						userQuestion.setQuestion(question.get());
						System.out.println("Step3");
						userQuestion.setStatus(UserQuestionStatus.Save);
						System.out.println("Step4");
						List<UserQuestionAnswer> answers = m.getAnswers().stream().map(item->{
							System.out.println("answer 1");
							UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
							userQuestionAnswer.setId(UUID.randomUUID().toString());
							System.out.println("answer 2");
							System.out.println(item.getAnswerId());
							Optional<Answer> answer = answerRepository.findById(item.getAnswerId());
							if(answer.isEmpty()) {
								map.put("status", HttpStatus.BAD_REQUEST);
								map.put("error","question not found");
							}else {
								System.out.println("answer 3");
								System.out.println(answer.get().getId());
								userQuestionAnswer.setAnswer(answer.get());
							}
						
							return userQuestionAnswer;
						}).toList();
						 Set<UserQuestionAnswer> sets = new HashSet<>(answers);
						userQuestion.setUserQuestionAnswers(sets);
						userQuestionRepository.save(userQuestion);
						m.setId(userQuestion.getId());
						
					}else {
						UserQuestion userQuestion =  new UserQuestion();
						userQuestion.setId(m.getId());
						userQuestion.setUser(user);
						userQuestion.setQuestion(question.get());
						userQuestion.setStatus(UserQuestionStatus.Save);
						List<UserQuestionAnswer> answers = m.getAnswers().stream().map(item->{
							UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
							userQuestionAnswer.setId(item.getId());
							Optional<Answer> answer = answerRepository.findById(item.getAnswerId());
							if(answer.isEmpty()) {
								map.put("status", HttpStatus.BAD_REQUEST);
								map.put("error","question not found");
							}else {
								userQuestionAnswer.setAnswer(answer.get());
							}
							
							return userQuestionAnswer;
						}).toList();
						 Set<UserQuestionAnswer> sets = new HashSet<>(answers);
						userQuestion.setUserQuestionAnswers(sets);
						userQuestionRepository.save(userQuestion);
						
					}
					
				}
				return m ;
			
			}).toList();
			if(map.get("status")!=null && map.get("status").equals(HttpStatus.BAD_REQUEST)) {
				return map;
			}
			map.put("status", HttpStatus.OK);
			map.put("data", results);
			return map;
			
		}catch(Exception ex) {
			throw ex;
		}
	
	}
	@Transactional
	public Map<String,Object> submitQuestion(List<SaveQuestionModel> models) throws Exception{
		System.out.println("Start");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<SaveQuestionModel> results = models.stream().map(m->{
				List<User> users = userRepository.findByUserName(m.getUserName());
				User user = users.get(0);
				Optional<Question> question = questionRepository.findById(m.getQuestionId());
				if(question.isEmpty()) {
					map.put("status", HttpStatus.BAD_REQUEST);
					map.put("error","question not found");
				}else {
					System.out.println("Step1");
					System.out.println(user.getId());
				
					if(m.getId()==null || m.getId().equals("")) {
						System.out.println(question.get().getId());
						UserQuestion userQuestion =  new UserQuestion();
						userQuestion.setId(UUID.randomUUID().toString());
						System.out.println("Step2");
						userQuestion.setUser(user);
						userQuestion.setQuestion(question.get());
						System.out.println("Step3");
						userQuestion.setStatus(UserQuestionStatus.Submit);
						System.out.println("Step4");
						List<UserQuestionAnswer> answers = m.getAnswers().stream().map(item->{
							System.out.println("answer 1");
							UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
							userQuestionAnswer.setId(UUID.randomUUID().toString());
							System.out.println("answer 2");
							System.out.println(item.getAnswerId());
							Optional<Answer> answer = answerRepository.findById(item.getAnswerId());
							if(answer.isEmpty()) {
								map.put("status", HttpStatus.BAD_REQUEST);
								map.put("error","question not found");
							}else {
								System.out.println("answer 3");
								System.out.println(answer.get().getId());
								userQuestionAnswer.setAnswer(answer.get());
							}
						
							return userQuestionAnswer;
						}).toList();
						 Set<UserQuestionAnswer> sets = new HashSet<>(answers);
						userQuestion.setUserQuestionAnswers(sets);
						userQuestionRepository.save(userQuestion);
						m.setId(userQuestion.getId());
						
					}else {
						UserQuestion userQuestion =  new UserQuestion();
						userQuestion.setId(m.getId());
						userQuestion.setUser(user);
						userQuestion.setQuestion(question.get());
						userQuestion.setStatus(UserQuestionStatus.Submit);
						List<UserQuestionAnswer> answers = m.getAnswers().stream().map(item->{
							UserQuestionAnswer userQuestionAnswer = new UserQuestionAnswer();
							userQuestionAnswer.setId(item.getId());
							Optional<Answer> answer = answerRepository.findById(item.getAnswerId());
							if(answer.isEmpty()) {
								map.put("status", HttpStatus.BAD_REQUEST);
								map.put("error","question not found");
							}else {
								userQuestionAnswer.setAnswer(answer.get());
							}
							
							return userQuestionAnswer;
						}).toList();
						 Set<UserQuestionAnswer> sets = new HashSet<>(answers);
						userQuestion.setUserQuestionAnswers(sets);
						userQuestionRepository.save(userQuestion);
						
					}
					
				}
				return m ;
			
			}).toList();
			if(map.get("status")!=null && map.get("status").equals(HttpStatus.BAD_REQUEST)) {
				return map;
			}
			map.put("status", HttpStatus.OK);
			map.put("data", results);
			return map;
		}catch(Exception ex) {
			throw ex;
		}
			
	}
	
	public Map<String,Object> summaryQuestion(LoadSaveQuestionModel model) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			SummaryDto summaryDto = summaryDao.summaryByUserName(model.getUserName());
			map.put("status", HttpStatus.OK);
			map.put("data", summaryDto);
			return map;
		}catch(Exception ex) {
			map.put("status",HttpStatus.INTERNAL_SERVER_ERROR);
			map.put("error", ex.getMessage());
			return map;
		}
		
		
	}
}

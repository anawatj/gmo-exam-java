package com.gmo.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmo.exam.entities.UserQuestionAnswer;

@Repository
public interface IUserQuestionAnswerRepository extends JpaRepository<UserQuestionAnswer, String> {
	
	@Query(nativeQuery = true,value="SELECT uqa.id,uqa.answer_id FROM user_question_answers uqa WHERE uqa.user_question_id=?1")
	List<UserQuestionAnswer> findByUsserQuestionId(String userQuestionId);
}

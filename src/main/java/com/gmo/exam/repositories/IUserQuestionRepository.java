package com.gmo.exam.repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gmo.exam.entities.UserQuestion;

@Repository
public interface IUserQuestionRepository extends JpaRepository<UserQuestion, String> {
	
	//@Query("SELECT uq FROM UserQuestion uq JOIN uq.user u JOIN uq.userQuestionAnswers uqa WHERE u.userName=?1")
	@Query(nativeQuery = true,value="SELECT uq.id,uq.user_id,uq.question_id,uq.status FROM user_questions uq JOIN users u on uq.user_id = u.id WHERE u.user_name=?1")
	List<UserQuestion> findByUserName(String userName);

}

package com.gmo.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmo.exam.entities.Answer;
@Repository
public interface IAnswerRepository extends JpaRepository<Answer, String> {
	
	
}

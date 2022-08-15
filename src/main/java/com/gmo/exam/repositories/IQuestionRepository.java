package com.gmo.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gmo.exam.entities.Question;
@Repository
public interface IQuestionRepository extends JpaRepository<Question, String> {

}

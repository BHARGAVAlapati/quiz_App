package com.hv.quiz.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hv.quiz.Entity.QuizDetails;

public interface QuizDetailsRepository extends JpaRepository<QuizDetails, Integer> {
    @Query("select q from QuizDetails q where q.qId=:quizId")
	List<QuizDetails> findByQuizId(int quizId);

}

package com.hv.questions.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hv.questions.Entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    @Query("select q from Questions q where q.questionType=:questionType")
	List<Questions> getAllQuestionsByCategory(@Param("questionType") String questionType);

}

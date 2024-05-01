package com.hv.questions.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.hv.questions.Entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {
    @Query("select q from Questions q where q.questionType=:questionType")
	List<Questions> getAllQuestionsByCategory(@Param("questionType") String questionType);

    @Query(value="SELECT * FROM (SELECT q.*, rownum as rn FROM questions q WHERE q.question_type = :quiztype ORDER BY dbms_random.value)WHERE rn <= :numQ",nativeQuery = true)
	List<Questions> getQuizQuestions(@Param("quiztype") String quiztype,@Param("numQ") int numQ);

}

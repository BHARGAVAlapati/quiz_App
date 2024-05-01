package com.hv.quiz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hv.quiz.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}

package com.hv.questions.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hv.questions.DTO.QuestionDTO;
import com.hv.questions.DTO.QuestionsDTO;
import com.hv.questions.Entity.Questions;
import com.hv.questions.Service.QuestionsService;

@RestController
@RequestMapping("/questions")
public class QuestionsController {
	
	@Autowired
	QuestionsService qser;
	
	@GetMapping("/getQuestions")
	public ResponseEntity<List<QuestionsDTO>> getAllQuestions(){
		List<QuestionsDTO> ques=qser.getAllQuestions();
		return new ResponseEntity<>(ques,HttpStatus.OK);
	}
	
	@PostMapping("/create-questions")
	public ResponseEntity<String> createQuestions(@RequestBody QuestionsDTO questionsDTO ){
		qser.createQuestion(questionsDTO);
		return new ResponseEntity<>("Created",HttpStatus.CREATED);
	}
	
	@GetMapping("/{questionType}")
	public ResponseEntity<List<Questions>> getAllQuestionsByCategory(@PathVariable("questionType") String questionType){
		List<Questions> quesCategory=qser.getAllQuestionsByCategory(questionType);
		return new ResponseEntity<>(quesCategory,HttpStatus.OK);
	}
	
	@GetMapping("/{quiztype}-Quiz/{numQ}")
	public ResponseEntity<List<QuestionsDTO>> getQuizQuestions(@PathVariable("quiztype") String quiztype,@PathVariable("numQ") int numQ){
		List<QuestionsDTO> quizQue=qser.getQuizQuestions(quiztype,numQ);
		return new ResponseEntity<>(quizQue,HttpStatus.OK);
		
	}

}

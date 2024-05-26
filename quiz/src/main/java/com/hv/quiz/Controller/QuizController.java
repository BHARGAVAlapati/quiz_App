package com.hv.quiz.Controller;





import java.util.List;

import com.hv.quiz.DTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.hv.quiz.DTO.QuizDTO;
import com.hv.quiz.Entity.QuizDetails;
import com.hv.quiz.Service.QuizService;

@RestController
@RequestMapping("/Quiz")
public class QuizController {
	
	@Autowired
	QuizService quizSer;
	
	@PostMapping("/create-quiz/{quiztype}/{numQ}/{quizTittle}")
	ResponseEntity<List<QuizDetails>> createQuestions(@PathVariable("quiztype") String quiztype,@PathVariable("numQ") int numQ,@PathVariable("quizTittle") String quizTittle ){
		List<QuizDetails> ty= quizSer.createQuiz(quiztype,numQ,quizTittle);
		return new ResponseEntity<>(ty,HttpStatus.CREATED);
	}
	@GetMapping("/getAllQuizes")
	ResponseEntity<List<QuizDTO>> getAllQuizes(){
		List<QuizDTO> quiz=quizSer.getAllQuizes();
		return new ResponseEntity<>(quiz,HttpStatus.OK);
	}
	
	@GetMapping("/getQuizById")
	ResponseEntity<QuizDTO> getQuizById(@RequestParam("quizId") int quizId){
		QuizDTO quiz = quizSer.getQuizById(quizId);
		return new ResponseEntity<>(quiz,HttpStatus.OK);
	}

	@GetMapping("/{Id}/getResult")
	ResponseEntity<Integer> submitQuiz(@PathVariable("Id") int Id, @RequestBody List<ResponseDTO> responses){
		Integer result= quizSer.submitQuiz(Id,responses);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}

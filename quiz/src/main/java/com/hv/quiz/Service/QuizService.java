package com.hv.quiz.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hv.quiz.DTO.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hv.quiz.DTO.QuestionsDTO;
import com.hv.quiz.DTO.QuizDTO;
import com.hv.quiz.Entity.Quiz;
import com.hv.quiz.Entity.QuizDetails;
import com.hv.quiz.Repository.QuizDetailsRepository;
import com.hv.quiz.Repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	QuizRepository quizRepo;
	@Autowired
	QuizDetailsRepository quizDetailsRepo;
	
	Logger logger = LoggerFactory.getLogger(QuizService.class);

	public List<QuizDetails> createQuiz(String quizType,int numQ,String quizTittle) {
		//List<QuestionsDTO> quizques=restTemplate.getForObject("http://localhost:8080/questions/"+quizType+"-Quiz"+"/"+numQ,List.class);
		ResponseEntity<List<QuestionsDTO>> quizques1 = restTemplate.exchange(
				"http://localhost:8080/questions/"+quizType+"-Quiz"+"/"+numQ,
			    HttpMethod.GET,
			    null, // No request body required for GET
			    new ParameterizedTypeReference<List<QuestionsDTO>>() {}
		);
		List<QuestionsDTO> quizques=quizques1.getBody();
		Quiz q=new Quiz();
		q.setName(quizTittle);
		quizRepo.save(q);
		List<QuizDetails> quizDe=new ArrayList<>();
		for(QuestionsDTO questions:quizques) {
			QuizDetails qD=new QuizDetails();
			qD.setqId(q.getqId());
			qD.setQuestionId(questions.getId());
			qD.setQuestionTittle(questions.getQuestionTittle());
			qD.setOption1(questions.getOption1());
			qD.setOption2(questions.getOption2());
			qD.setOption3(questions.getOption3());
			qD.setOption4(questions.getOption4());
			qD.setQuestionType(questions.getQuestionType());
			qD.setRightAnswer(questions.getRightAnswer());
			quizDe.add(qD);
			//quizDetailsRepo.save(qD);
		}
		quizDetailsRepo.saveAllAndFlush(quizDe);
		return quizDe;
			
	}

	public List<QuizDTO> getAllQuizes() {
		List<QuizDetails> quizDetails=quizDetailsRepo.findAll();
		List<Quiz> quiz= quizRepo.findAll();
		List<QuestionsDTO> questionsDTO= quizDetails.stream().map(quizDetals->{
			QuestionsDTO questionDTO = new QuestionsDTO();
			questionDTO.setId(quizDetals.getqId());
			questionDTO.setQuestionTittle(quizDetals.getQuestionTittle());
			questionDTO.setOption1(quizDetals.getOption1());
			questionDTO.setOption2(quizDetals.getOption2());
			questionDTO.setOption3(quizDetals.getOption3());
			questionDTO.setOption4(quizDetals.getOption4());
			questionDTO.setQuestionType(quizDetals.getQuestionType());
			return questionDTO;
		}).collect(Collectors.toList());
		
		List<QuizDTO> quizDTO= quiz.stream().map(quizs->{
			QuizDTO quizData = new QuizDTO();
			quizData.setName(quizs.getName());
			quizData.setQid(quizs.getqId());
			quizData.setQuestions(questionsDTO);
			return quizData;
		}).collect(Collectors.toList());
		return quizDTO;
	}

	public QuizDTO getQuizById(int quizId) {
		Optional<Quiz> quiz= quizRepo.findById(quizId);
		List<QuizDetails> quizDetails=quizDetailsRepo.findByQuizId(quizId);
		List<QuestionsDTO> questionsDTO= quizDetails.stream().map(quizDetals->{
			QuestionsDTO questionDTO = new QuestionsDTO();
			questionDTO.setId(quizDetals.getqId());
			questionDTO.setQuestionTittle(quizDetals.getQuestionTittle());
			questionDTO.setOption1(quizDetals.getOption1());
			questionDTO.setOption2(quizDetals.getOption2());
			questionDTO.setOption3(quizDetals.getOption3());
			questionDTO.setOption4(quizDetals.getOption4());
			questionDTO.setQuestionType(quizDetals.getQuestionType());
			return questionDTO;
		}).collect(Collectors.toList());
		if(quiz.isPresent()) {
			QuizDTO quizDTO =new QuizDTO();
			quizDTO.setName(quiz.get().getName());
			quizDTO.setQid(quiz.get().getqId());
			quizDTO.setQuestions(questionsDTO);
			
			return quizDTO;
		}
		else {
			return new QuizDTO();
		}
		
	}

	public Integer submitQuiz(int id,List<ResponseDTO> responseDTO){
		QuizService qs=new QuizService();
		QuizDTO quizDTO=qs.getQuizById(id);
		int result=0;
		int count=0;
		List<QuestionsDTO> questions = quizDTO.getQuestions();
		for(ResponseDTO response: responseDTO){
			if(response.getQuestionID().equals(questions.get(count).getQuestionId()) &&
					response.getResponse().equals(questions.get(count).getRightAnswer())){

				result++;
			}
			count++;
		}
		return result;
	}
}

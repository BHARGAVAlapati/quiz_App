package com.hv.questions.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hv.questions.DTO.QuestionDTO;
import com.hv.questions.DTO.QuestionsDTO;
import com.hv.questions.Entity.Questions;
import com.hv.questions.Repository.QuestionsRepository;

@Service
public class QuestionsService {
	@Autowired
	QuestionsRepository qRepo;

	public List<QuestionsDTO> getAllQuestions() {
		List<Questions> questions=qRepo.findAll();
		List<QuestionsDTO> listqDTO=new ArrayList<>();
		for(Questions question:questions) {
			QuestionsDTO qDTO=new QuestionsDTO();
			qDTO.setId(question.getId());
			qDTO.setQuestionType(question.getQuestionType());
			qDTO.setQuestionTittle(question.getQuestionTittle());
			qDTO.setOption1(question.getOption1());
			qDTO.setOption2(question.getOption2());
			qDTO.setOption3(question.getOption3());
			qDTO.setOption4(question.getOption4());
			qDTO.setRightAnswer(question.getRightAnswer());
			qDTO.setDiffucltyLevel(question.getDiffucltyLevel());
			listqDTO.add(qDTO);
		}
		
		return listqDTO;
	}

	public void createQuestion(QuestionsDTO questionsDTO) {
		Questions q=new Questions();
		q.setDiffucltyLevel(questionsDTO.getDiffucltyLevel());
		q.setOption1(questionsDTO.getOption1());
		q.setOption2(questionsDTO.getOption2());
		q.setOption3(questionsDTO.getOption3());
		q.setOption4(questionsDTO.getOption4());
		q.setQuestionTittle(questionsDTO.getQuestionTittle());
		q.setQuestionType(questionsDTO.getQuestionType());
		q.setRightAnswer(questionsDTO.getRightAnswer());
		qRepo.saveAndFlush(q);
	}

	public List<Questions> getAllQuestionsByCategory(String questionType) {
		List<Questions> ques=qRepo.getAllQuestionsByCategory(questionType);
		return ques;
	}

	public List<QuestionDTO> getQuizQuestions(String quiztype, int numQ) {
		List<Questions> ques=qRepo.getQuizQuestions(quiztype,numQ);
		List<QuestionDTO> response=new ArrayList<>();
		for(Questions q:ques) {
			QuestionDTO quesDTO= new QuestionDTO();
			quesDTO.setId(q.getId());
			quesDTO.setQuestionTittle(q.getQuestionTittle());
			quesDTO.setQuestionType(q.getQuestionType());
			quesDTO.setOption1(q.getOption1());
			quesDTO.setOption2(q.getOption2());
			quesDTO.setOption3(q.getOption3());
			quesDTO.setOption4(q.getOption4());
			quesDTO.setDiffucltyLevel(q.getDiffucltyLevel());
			response.add(quesDTO);
		}
		return response;
	}

}

package com.microservices.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.quiz_service.feign.QuizInterface;
import com.microservices.quiz_service.model.Question;
import com.microservices.quiz_service.model.QuestionWrapper;
import com.microservices.quiz_service.model.Quiz;
import com.microservices.quiz_service.model.Response;
import com.microservices.quiz_service.repository.QuizRepository;
@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numofQues, String quizTitle) {
		// TODO Auto-generated method stub
		
		List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numofQues).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setTitle(quizTitle);
		quiz.setQuestionsIds(questionIds);
		quizRepo.save(quiz);
		
		return new ResponseEntity<String>("Created Quiz",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		
		//quiz obj by id
		Optional<Quiz> quiz = quizRepo.findById(id);
		
		//this is where elementsCollection annotation will be come into picture
		List<Integer> questionIds = quiz.get().getQuestionsIds();
		
		//getting the questions from questions service
		List<QuestionWrapper> questions = quizInterface.getQuestionsForQuiz(questionIds).getBody();
		
		
		return new ResponseEntity<List<QuestionWrapper>>(questions,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		return new ResponseEntity<>(quizInterface.getScore(responses).getBody(),HttpStatus.OK);
	}

}

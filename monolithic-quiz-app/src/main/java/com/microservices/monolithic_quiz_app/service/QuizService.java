package com.microservices.monolithic_quiz_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.monolithic_quiz_app.model.Question;
import com.microservices.monolithic_quiz_app.model.QuestionWrapper;
import com.microservices.monolithic_quiz_app.model.Quiz;
import com.microservices.monolithic_quiz_app.model.Response;
import com.microservices.monolithic_quiz_app.repository.QuestionRepository;
import com.microservices.monolithic_quiz_app.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private QuestionRepository questionRepo;

	public ResponseEntity<String> createQuiz(String category, int numofQues, String quizTitle) {
		// TODO Auto-generated method stub
		
		List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numofQues);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(quizTitle);
		quiz.setQuestions(questions);
		
		quizRepo.save(quiz);
		
		return new ResponseEntity<String>("Created Quiz",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		
		Optional<Quiz> quiz = quizRepo.findById(id);
		
		List<Question> questionsFromDB = quiz.get().getQuestions();
		
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		
		for(Question q:questionsFromDB) {
			QuestionWrapper qwrp = new QuestionWrapper();
			qwrp.setId(q.getId());
			qwrp.setQuestionTitle(q.getQuestionTitle());
			qwrp.setOption1(q.getOption1());
			qwrp.setOption2(q.getOption2());
			qwrp.setOption3(q.getOption3());
			qwrp.setOption4(q.getOption4());
			questionsForUser.add(qwrp);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		//getting the quiz questions from the quiz id;
		Optional<Quiz> quiz = quizRepo.findById(id);
		
		List<Question> questionsFromDB = quiz.get().getQuestions();
		int result = 0;
		int i=0;
		for(Response response:responses) {
			if(response.getAnswer().equals(questionsFromDB.get(i).getRightAnswer())) {
				result++;
			}
			i++;
		}
		
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}

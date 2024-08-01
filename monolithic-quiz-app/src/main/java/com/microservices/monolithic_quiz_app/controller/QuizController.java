package com.microservices.monolithic_quiz_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.monolithic_quiz_app.model.Question;
import com.microservices.monolithic_quiz_app.model.QuestionWrapper;
import com.microservices.monolithic_quiz_app.model.Response;
import com.microservices.monolithic_quiz_app.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;

	//creating the quiz
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numofQues,@RequestParam String quizTitle){
		
		return quizService.createQuiz(category,numofQues,quizTitle);
	}
	
	//getting the question from the quiz id
	@GetMapping("/getQuiz/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);
	}
	
	
	//validating the answers and returning the score which accepts the quiz id and its reponses(ques id, ques answer)
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
		return quizService.calculateResult(id,responses);
	}
	
}

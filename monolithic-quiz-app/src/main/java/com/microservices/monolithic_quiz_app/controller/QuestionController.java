package com.microservices.monolithic_quiz_app.controller;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.monolithic_quiz_app.model.Question;
import com.microservices.monolithic_quiz_app.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	//returning all the questions
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	
	//returning the questions based on category
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable("category") String category) {
		return questionService.getAllQuestionsByCategory(category);
	}
	
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String>  addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
		
	}
	
	@PutMapping("/updateQuestion")
	public ResponseEntity<String>  updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
		
	}
	
	@DeleteMapping("/deleteQuestion/{question}")
	public ResponseEntity<String>  deleteQuestion(@PathVariable("question") String question) {
		return questionService.deleteQuestion(question);
	}

}

package com.microservices.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.question_service.model.Question;
import com.microservices.question_service.model.QuestionWrapper;
import com.microservices.question_service.model.Response;
import com.microservices.question_service.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private Environment environment;
	
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
	
	//generate quiz questions
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numOfQuestions){
		return questionService.getQuestionsForQuiz(categoryName,numOfQuestions);
	}
	
	//get questions based on question ids
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds){
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionsForQuiz(questionIds);
	}
	
	
	//get score
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		
		return questionService.getScore(responses);
	}

}

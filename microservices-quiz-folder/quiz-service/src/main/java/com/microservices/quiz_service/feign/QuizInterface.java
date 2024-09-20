package com.microservices.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.quiz_service.model.QuestionWrapper;
import com.microservices.quiz_service.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
	//generate quiz questions
	
		@GetMapping("/question/generate")
		public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numOfQuestions);
		
		//get questions based on question ids
		@PostMapping("/question/getQuestions")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds);
		
		
		//get score
		@PostMapping("/question/getScore")
		public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}

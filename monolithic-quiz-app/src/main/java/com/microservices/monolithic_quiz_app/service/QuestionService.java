package com.microservices.monolithic_quiz_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.monolithic_quiz_app.model.Question;
import com.microservices.monolithic_quiz_app.repository.QuestionRepository;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestions() {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Something went wrong..."+e.getMessage());
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try {
			return new ResponseEntity<List<Question>>(questionRepository.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Something went wrong..."+e.getMessage());
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);	
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionRepository.save(question);
			return new ResponseEntity<>("Successfully added the question",HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println("Something went wrong..."+e.getMessage());
		}
		return new ResponseEntity<>("Bad Request....",HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<String> updateQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			questionRepository.save(question);
			return new ResponseEntity<>("Successfully updated the question",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Something went wrong..."+e.getMessage());
		}
		return new ResponseEntity<>("Bad Request....",HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<String> deleteQuestion(String questionTitle){
		try {
			int count = questionRepository.deleteByQuestionTitle(questionTitle);
			return new ResponseEntity<>("Successfully deleted the "+ count + " question",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("Something went wrong..."+e.getMessage());
		}
		return new ResponseEntity<>("Bad Request....",HttpStatus.BAD_REQUEST);
	}

}

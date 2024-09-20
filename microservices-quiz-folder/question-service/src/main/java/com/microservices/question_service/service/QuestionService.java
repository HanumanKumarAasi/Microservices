package com.microservices.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.question_service.model.Question;
import com.microservices.question_service.model.QuestionWrapper;
import com.microservices.question_service.model.Response;
import com.microservices.question_service.repository.QuestionRepository;



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

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numOfQuestions) {
		// TODO Auto-generated method stub
		// just getting the question ids not the entire questions
		List<Integer> questionNos = questionRepository.findRandomQuestionsByCategory(categoryName,numOfQuestions);
		
		return new ResponseEntity<List<Integer>>(questionNos,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds) {
		// TODO Auto-generated method stub
		List<QuestionWrapper> questionWrappers = new ArrayList<>();
		
		for(Integer id:questionIds) {
			Question question = questionRepository.findById(id).get();
			//wrapping the question data to the wrapper
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			questionWrappers.add(wrapper);
		}
		
		return new ResponseEntity<List<QuestionWrapper>>(questionWrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		// TODO Auto-generated method stub
		int result = 0;
		for(Response response:responses) {
			Question question = questionRepository.findById(response.getId()).get();
			if(response.getAnswer().equals(question.getRightAnswer())) {
				result++;
			}
		}
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}

}

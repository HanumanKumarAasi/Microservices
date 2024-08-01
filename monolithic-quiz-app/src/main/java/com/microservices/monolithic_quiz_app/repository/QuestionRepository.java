package com.microservices.monolithic_quiz_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.monolithic_quiz_app.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

	List<Question> findByCategory(String category);
	
	Integer deleteByQuestionTitle(String questionTitle);

	@Query(value = "select * from question q where q.category=:category order by Random() LIMIt :numofQues",nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int numofQues);
	

}

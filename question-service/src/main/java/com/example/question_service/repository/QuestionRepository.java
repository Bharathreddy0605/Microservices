package com.example.question_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.question_service.model.Question;



public interface QuestionRepository extends JpaRepository<Question, Integer>{

	List<Question> findByCategory(String category);
	
	@Query(value = "SELECT q.id FROM question q where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
	List<Integer> findRandomQuestionByCategory(String category, int numQ);
	
	
	// Native queries refer to actual database tables and columns.
	//JPQL queries refer to entity class names and their properties, not database table names and columns.
	//Use nativeQuery = true to execute raw SQL, which allows you to use functions like RAND().
}

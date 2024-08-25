package com.example.quiz_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz_service.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}

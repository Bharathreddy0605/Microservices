package com.example.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.QuizDto;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.service.QuizService;

@RestController
@RequestMapping("/Quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
	{
		return new ResponseEntity<String>(quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle()),HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return new ResponseEntity<List<QuestionWrapper>>(quizService.getQuizQuestions(id),HttpStatus.OK);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> calculateResult(@PathVariable Integer id,@RequestBody List<Response> responses)
	{
		return new ResponseEntity<Integer>(quizService.calculateResult(id,responses),HttpStatus.OK);
	}
}

package com.example.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.service.QuestionService;

@RestController
@RequestMapping("/Questions")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() 
	{
		return new ResponseEntity<List<Question>>(questionService.getAllQuestions(),HttpStatus.OK);
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
	{
		return new ResponseEntity<>(questionService.getQuestionsByCategory(category),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody  Question question)
	{
		return new ResponseEntity<>(questionService.addQuestion(question),HttpStatus.OK);
	}
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQ)
	{
		return new ResponseEntity<List<Integer>> (questionService.getQuestionsForQuiz(categoryName,numQ),HttpStatus.OK);
	}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
	{
		return new ResponseEntity<List<QuestionWrapper>> (questionService.getQuestionsFromId(questionIds),HttpStatus.OK);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return new ResponseEntity<Integer> (questionService.getScore(responses),HttpStatus.OK);
	}
}

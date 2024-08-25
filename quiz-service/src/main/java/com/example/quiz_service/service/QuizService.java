package com.example.quiz_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepo;
	
	@Autowired
	QuizInterface quizInterface;

	public String createQuiz(String category, int numQ, String title) {
		
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizRepo.save(quiz);
		return " created ";
	}

	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		
		Optional<Quiz> quiz = quizRepo.findById(id);
		List<Integer> questionIds = quiz.get().getQuestionIds();
		List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds).getBody();
		
		return questions;
	}

	public Integer calculateResult(Integer id, List<Response> responses) {
			
		return quizInterface.getScore(responses).getBody();
	}

}

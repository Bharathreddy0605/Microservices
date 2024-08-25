package com.example.question_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.repository.QuestionRepository;


@Service
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepo;
	
	public List<Question> getAllQuestions() {	
		return questionRepo.findAll();
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionRepo.findByCategory(category);
	}

	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	public List<Integer> getQuestionsForQuiz(String categoryName, Integer numQ) {
		List<Integer> questions = questionRepo.findRandomQuestionByCategory(categoryName, numQ);
		return questions;
	}

	public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id : questionIds)
		{
			questions.add(questionRepo.findById(id).get());
		}
		
		for(Question question : questions)
		{
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		return wrappers;
	}

	public int getScore(List<Response> responses) {

		int right=0;
		for(Response response : responses) {
			Question question = questionRepo.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer()))
				right++;
		}
		return right;
	}
}

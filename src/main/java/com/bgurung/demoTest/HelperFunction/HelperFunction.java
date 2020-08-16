package com.bgurung.demoTest.HelperFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;

import com.bgurung.demoTest.dao.ExamRepository;
import com.bgurung.demoTest.dao.QuestionRepository;
import com.bgurung.demoTest.model.Exam;
import com.bgurung.demoTest.model.Question;

public class HelperFunction {
	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private QuestionRepository questionRepo;
	
	public List<Question> getQuestionByExamId(Long examId) {
		System.out.println("In function");
		Optional<Exam> exam = examRepo.findById(examId);
		String testQuestions = exam.get().getTestQuestions();
		System.out.println(testQuestions.equals("NO"));
		if(testQuestions == "NO") {
			return null ;
		}
		List<Question> questions = new ArrayList<Question>() ;
		StringTokenizer tokenizer = new StringTokenizer(testQuestions, "|");
        String id = null;
        while (tokenizer.hasMoreTokens()) {
        	id = tokenizer.nextToken();
        	Question newQuestion = new Question((Question)questionRepo.findById(Long.parseLong(id)).get());
        	questions.add(newQuestion);
        }   
		return questions;
	}
	public float calculateTimeForExam(List<Question> questionList) {
		if(questionList == null) 
			return 0;
		float count = 0;
		for(Question question: questionList) {
			count = count + question.getTimeForAns();
		}
		return count;
	}
	public float calculateTotalMarksForExam(List<Question> questionList) {
		if(questionList == null) 
			return 0;
		float count = 0;
		for(Question question: questionList) {
			count = count + question.getWeight();
		}
		return count;
	}
	public String removeQuestion(String questionColection, String question) {
		System.out.println("Question collection" + questionColection);
		StringTokenizer tokenizer = new StringTokenizer(questionColection, "|");
        String newQuestionCollection = "";
        int count = 0;
        String id = null;
		while (tokenizer.hasMoreTokens()) {
			id = tokenizer.nextToken();
        	if(id.equals(question)) {
        		
        	}else {
        		if(count == 0) {
        			newQuestionCollection = newQuestionCollection.concat(id);
        		}else {
        			newQuestionCollection = newQuestionCollection.concat("|").concat(id);
        		}
        		
        	}
        	count++;
        }
		System.out.println("new question" + newQuestionCollection);
		if(newQuestionCollection == "") {
			return questionColection;
		}
		return newQuestionCollection;
	}

}

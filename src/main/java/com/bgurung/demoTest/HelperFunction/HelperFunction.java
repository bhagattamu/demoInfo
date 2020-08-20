package com.bgurung.demoTest.HelperFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;

import com.bgurung.demoTest.dao.AnswerRepository;
import com.bgurung.demoTest.dao.ExamRepository;
import com.bgurung.demoTest.dao.QuestionRepository;
import com.bgurung.demoTest.dao.ResultRepository;
import com.bgurung.demoTest.model.Answer;
import com.bgurung.demoTest.model.Exam;
import com.bgurung.demoTest.model.Question;
import com.bgurung.demoTest.model.Result;

import ch.qos.logback.core.joran.action.Action;

public class HelperFunction {
	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired 
	private AnswerRepository answerRepo;
	@Autowired 
	private ResultRepository resultRepo;
	
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
	public int calculateMark(String fullResult) {
		StringTokenizer tokenizer = new StringTokenizer(fullResult, "|");
		String qa=null;
		Long qId; // QuestionId
		Long aId; // Answer ID
		int mark = 0;
		HashMap <Long,Float> fullResultMap = new HashMap<Long,Float>();
		//LinkedList<int> list=new LinkedList<int>();
		// Seperated into questionId,answerId
		while (tokenizer.hasMoreTokens()) {
			qa = tokenizer.nextToken();
			StringTokenizer questionAndAnsSeperator = new StringTokenizer(qa,",");
			while (questionAndAnsSeperator.hasMoreTokens()) {
				qId= Long.parseLong(questionAndAnsSeperator.nextToken());
				aId= Long.parseLong(questionAndAnsSeperator.nextToken());
				
				System.out.println("question Id" + qId);
				System.out.println("Answer Id" + aId);
				Question question = questionRepo.findById(qId).get();
				Answer answer = answerRepo.findByAnswerIdAndQuestionId(aId, qId).get(0);
				if(fullResultMap.get(qId) != null) {
					System.out.println("In duplicate");
					fullResultMap.remove(qId);
					if(answer.getCorrectness()) {
						fullResultMap.put(qId, question.getWeight());	
					}else {
						fullResultMap.put(qId, (float)0);
					}
				}else {
					if(answer.getCorrectness()) {
						fullResultMap.put(qId, question.getWeight());	
					}else {
						fullResultMap.put(qId, (float)0);
					}
				}	
			}
			System.out.println("Question and answer id" +qa);
		}
		for(Entry<Long, Float> entry : fullResultMap.entrySet()) {
		    Long key = entry.getKey();
		    Float value = entry.getValue();
		    System.out.println("question Id" + key + "Mark " + value);
		    mark = mark + value.intValue();
		    // do what you have to do here
		    // In your case, another loop.
		}
		return mark;
	}
	public Boolean isPass(Long id) {
		int markObtained = getMarks(id);
		Result result = resultRepo.findById(id).get();
		int passMark = (int)examRepo.findById(result.getTestId()).get().getTestPassWeight();
		if(markObtained >= passMark) {
			return true;
		}
		return false;
	}
	public int getMarks(Long id) {
		Result result = resultRepo.findById(id).get();
		String fullResult = result.getFullResult();
		int markObtained = calculateMark(fullResult);
		return markObtained;
	}

}

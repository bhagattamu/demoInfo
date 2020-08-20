package com.bgurung.demoTest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bgurung.demoTest.dao.AnswerRepository;
import com.bgurung.demoTest.dao.DepartmentRepository;
import com.bgurung.demoTest.dao.DesignationRepository;
import com.bgurung.demoTest.dao.ExamRepository;
import com.bgurung.demoTest.dao.NotificationRepository;
import com.bgurung.demoTest.dao.QuestionRepository;
import com.bgurung.demoTest.dao.ResultRepository;
import com.bgurung.demoTest.dao.TimerRepository;
import com.bgurung.demoTest.model.Answer;
import com.bgurung.demoTest.model.Exam;
import com.bgurung.demoTest.model.Notification;
import com.bgurung.demoTest.model.Question;
import com.bgurung.demoTest.model.Result;
import com.bgurung.demoTest.model.Timer;
import com.bgurung.demoTest.HelperFunction.HelperFunction;

@Controller
public class RouteController {
	@Autowired
	private DepartmentRepository deptRepo;
	@Autowired
	private DesignationRepository designRepo;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private AnswerRepository answerRepo;
	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private NotificationRepository notificationRepo;
	@Autowired
	private HelperFunction hFunction;
	@Autowired
	private ResultRepository resultRepo;
	@Autowired
	private TimerRepository timerRepo;
	
	@RequestMapping(path="/admin/exam")
	public ModelAndView showExam(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("Exam/exam");
		mv.addObject("active", "EXAM");
		mv.addObject("departments", deptRepo.findAll());
		mv.addObject("designations", designRepo.findAll());
		mv.addObject("exam", new Exam());
		return mv;
	}

	@RequestMapping(path="/home")
	public ModelAndView showHome(HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		List<Notification> notificationList = notificationRepo.findByUserId(Long.parseLong(session.getAttribute("userid").toString()));
		int notificationUnseenCount = 0;
		for (Notification notification : notificationList) {
			if(notification.getNotificationSeen()) {
				
			}else {
				notificationUnseenCount++;
			}
		}
		System.out.println("NOtification count backend" + notificationUnseenCount );
		mv.addObject("notificationCount", notificationUnseenCount);
		mv.addObject("notifications", notificationList);
		mv.addObject("active", "HOME");
		return mv;
	}
	@RequestMapping(path="/question/exam/{id}")
	public ModelAndView showQuestionForExamById() {
		ModelAndView mv = new ModelAndView("Exam/question");
		mv.addObject("active", "QUESTION");
		return mv;
	}
	@RequestMapping(path="/answers/question/{id}", method=RequestMethod.GET)
	public ModelAndView showAnswersForQuestionById(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Exam/answer");
		System.out.println(questionRepo.findById(id).get());
		mv.addObject("question", questionRepo.findById(id).get());
		mv.addObject("answer", answerRepo.findByQuestionIds(id));
		mv.addObject("answers", new Answer());
		mv.addObject("active", "ANSWER");
		return mv;
	}
	@RequestMapping(path="/admin/question")
	public ModelAndView showQuestion() {
		ModelAndView mv = new ModelAndView("Exam/question");
		mv.addObject("departments", deptRepo.findAll());
		mv.addObject("designations", designRepo.findAll());
		mv.addObject("question", new Question());
		mv.addObject("active", "QUESTION");
		return mv;
	}
	@RequestMapping(path="/answer")
	public ModelAndView showAnswer() {
		ModelAndView mv = new ModelAndView("Exam/answer");
		mv.addObject("questions", questionRepo.findAll());
		mv.addObject("answer", answerRepo.findAll());
		mv.addObject("answers", new Answer());
		mv.addObject("active", "ANSWER");
		return mv;
	}
	@RequestMapping(path="/question/exam/{id}", method=RequestMethod.GET)
	public ModelAndView showExamDetail(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Exam/specificexam");
		mv.addObject("examid", id);
		mv.addObject("examDesc", examRepo.findById(id).get().getTestDesc());
		mv.addObject("departments", deptRepo.findAll());
		mv.addObject("designations", designRepo.findAll());
		mv.addObject("examReady", examRepo.findById(id).get().getTestComplete());
		mv.addObject("answers", answerRepo.findAll());
		mv.addObject("testtakers",examRepo.findById(id).get().getTestTakerIds());
		mv.addObject("disableForm", false);
		try {
			List<Question> questions = hFunction.getQuestionByExamId(id);
			System.out.println("get question for exam" );
			mv.addObject("examtime", hFunction.calculateTimeForExam(questions));
			mv.addObject("examMarks", hFunction.calculateTotalMarksForExam(questions));
			mv.addObject("questions", questions);
			if(hFunction.calculateTotalMarksForExam(questions) >= examRepo.findById(id).get().getTestFullWeight()) {
				mv.addObject("disableForm", true);
			}
		}catch(Exception e) {
			mv.addObject("questions", "");
		}
		
		mv.addObject("active", "EXAM");
		return mv;
	}
	// Normal User
	@RequestMapping(path="/exam")
	public ModelAndView userExamView(HttpSession session) {
		ModelAndView mv = new ModelAndView("user/exam");
		List<Exam> examList = examRepo.findAll();
		List<Exam> individualExam = new ArrayList<Exam>();

		for (Exam exam : examList) {
			if(exam.getTestComplete() == false) {
				mv.addObject("examlist", "");
			}else {
				for(int i = 0; i<exam.getTestTakerIds().split(",").length; i++) {
					String userId = String.valueOf(session.getAttribute("userid")) ;
					System.out.println(exam.getTestTakerIds().split(",")[i].equals(userId));
					if(exam.getTestTakerIds().split(",")[i].equals(userId)) {
						individualExam.add(exam);
					}
				}
			}
		}
		mv.addObject("examlist", individualExam);
		mv.addObject("active", "EXAM");
		return mv;
	}
	@RequestMapping(path="/exam/{id}")
	public ModelAndView examDash(HttpSession session, @PathVariable Long id) {
		ModelAndView mv = new ModelAndView("user/examdash");
		List<Result> examList = resultRepo.findByTestId(id);
		Long userId = Long.parseLong(session.getAttribute("userid").toString());
		
		mv.addObject("removeStartButton", false);
		try {
			List<Question> questionList = hFunction.getQuestionByExamId(id);
			mv.addObject("questions", questionList);
			mv.addObject("timeForExam", hFunction.calculateTimeForExam(questionList));
			int timeForExam = (int) hFunction.calculateTimeForExam(questionList);
			for (Result result : examList) {
				if(result.getUserId() == userId) {
					mv.addObject("resultId", result.getResultId());
					mv.addObject("fullResult",result.getFullResult());
					Date createdAt = result.getCreatedAt();

					int timeExamFinish = createdAt.getHours() * 60 * 60 + createdAt.getMinutes() * 60 + createdAt.getSeconds() + timeForExam;
					Date current = new Date();
					int currentTime = current.getHours() * 60 * 60 + current.getMinutes() * 60 + current.getSeconds();
					Timer timer = timerRepo.findByResultId(result.getResultId());
					System.out.println("Timer finish" + timer.getTimeFinish());
					if(current.getTime() > createdAt.getTime() + timeForExam * 1000 || timer.getTimeFinish()) {
						return new ModelAndView("redirect:/result/" + result.getResultId());
					}else {
						mv.addObject("removeStartButton", true);
						mv.addObject("timeForExam", timeExamFinish - currentTime);
					}
				}
			}
		}catch(Exception e) {
			mv.addObject("questions", "No Question");
			mv.addObject("timeForExam", 0);
		}
		
		
		
		mv.addObject("answers", answerRepo.findAll());
		mv.addObject("examid", id);
		//
		
		return mv;
	}
	@RequestMapping(path = "/result/{id}")
	public ModelAndView resultOfExam(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("Result/result");
		mv.addObject("active", "RESULT");
		mv.addObject("isPass",hFunction.isPass(id));
		mv.addObject("marks",hFunction.getMarks(id));
		return mv;
	}
	
	@RequestMapping(path = "/user/result")
	public ModelAndView resultsOfUser() {
		ModelAndView mv = new ModelAndView("/Result/result");
		mv.addObject("active", "RESULT");
		System.out.println("in user result");
		return mv;
	}
	
}

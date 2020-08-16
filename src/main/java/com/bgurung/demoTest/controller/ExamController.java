package com.bgurung.demoTest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.bgurung.demoTest.dao.AnswerRepository;
import com.bgurung.demoTest.dao.DepartmentRepository;
import com.bgurung.demoTest.dao.ExamRepository;
import com.bgurung.demoTest.dao.NotificationRepository;
import com.bgurung.demoTest.dao.QuestionRepository;
import com.bgurung.demoTest.dao.ResultRepository;
import com.bgurung.demoTest.dao.UserRepository;
import com.bgurung.demoTest.dao.UserRoleRepository;
import com.bgurung.demoTest.model.Answer;
import com.bgurung.demoTest.model.Department;
import com.bgurung.demoTest.model.Exam;
import com.bgurung.demoTest.model.Notification;
import com.bgurung.demoTest.model.Question;
import com.bgurung.demoTest.model.Result;
import com.bgurung.demoTest.model.Timer;
import com.bgurung.demoTest.model.User;
import com.bgurung.demoTest.util.TimeUtil;
import com.bgurung.demoTest.HelperFunction.HelperFunction;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ExamController {
	
	@Autowired
	private ExamRepository examRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserRoleRepository roleRepo;
	@Autowired
	private DepartmentRepository deptRepo;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private AnswerRepository answerRepo;
	@Autowired
	private NotificationRepository notificationRepo;
	@Autowired
	private ResultRepository resultRepo;
	@Autowired
	private TimeUtil timeUtil;
	@Autowired
	private HelperFunction hFunction;
	@Autowired
	private TimerRepository timerRepo;

	@PostMapping(path="/admin/addExam")
	public RedirectView addExam(@ModelAttribute("exam") Exam exam,HttpSession session){
		exam.setTestMakerId((Long)session.getAttribute("userid"));
		String res = examRepo.save(exam).toString();
		System.out.println("Res" + res);
		return new RedirectView("/demoTest/admin/exam");
	}
	
	@GetMapping(path="/getExamList/{id}")
	public List<Exam> getExamList(@PathVariable Long id){
		HashMap<String,String> map = new HashMap<>(); 
//		System.out.println("Get ID" + id);
//		System.out.println("In get exam list");
//		System.out.println(examRepo.findByTestMakerId(id));
//		map.put("data", "test");
		return examRepo.findByTestMakerId(id);
	}
	@GetMapping(path="/getNormUsers")
	public List<User> getNormUserList(){
		Long roleId = roleRepo.findByUserRole("User").getRoleId();
		List<User> users = userRepo.findByRoleId(roleId);
		users.forEach((user) -> user.setPassword("")); // set password to empty
		return users;
	}
	@GetMapping(path="/getAdminUsers")
	public List<User> getAdminUserList(){
		Long roleId = roleRepo.findByUserRole("Admin").getRoleId();
		List<User> users = userRepo.findByRoleId(roleId);
		users.forEach((user) -> user.setPassword("")); // set password to empty
		return users;
	}
	@GetMapping(path="/getQuestionList")
	public List<Question> getQuestionList(){
		List<Question> questions = questionRepo.findAll();
		return questions;
	}
	
	@PostMapping(path="/admin/addQuestion")
	public RedirectView addQuestion(@ModelAttribute("question") Question question,HttpSession session){
		System.out.println(question.toString());
		question.setUserId(((Long)session.getAttribute("userid")));
		String res = questionRepo.save(question).toString();
//		System.out.println("Res" + res);
		return new RedirectView("/demoTest/admin/question");
	}
	
	@PostMapping(path="addAnswer")
	public RedirectView addAnswer(@ModelAttribute("answers") Answer answer,HttpSession session){
//		System.out.println(answer.toString());
		answer.setUserId(((Long)session.getAttribute("userid")));
		String res = answerRepo.save(answer).toString();
		System.out.println("Res" + res);
		return new RedirectView("answers/question/" + answer.getQuestionIds());
	}
	
	@GetMapping(path="/getDepartment/{id}")
	public Optional<Department> getDepartment(@PathVariable Long id){
//		Department dept = 
//		System.out.println("Get ID" + id);
//		System.out.println("In get exam list");
//		System.out.println(examRepo.findByTestMakerId(id));
//		map.put("data", "test");
		return deptRepo.findById(id);
	}
	
	@GetMapping(path="/getAnswerList/question/{id}")
	public List<Answer> getAnswerListByQuestionId(@PathVariable Long id){
		if(id== 0 ) {
			System.out.println("find all");
			return answerRepo.findAll();
		}
		return answerRepo.findByQuestionIds(id);
	}
	@PostMapping(path="/admin/postQuestion")
	public Question postQuestion(@RequestBody Question question) {
		questionRepo.save(question);
		// increase time for exam when question added
		
		return question;
	}
	@PutMapping(path="/admin/putQuestionInExam/{id}")
	public Exam putQuestionInExam(@RequestBody Exam newExam, @PathVariable Long id) {
		examRepo.findById(id)
	      .map(exam -> {
//	        employee.setName(newEmployee.getName());
//	        employee.setRole(newEmployee.getRole());
	    	// update value
	    	exam.setTestQuestions(newExam.getTestQuestions());
	    	// previous value
	        return examRepo.save(exam);
	      })
	      .orElseGet(() -> {
//	        newEmployee.setId(id);
	    	  System.out.println("No exam found of that id");
	        return newExam;
	      });
		return newExam;
	}
	
	@PutMapping(path="/admin/putAnswer/{id}")
	public Answer putAnswer(@RequestBody Answer newAnswer, @PathVariable Long id) {
		answerRepo.findById(id)
			.map(answer -> {
				answer.setAnswer(newAnswer.getAnswer());
				answer.setCorrectness(newAnswer.getCorrectness());
				return answerRepo.save(answer);
			})
			.orElseGet(() -> {
				return newAnswer;
			});
		return newAnswer;
	}
	@PostMapping(path="/admin/postAnswer")
	public Answer postAnswer(@RequestBody Answer newAnswer,HttpSession session) {
		newAnswer.setUserId((Long) session.getAttribute("userid"));
		return answerRepo.save(newAnswer);
	}
	@DeleteMapping(path="/admin/deleteQuestion/{id}")
	public String deleteQuestion(@PathVariable Long id) {
		// answer delete
		answerRepo.deleteByQuestionIds(id);
		
		// question delete
		questionRepo.deleteById(id);
		return "Success Delete";
	}
	@DeleteMapping(path="/admin/deleteAnswer/{id}")
	public String deleteAnswer(@PathVariable Long id) {
		// answer delete
		answerRepo.deleteById(id);
		return "Success Delete";
	}
	@PutMapping(path="/admin/putExam/{id}")
	public Exam putExam(@RequestBody Exam newExam,@PathVariable Long id) {
		examRepo.findById(id)
			.map(exam -> {
				exam.setTestComplete(newExam.getTestComplete());
				return examRepo.save(exam);
			})
			.orElseGet(() -> {
				return newExam;
			});
		return newExam;
	}
	@PutMapping(path="/admin/putExamAfterDeleteQuestion/{id}")
	public Exam putExamAfterQuestionDelete(@RequestBody Exam newExam,@PathVariable Long id) {
		examRepo.findById(id)
			.map(exam -> {
				exam.setTestQuestions(hFunction.removeQuestion(exam.getTestQuestions(),newExam.getTestQuestions()));
				return examRepo.save(exam);
			})
			.orElseGet(() -> {
				return newExam;
			});
		return newExam;
	}
	@PostMapping(path="/admin/postNotification")
	public Notification postNotification(@RequestBody Notification newNotification) {
		return notificationRepo.save(newNotification);
	}
	
	@PostMapping(path="/user/postResult")
	public Map<String,String> postResult(@RequestBody Result result, HttpSession session) {
		HashMap<String,String> map = new HashMap<>();
		// set userId
		result.setUserId(Long.parseLong(session.getAttribute("userid").toString()));
		result = resultRepo.save(result);
		List<Question> questions = hFunction.getQuestionByExamId(result.getTestId());
		float timeForExam = hFunction.calculateTimeForExam(questions);
		map.put("timeForExam", String.valueOf(timeForExam));
		map.put("resultId", result.getResultId().toString());
		// get Questions from testid --> this should be done for security
		// java timer start
		//timeUtil.run((int) timeForExam);		
		return map;
	}
	@PutMapping(path="/user/putResult/{id}")
	public String putResult (@RequestBody Result newResult, HttpSession session,@PathVariable Long id) {
		String fullResult = newResult.getFullResult();
		System.out.println("result from frontend" + fullResult);
		resultRepo.findById(id)
			.map(result ->{
				System.out.println("Prev results" + result.getFullResult());
				if(result.getFullResult().equals("started")) {
					result.setFullResult(fullResult);
				}else {
					result.setFullResult(result.getFullResult() + "|" + fullResult);
				}
				//result.setFullResult(newResult.getFullResult());
				resultRepo.save(result);
				return "success";
			})
			.orElseGet(() -> {
				return "failed";
			});
		return "success";
	}
	@PutMapping(path="/user/startTimer")
	public String startTimer(@RequestBody Timer newTimer,HttpSession session) {
		System.out.println("In start timer");
		Long userId = Long.parseLong(session.getAttribute("userid").toString());
		Long resultId = newTimer.getResultId();
		Long examId = resultRepo.findById(resultId).get().getTestId();
		System.out.println("Result id: " + resultId);

				List<Question> questions = hFunction.getQuestionByExamId(examId);
				float timeForExam = hFunction.calculateTimeForExam(questions);
				timerRepo.save(newTimer);
				System.out.println("Time For Exam pre" + timeForExam);
				timeUtil.run((int) timeForExam);
				System.out.println("time for exam complete");
				// now update result according to full result;
				//System.out.println("Results from backend" + resultRepo.findById(resultId).get().getFullResult());
			
		
		return "Success";
	}
	
}

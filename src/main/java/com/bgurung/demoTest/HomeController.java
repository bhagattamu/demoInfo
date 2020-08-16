package com.bgurung.demoTest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.bgurung.demoTest.dao.DepartmentRepository;
import com.bgurung.demoTest.dao.DesignationRepository;
import com.bgurung.demoTest.dao.NotificationRepository;
import com.bgurung.demoTest.dao.UserRepository;
import com.bgurung.demoTest.model.Department;
import com.bgurung.demoTest.model.Designation;
import com.bgurung.demoTest.model.Notification;
import com.bgurung.demoTest.model.Question;
import com.bgurung.demoTest.model.User;
import com.bgurung.demoTest.util.MailUtil;

@Controller
public class HomeController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DepartmentRepository deptRepo;
	@Autowired
	private DesignationRepository designRepo;
	@Autowired
	private MyUserDetailsService userService;
	@Autowired
	private MailUtil mailUtil;
	@Autowired
	private NotificationRepository notificationRepo;
	
	@RequestMapping("/")
	public ModelAndView home(HttpSession session) {
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
	@RequestMapping("/register")
	public ModelAndView addUser() {
		System.out.println("Register user route");
		ModelAndView mv = new ModelAndView("registerUser");
//		List<UserRole> roleList = (List<Role>) roleRepo.findAll();
		List<Department> deptList = (List<Department>) deptRepo.findAll();
		List<Designation> designList = (List<Designation>) designRepo.findAll();
//		mv.addObject("roles", roleList);
//		mv.addObject("roles", roleList);
		mv.addObject("departments", deptList);
		mv.addObject("designations", designList);
		return mv;
	}
	@RequestMapping("/admin/register")
	public ModelAndView addAdmin() {
		System.out.println("Register admin route");
		ModelAndView mv = new ModelAndView("registerAdmin");
//		List<UserRole> roleList = (List<Role>) roleRepo.findAll();
		List<Department> deptList = (List<Department>) deptRepo.findAll();
		List<Designation> designList = (List<Designation>) designRepo.findAll();
//		mv.addObject("roles", roleList);
//		mv.addObject("roles", roleList);
		mv.addObject("departments", deptList);
		mv.addObject("designations", designList);
		return mv;
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	        UserPrincipal loggedInUser = (UserPrincipal) userService.loadUserByUsername(auth.getName());
	        System.out.println(loggedInUser.getUsername() + " is logged in!!");
	        return "redirect:/";
	    }
	    return "login";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (!(auth instanceof AnonymousAuthenticationToken)) {
	        UserPrincipal loggedInUser = (UserPrincipal) userService.loadUserByUsername(auth.getName());
	        return "redirect:/";
	    }
		return "redirect:/";
	}
	@PostMapping(path="/register")
	public RedirectView registerUser(@RequestParam MultipartFile file, @ModelAttribute User user, HttpSession session)  throws Exception{
		user.setRoleId(2); // 2 is id of normal user
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encoding using bcrypt password encoder
		try {
			userRepo.save(user);
//			System.out.println(user);
			System.out.println(file.getName());
			ServletContext context = session.getServletContext(); 
//			String path = context.getRealPath(UPLOAD_DIRECTORY);  
		    
		    List<User> userList = userRepo.findByRoleId((long) 1);
		    Set<String> emailSet = new HashSet<String>();
		    StringTokenizer userTokenizer = new StringTokenizer(user.getDepartment(), "|");
		    String[] userDeptId = new String[20];
		    String deptId = null;
		    Set<String> deptNameSet = new HashSet<String>();
		    int count = 0;
		    while(userTokenizer.hasMoreTokens()) {
		    	System.out.println("In");
		    	//System.out.println(userTokenizer.nextToken());
		    	deptId = userTokenizer.nextToken();
		    	userDeptId[count] = deptId;
		    	System.out.println("department name" + deptRepo.findById(Long.parseLong(deptId)).get().getDeptName());
		    	deptNameSet.add(deptRepo.findById(Long.parseLong(deptId)).get().getDeptName());
//		    	deptName[count] = deptRepo.findById(Long.parseLong(deptId)).get().getDeptName();
		    	count++;
		    }
		    for (User user2 : userList) {
				StringTokenizer tokenizer = new StringTokenizer(user2.getDepartment(), "|");
				String id = null;
				System.out.println("user" + user2.getUsername());
		        while (tokenizer.hasMoreTokens()) {
		        	id = tokenizer.nextToken();
		        	System.out.println("departmentid" + id);
		        	for(int i = 0; i < userDeptId.length; i++) {
		        		if(id.equals(userDeptId[i])) {
		        			System.out.println("Email send to" + user2.getEmail());
		        			emailSet.add(user2.getEmail());
		        		}
		        	}
		        	
		        }   
			}
		    String[] deptNameList = convert(deptNameSet);
		    System.out.println("Uploading file");
		    String filename = null;
		    try {
		    	 String path = "E:\\Bhagat\\javaProj\\demoTest\\src\\main\\resources\\cv";  
				    filename = file.getOriginalFilename();       
				    byte[] bytes = file.getBytes();  
				    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
				         new File(path + File.separator + user.getUsername() + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + filename)));  
				    stream.write(bytes);  
				    stream.flush();  
				    stream.close();
		    }catch(Exception e) {
		    	System.out.println(e.getMessage());
		    }
			mailUtil.sendEmail(emailSet,"Application for " + String.join(",", deptNameList),"<h1>Application forwarded by " + user.getEmail() +  " for " + user.getDesignation() + " level </h1>", file);			    
		    
		}catch(Exception e) {
			return 	new RedirectView("/demoTest/register");
		}
		return new RedirectView("/demoTest/login");
	}
	@PostMapping(path="/admin/registerAdmin")
	public RedirectView registerAdmin(@RequestParam MultipartFile file, @ModelAttribute User user, HttpSession session)  throws Exception{
		user.setRoleId(1); // 2 is id of normal user
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encoding using bcrypt password encoder
		try {
			userRepo.save(user);
//			System.out.println(user);
			System.out.println(file.getName());
			ServletContext context = session.getServletContext(); 
//			String path = context.getRealPath(UPLOAD_DIRECTORY);  
		    String path = "E:\\Bhagat\\javaProj\\demoTest\\src\\main\\resources\\cv";  
		    String filename = file.getOriginalFilename();       
		    byte[] bytes = file.getBytes();  
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
		         new File(path + File.separator + user.getUsername() + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + filename)));  
		    stream.write(bytes);  
		    stream.flush();  
		    stream.close();
		    // Send mail to hr
		}catch(Exception e) {
			return 	new RedirectView("/demoTest/register");
		}
		return new RedirectView("/demoTest/login");
	}
	public static String[] convert(Set<String> setOfString) 
    { 
  
        // Create String[] of size of setOfString 
        String[] arrayOfString = new String[setOfString.size()]; 
  
        // Copy elements from set to string array 
        // using advanced for loop 
        int index = 0; 
        for (String str : setOfString) 
            arrayOfString[index++] = str; 
  
        // return the formed String[] 
        return arrayOfString; 
    } 
}
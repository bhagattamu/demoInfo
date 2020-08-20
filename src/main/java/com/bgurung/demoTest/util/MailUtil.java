package com.bgurung.demoTest.util;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

public class MailUtil {


	@Autowired
    private JavaMailSender javaMailSender;

	public void sendEmail() {
		System.out.println("Sending email ...........................................");
		MimeMessage msg = javaMailSender.createMimeMessage();

        try {
        	// true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	        helper.setTo("bhagattamu@gmail.com");

	        helper.setSubject("Testing from Spring Boot");

	        // default = text/plain
	        //helper.setText("Check attachment for image!");

	        // true = text/html
	        helper.setText("<h1>Check attachment for image!</h1>", true);

			// hard coded a file path
	        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

	        //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        } catch(Exception e) {
        	System.out.println("Error sending email.............................");
        	System.out.println(e.getMessage());
        }
		
        javaMailSender.send(msg);


    }
	public void sendEmail(Set<String> email, String subject,String messageBody,MultipartFile fileName) {
		System.out.println("sending mail.................");
		MimeMessage msg = javaMailSender.createMimeMessage();
		String[] emailList = convert(email);
		for(int i = 0 ; i< emailList.length; i++) {
			System.out.println("Email" + emailList[i]);
		}
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(emailList);
			helper.setSubject(subject);
			helper.setText(messageBody, true);
			System.out.println(fileName);
			helper.addAttachment(fileName.getOriginalFilename(), fileName);
		}catch(Exception e) {
        	System.out.println("Error sending email.............................");
        	System.out.println(e.getMessage());
        }
		
        javaMailSender.send(msg);
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

package com.bgurung.demoTest.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }

}

package com.bgurung.demoTest.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bgurung.demoTest.ActiveUserStore;

@Controller
public class UserController {
    
    @Autowired
    ActiveUserStore activeUserStore;
 
    @GetMapping("/loggedUsers")
    public String getLoggedUsers(Locale locale, Model model) {
        model.addAttribute("users", activeUserStore.getUsers());
        System.out.println("Active users" + activeUserStore.getUsers());
        return "home";
    }
}

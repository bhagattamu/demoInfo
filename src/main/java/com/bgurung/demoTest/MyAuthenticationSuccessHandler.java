package com.bgurung.demoTest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.bgurung.demoTest.dao.UserRepository;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
    @Autowired
    ActiveUserStore activeUserStore;
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) 
      throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            LoggedUser user = new LoggedUser(authentication.getName(), activeUserStore);
            session.setAttribute("username", authentication.getName());
            session.setAttribute("userid", userRepo.findByUsername(authentication.getName()).getUserId());
        	session.setAttribute("roleid", userRepo.findByUsername(authentication.getName()).getRoleId());
        	session.setAttribute("firstname", userRepo.findByUsername(authentication.getName()).getFirstName());
        	session.setAttribute("lastname", userRepo.findByUsername(authentication.getName()).getLastName());
        }
      //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

        //since we have created our custom success handler, its up to us, to where
        //we will redirect the user after successfully login
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        // String requestUrl = savedRequest.getRedirectUrl();
        System.out.println("Url" + savedRequest);
//        response.sendRedirect(savedRequest == null ? "/demoTest" : savedRequest.getRedirectUrl()); //requestUrl!=null?requestUrl:
        response.sendRedirect("/demoTest");
    }
}

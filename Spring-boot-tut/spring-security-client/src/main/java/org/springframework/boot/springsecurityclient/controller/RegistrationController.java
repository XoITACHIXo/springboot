package org.springframework.boot.springsecurityclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springsecurityclient.entity.User;
import org.springframework.boot.springsecurityclient.event.RegistrationCompleteEvent;
import org.springframework.boot.springsecurityclient.model.UserModel;
import org.springframework.boot.springsecurityclient.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController

public class RegistrationController {

     @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
public  String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return "Success";
}
@GetMapping("/verifyRegistration")
public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User Verifies Successfully";
        }
        return "Bad User";

}

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName() + ":"+request.getServerPort()+request.getContextPath();
    }

}

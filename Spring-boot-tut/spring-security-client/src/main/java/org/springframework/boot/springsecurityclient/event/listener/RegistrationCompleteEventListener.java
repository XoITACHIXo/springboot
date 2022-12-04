package org.springframework.boot.springsecurityclient.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springsecurityclient.entity.User;
import org.springframework.boot.springsecurityclient.event.RegistrationCompleteEvent;
import org.springframework.boot.springsecurityclient.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    @Autowired
     private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create the Verification Token for user
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);
        // send Mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verify your account :{}", url);
    }
}

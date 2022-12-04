package org.springframework.boot.springsecurityclient.service;

import org.springframework.boot.springsecurityclient.entity.User;
import org.springframework.boot.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}

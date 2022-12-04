package org.springframework.boot.springsecurityclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.springsecurityclient.entity.User;
import org.springframework.boot.springsecurityclient.entity.VerificationToken;
import org.springframework.boot.springsecurityclient.model.UserModel;
import org.springframework.boot.springsecurityclient.repository.UserRepository;
import org.springframework.boot.springsecurityclient.repository.VerificationRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationRepository verificationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(user);

        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user,token);
        verificationRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationRepository.findByToken(token);
        if (verificationToken==null){
            return "invalid";
        }
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime()-cal.getTime().getTime()<=0){
            verificationRepository.delete(verificationToken);
            return "Expired";
        }
         user.setEnabled(true);
         userRepository.save(user);
         return "Valid";
    }
}

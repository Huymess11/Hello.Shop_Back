package com.alibou.security.service;

import com.alibou.security.model.dto.ChangePasswordDtos;
import com.alibou.security.model.dto.ChangeProfileDtos;
import com.alibou.security.model.entities.User;
import com.alibou.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changeProfile(ChangeProfileDtos request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        user.setAddress(request.getAddress());
        user.setFullName(request.getFullName());
        user.setTelephoneNumber(request.getTelephoneNumber());

        // save the new password
        repository.save(user);
    }

    public String changePassword(ChangePasswordDtos request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
//            throw new IllegalStateException("Wrong password");
            return "Wrong password";
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getRetypeNewPassword())) {
//            throw new IllegalStateException("Password are not the same");
            return "Password are not the same";
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
        return "changePassword successfully !!!";
    }
}

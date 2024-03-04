package com.example.Lost_And_Found;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRole("ROLE_USER");
        repo.save(userInfo);
    }
    public String searchByName(String name) {
        List<UserInfo> users = repo.findAll();
        for ( UserInfo user: users) {
            if (user.getName().equals(name) ) {
                return user.getRole();

            }

        }
        return "noname";
    }
}







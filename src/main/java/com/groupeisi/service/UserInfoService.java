package com.groupeisi.service;

import com.groupeisi.entity.UserInfo;
import com.groupeisi.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added successfully!";
    }
    @Transactional(readOnly = true)
    public Optional<UserInfo> getUserByUsername(String username){
        return repository.findByUsername(username);
    }
    @Transactional(readOnly = true)
    public List<UserInfo> getAllUsers(){
        return repository.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<UserInfo> getUserById(int id){
        return repository.findById(id);
    }
    @Transactional(readOnly = true)
    public Boolean isListEmpty(){
        return repository.findAll().isEmpty();
    }
}

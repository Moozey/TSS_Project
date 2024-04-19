package com.example.tss_project_withspring.Service;
import com.example.tss_project_withspring.User;
import com.example.tss_project_withspring.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user) {
        repository.save(user);
    }
    public User getUser(String id) {
        return repository.findById(id).orElse(null);
    }
    public List<User> getAllUsers(){
        return repository.findAll();
    }
    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}

package com.picpay.service;

import com.picpay.domain.user.User;
import com.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> get() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
          new RuntimeException("Unable to perform the request")
        );
    }

    public User post(User user) {
        repository.save(user);
        return user;
    }

    public User put(Long id, User user) {
        this.getById(id);
        deleteById(id);
        repository.save(user);
        return user;
    }

    public void deleteById(Long id) {
        this.getById(id);
        repository.deleteById(id);
    }
}

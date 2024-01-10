package com.picpay.service;

import com.picpay.domain.user.User;
import com.picpay.dto.UserDTO;
import com.picpay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getUser() {
        return repository.findAll();
    }

    public User getByIdUser(Long id) {
        return repository.findById(id).orElseThrow(() ->
          new RuntimeException("Unable to perform the request")
        );
    }

    public User postUser(User user) {
        repository.save(user);
        return user;
    }

    public User putUser(Long id, UserDTO user) {
        this.getByIdUser(id);
        deleteByIdUser(id);
        User newUser = new User(user);
        repository.save(newUser);
        return newUser;
    }

    public void deleteByIdUser(Long id) {
        this.getByIdUser(id);
        repository.deleteById(id);
    }
}

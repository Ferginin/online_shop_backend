package ru.nikolaev.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikolaev.eshop.model.User;
import ru.nikolaev.eshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return  userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

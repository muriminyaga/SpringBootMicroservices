package com.novapile.userservice.service;

import java.util.List;

import com.novapile.userservice.entity.User;
import com.novapile.userservice.repository.JdbcUserRepository;
import com.novapile.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcUserRepository jdbcUserRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public List fetchUsers() {
        return userRepository.findAll();
    }

    public List findUserPayments(Long userId) {
        return restTemplate.getForObject("http://PAYMENT-SERVICE/payments/user/"+ userId,List.class);
    }

    public int findTotalUsers() {
        return jdbcUserRepository.count();
    }
}

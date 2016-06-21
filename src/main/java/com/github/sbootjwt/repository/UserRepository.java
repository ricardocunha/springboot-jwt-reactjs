package com.github.sbootjwt.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.github.sbootjwt.entity.User;

public interface UserRepository  extends MongoRepository<User, String> {
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email,String password);
    public List<User> findAll();
}

package com.example.pizzaorderapi.repository;

import com.example.pizzaorderapi.domain.User;
import com.example.pizzaorderapi.exceptions.EtAuthException;

public interface UserRepository {
    Integer create(String firstName,String lastName,String email,String password) throws EtAuthException;

    User findByEmailAndPassword(String email,String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}

package com.example.pizzaorderapi.services;

import com.example.pizzaorderapi.domain.User;
import com.example.pizzaorderapi.exceptions.EtAuthException;

public interface UserServices {

    User validateUser(String email,String password) throws EtAuthException;

    User registerUser(String firstName,String lastName,String email,String password) throws EtAuthException;
}

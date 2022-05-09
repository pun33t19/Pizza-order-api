package com.example.pizzaorderapi.services;

import com.example.pizzaorderapi.domain.User;
import com.example.pizzaorderapi.exceptions.EtAuthException;
import com.example.pizzaorderapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServicesImpl implements UserServices{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
      if (email!=null){
          email=email.toLowerCase();
      }

      return userRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern =Pattern.compile("^(.+)@(.+)$");

        if (email!=null) email=email.toLowerCase();

        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email Format");
        }

        Integer count= userRepository.getCountByEmail(email);

        if (count>0){
            throw new EtAuthException("Email already exists");
        }

        Integer userId= userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}

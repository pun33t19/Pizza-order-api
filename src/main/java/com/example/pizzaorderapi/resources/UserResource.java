package com.example.pizzaorderapi.resources;


import com.example.pizzaorderapi.Constants;
import com.example.pizzaorderapi.domain.User;
import com.example.pizzaorderapi.services.UserServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    UserServices services;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Map<String,Object> userMap){
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");

        User user=services.validateUser(email,password);


        return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUsers(@RequestBody Map<String,Object> userMap){
        String firstName=(String) userMap.get("firstName");
        String lastName=(String) userMap.get("lastName");
        String email=(String) userMap.get("email");
        String password=(String) userMap.get("password");

        User user=services.registerUser(firstName,lastName,email,password);


        return new ResponseEntity<>(generateJWTToken(user) , HttpStatus.OK);
    }

    private Map<String,String> generateJWTToken(User user){
        long timeStamp=System.currentTimeMillis();

        String token= Jwts.builder().signWith(SignatureAlgorithm.HS256,Constants.apikey)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp+Constants.TOKEN_VALIDITY))
                .claim("userId",user.getUserId())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .claim("email",user.getEmail())
                .compact();

        Map<String,String> map=new HashMap<>();

        map.put("token",token);
        return map;
    }


}

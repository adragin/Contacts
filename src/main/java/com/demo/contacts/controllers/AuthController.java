package com.demo.contacts.controllers;

import com.demo.contacts.dto.UserLoginDTO;
import com.demo.contacts.dto.UserRegisterDTO;
import com.demo.contacts.models.ResponseModel;
import com.demo.contacts.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseModel register(@RequestBody UserRegisterDTO userRegisterDTO) {
        try {
            if (userRegisterDTO.getEmail() == null || userRegisterDTO.getPassword() == null ||
                    userRegisterDTO.getName() == null) {
                return new ResponseModel(HttpStatus.FORBIDDEN.value(), null, null);
            }
            authService.registerUser(
                    userRegisterDTO.getEmail(), userRegisterDTO.getPassword(), userRegisterDTO.getName());
            return new ResponseModel(HttpStatus.CREATED.value(), null, null);

        } catch (SQLException e) {
            return new ResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null) ;
        }
    }

    @GetMapping("/login") // 200 OK: 001ZWthdGVyaW5hQGdtYWkuY29tfDEyMzQ1Njc4OUFh
    public ResponseModel login(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            if (userLoginDTO.getEmail() == null || userLoginDTO.getPassword() == null) {

                return new ResponseModel(HttpStatus.BAD_REQUEST.value(), null, "Invalid input data");

            }
            String token = authService.authenticate(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            if (token != null) {
                return new ResponseModel(HttpStatus.OK.value(), token, null);

            } else {
                return new ResponseModel(HttpStatus.UNAUTHORIZED.value(), null, "Invalid credentials");

            }
        } catch (SQLException e) {
            return new ResponseModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Login failed");
        }
    }
}

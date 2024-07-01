package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.authentication.LoginUseCase;
import com.example.bookzon.application.usecases.authentication.RefreshUseCase;
import com.example.bookzon.application.usecases.authentication.RegisterUseCase;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.domain.enums.UserRole;
import com.example.bookzon.domain.exceptions.InvalidUserDataException;
import com.example.bookzon.domain.exceptions.UserAlreadyExistsException;
import com.example.bookzon.infrastructure.dtos.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")

public class AuthenticationController {


    private final LoginUseCase loginUseCase;
    private final RefreshUseCase refreshUseCase;
    private final RegisterUseCase registerUseCase;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, RefreshUseCase refreshUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.refreshUseCase = refreshUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginRequestDTO data){
    LoginResponseDTO tokens =   loginUseCase.execute(new User(data.username(), data.password()));
    return ResponseEntity.ok(tokens);
    }
    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponseDTO> refresh(@RequestBody @Validated RefreshRequestDTO data){
        RefreshResponseDTO tokens = refreshUseCase.execute(data.refreshToken());
        return ResponseEntity.ok(tokens);
    }
    @PostMapping("/login/cookie")
    public String login(@RequestBody @Validated LoginRequestDTO data, HttpServletRequest
            request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.username(), data.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "Login successful!";
    }
    @PostMapping("/login/cookie/logout")
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "Logout successful!";
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequestDTO data,  BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
             User user = registerUseCase.execute(new User(data.username(), data.password(), UserRole.USER));
             return ResponseEntity.ok().build();
         } catch (UserAlreadyExistsException | InvalidUserDataException ex) {
             throw ex;
         } catch (Exception ex) {
             throw new RuntimeException("An error occurred during registration", ex);
         }
    }
}

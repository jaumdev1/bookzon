package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.authentication.AuthenticateUserUseCase;
import com.example.bookzon.application.usecases.authentication.LoginUseCase;
import com.example.bookzon.application.usecases.authentication.RefreshUseCase;
import com.example.bookzon.application.usecases.authentication.RegisterUseCase;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.domain.enums.UserRole;
import com.example.bookzon.domain.exceptions.InvalidUserDataException;
import com.example.bookzon.domain.exceptions.UserAlreadyExistsException;
import com.example.bookzon.infrastructure.dtos.*;

;
import com.example.bookzon.infrastructure.security.UserIdStrategy.Factory.UserIdStrategyFactory;
import com.example.bookzon.infrastructure.security.UserIdStrategy.UserIdStrategy;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "*")
public class AuthenticationController {


    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final RegisterUseCase registerUseCase;
    @Autowired
    private UserIdStrategyFactory userIdStrategyFactory;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationController(
            AuthenticateUserUseCase authenticateUserUseCase,
            RegisterUseCase registerUseCase,
            RefreshUseCase refreshUseCase) {

        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginRequestDTO data, HttpServletRequest
            request) {
        Authentication authentication = authenticateUserUseCase.execute(data.username(), data.password());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        return "Login successful!";
    }

    @PostMapping("/logout")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return "Logout successful!";
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody RegisterRequestDTO data, BindingResult result) {
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

    @GetMapping("/user")
    public ResponseEntity<String> getCurrentUserId(HttpSession session, HttpServletRequest request) {
        UUID userId = null;
        userId = userIdStrategyFactory.getStrategy(request).getCurrentUserId();
        if (userId == null)
            userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
        if(userId == null)
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");

        return ResponseEntity.ok("User ID: " + userId);

    }

}

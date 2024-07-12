package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.authentication.*;
import com.example.bookzon.application.usecases.user.GetUserUseCase;
import com.example.bookzon.domain.entities.User;
import com.example.bookzon.domain.enums.UserRole;
import com.example.bookzon.domain.exceptions.InvalidUserDataException;
import com.example.bookzon.domain.exceptions.UserAlreadyExistsException;
import com.example.bookzon.infrastructure.dtos.*;

;
import com.example.bookzon.infrastructure.dtos.User.UserDTO;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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

    private final LoginGoogleUseCase loginGoogleUseCase;

    private final GetUserUseCase getUserUseCase;
    @Autowired
    private UserIdStrategyFactory userIdStrategyFactory;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationController(
            AuthenticateUserUseCase authenticateUserUseCase,
            RegisterUseCase registerUseCase,
            RefreshUseCase refreshUseCase, LoginGoogleUseCase loginGoogleUseCase,
            GetUserUseCase getUserUseCase

    ) {

        this.loginGoogleUseCase = loginGoogleUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.registerUseCase = registerUseCase;
        this.getUserUseCase = getUserUseCase;
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
            User user = registerUseCase.execute(new User(data.username(), data.name(), data.email(), data.password(), UserRole.USER));
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException | InvalidUserDataException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("An error occurred during registration", ex);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(HttpSession session, HttpServletRequest request) throws Exception {
        UUID userId = null;
        userId = userIdStrategyFactory.getStrategy(request).getCurrentUserId();
        if (userId == null)
            userId = userIdStrategyFactory.getStrategy(session).getCurrentUserId();
        if(userId == null)
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");

        User user = getUserUseCase.execute(userId);
        var userDto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getImageUrl()
        );
        return ResponseEntity.ok(userDto);
    }


    @GetMapping("/googleuser")
    public Map<String, Object> userEndpoint(@AuthenticationPrincipal OidcUser principal) {
        Map<String, Object> response = new HashMap<>();

        if (principal != null) {
            String googleId = principal.getSubject();
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");

            User user = loginGoogleUseCase.execute(googleId, name, email);

            response.put("id", user.getId());

        } else {
            response.put("message", "User not authenticated");
        }

        return response;
    }

    @GetMapping("/loginGoogle")
    public String login() {
        return "redirect:/oauth2/authorization/google";
    }
}

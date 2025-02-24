package ru.nikolaev.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolaev.eshop.security.JwtCore;
import ru.nikolaev.eshop.security.SigninRequest;
import ru.nikolaev.eshop.security.SignupRequest;
import ru.nikolaev.eshop.model.User;
import ru.nikolaev.eshop.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    @Autowired
    public SecurityController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtCore jwtCore) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtCore = jwtCore;
    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsUserByUsername(signupRequest.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose a different username");
        }
        if (userRepository.existsUserByEmail(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose a different email");
        }

        // Логирование исходного пароля (для отладки)
        System.out.println("Raw Password: " + signupRequest.getPassword());

        // Хэширование пароля
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // Логирование захэшированного пароля (для отладки)
        System.out.println("Hashed Password: " + hashedPassword);

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(signupRequest.getEmail());
        user.setRole("ROLE_USER");

        userRepository.save(user);

        return ResponseEntity.ok().body("Success, baby");
    }


    @PostMapping("/signin")
    ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        Authentication authentication = null;
        try {
//            System.out.println("Username: " + signinRequest.getUsername());
//            System.out.println("Password: " + signinRequest.getPassword());

            // Загрузите пользователя из базы данных
            User user = userRepository.findUserByUsername(signinRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("User not found"));

//            System.out.println("Stored password: " + user.getPassword());
//            System.out.println("Password matches: " + passwordEncoder.matches(signinRequest.getPassword(), user.getPassword()));

            // Проверьте соответствие пароля
            if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Password does not match");
            }

            // Аутентификация пользователя
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signinRequest.getUsername(),
                            signinRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtCore.generateToken(authentication);

            return ResponseEntity.ok(jwt);

        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
package ru.nikolaev.eshop.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/secured")
public class MainController {
    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null) {
            System.out.println("Principal is null");
            return "Unauthorized";
        }
        System.out.println("Authenticated user: " + principal.getName());
        return principal.getName();
    }
}

package hello.controller;

import hello.entity.User;
import hello.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class JwtAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User addUser) throws AuthenticationException {
        return ResponseEntity.ok(authService.register(addUser));
    }

    // 登录
    @PostMapping("/login")
    public ResponseEntity<String> createToken(@RequestBody User userToLogin) throws AuthenticationException {
        return ResponseEntity.ok(authService.login(userToLogin));
    }
}

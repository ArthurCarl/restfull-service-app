package hello.service;

import hello.entity.User;
import org.springframework.security.core.AuthenticationException;

public interface AuthService {
    User register( User userToAdd );

    String login(User userToLogin) throws AuthenticationException;
}

package hello.service.impl;

import hello.entity.User;
import hello.repository.UserRepository;
import hello.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User userToAdd) {
        if (userRepository.findByUsername(userToAdd.getUsername()) != null){
            return null;
        }
        userToAdd.setAccountNonExpired(true);
        userToAdd.setAccountNonLocked(true);
        userToAdd.setCredentialsNonExpired(true);
        userToAdd.setEnabled(true);
        return userRepository.save(userToAdd);
    }
}

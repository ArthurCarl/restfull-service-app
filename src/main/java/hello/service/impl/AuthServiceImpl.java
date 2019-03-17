package hello.service.impl;

import hello.entity.User;
import hello.repository.UserRepository;
import hello.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public String login(User userToLogin) throws AuthenticationException {
        User userLoad = userRepository.findByUsername(userToLogin.getUsername());
        if (!userLoad.getPassword().equals(userToLogin.getPassword())){
            throw new UsernameNotFoundException("密码错误");
        }
        return userLoad.getUsername();
    }
}

package med.voll.api.service;

import med.voll.api.domain.dtos.user.UserData;
import med.voll.api.domain.models.User;
import med.voll.api.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserData data) {
        String hashedPassword = passwordEncoder.encode(data.password());
        User user = new User(data.username(), hashedPassword);
        repository.save(user);
        return user;
    }
}

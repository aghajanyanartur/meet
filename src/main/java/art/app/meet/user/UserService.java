package art.app.meet.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.info("Getting user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        log.info("Creating and saving user: {}", user);
        return userRepository.saveAndFlush(user);
    }

    public User updateUser(Long id, User user) {
        log.info("Updating user by id: {}, with user: {}", id, user);
        var existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
    }
}
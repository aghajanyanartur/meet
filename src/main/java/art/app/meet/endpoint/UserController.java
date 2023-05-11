package art.app.meet.endpoint;

import art.app.meet.user.User;
import art.app.meet.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() {
        log.info("Endpoint getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        log.info("Endpoint getting user by id: {}", id);
        return userService.getUserById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        log.info("Endpoint creating user: {}", user);
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        log.info("Endpoint updating user by id: {}, with user: {}", id, user);
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Endpoint deleting user by id: {}", id);
        userService.deleteUser(id);

    }
}
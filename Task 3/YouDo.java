
class User {
    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

class EmailValidator {

    public boolean isValid(String email) {
        if (email != null && email.contains("@")) {
            return true;
        }

        System.out.println("Invalid email format.");
        return false;
    }
}


interface UserRepository {
    void save(User user);
}


class DatabaseUserRepository implements UserRepository {

    @Override
    public void save(User user) {
        System.out.println("Connecting to database...");
        System.out.println("Saving user " + user.getUsername() + " to the users table.");
    }
}


class UserService {

    private UserRepository userRepository;
    private EmailValidator emailValidator;

    public UserService(UserRepository userRepository,
                       EmailValidator emailValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
    }

    public void registerUser(User user) {
        if (emailValidator.isValid(user.getEmail())) {
            userRepository.save(user);
        }
    }
}


public class Main {

    public static void main(String[] args) {

        User user = new User("john", "john@example.com");

        UserRepository repository = new DatabaseUserRepository();
        EmailValidator validator = new EmailValidator();

        UserService userService =
                new UserService(repository, validator);

        userService.registerUser(user);
    }
}

 public class Main {

    
    static class User {
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

   
    static class EmailValidator {
        public boolean isValid(String email) {
            return email != null && email.contains("@");
        }
    }

    
    interface UserRepository {
        void save(User user);
    }

   
    static class DatabaseUserRepository implements UserRepository {

        @Override
        public void save(User user) {
            System.out.println("Connecting to database...");
            System.out.println(
                "Saving user " + user.getUsername() + " to the users table."
            );
        }
    }

    
    static class UserService {

        private UserRepository userRepository;
        private EmailValidator emailValidator;

        public UserService(UserRepository userRepository,
                           EmailValidator emailValidator) {
            this.userRepository = userRepository;
            this.emailValidator = emailValidator;
        }

        public void registerUser(User user) {

            if (!emailValidator.isValid(user.getEmail())) {
                System.out.println("Invalid email format.");
                return;
            }

            userRepository.save(user);
        }
    }

   
    public static void main(String[] args) {

        User user = new User("John", "john@gmail.com");

        UserRepository repository = new DatabaseUserRepository();
        EmailValidator validator = new EmailValidator();

        UserService service =
                new UserService(repository, validator);

        service.registerUser(user);
    }
}

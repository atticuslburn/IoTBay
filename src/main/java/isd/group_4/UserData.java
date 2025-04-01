package isd.group_4;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static final List<User> users = new ArrayList<>();
    static {
        users.add(new User(1, "123", "Adrian", "Irwin", "adrian@gmail.com", "04123456", "42 Wallaby way", "Sydney", "1"));
    }

    public static User authenticateUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.checkPassword(password)) {
                return user;
            }
        }
        return null;
    }
    public static List<User> getUsers() {
        return users;
    }
    public static void addUser(User user) {
        users.add(user);
    }
}


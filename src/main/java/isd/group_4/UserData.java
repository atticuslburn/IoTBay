package isd.group_4;
import java.util.ArrayList;
import java.util.*;

public class UserData {

    private static final LinkedList<User> users = new LinkedList<>();
    static {
        users.add(new User(1, "123", "Adrian", "Irwin", "adrian@gmail.com", "04123456", "42", "Wallaby way", "Sydney", "1"));
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


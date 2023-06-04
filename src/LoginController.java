import java.util.ArrayList;
import java.util.Scanner;

public class LoginController {
    static Scanner input = new Scanner(System.in);
    private ArrayList<User> usersList;

    public LoginController() {
        //  Get usersList from users file
        usersList = DBConnection.readUsersFromFile();
    }

    public void login() {
        System.out.print("USERNAME: ");
        String username = input.next();
        User user = findUser(username);
        if (user == null) {
            System.out.println("\n### username is not exists. Try again or create a new username!! ###\n");
            return;
        }
        System.out.print("Password: ");
        String password = input.next();
        if (!user.getPassword().equals(password)) {
            System.out.println("\n### wrong password!! ###\n");
            return;
        }
        User.isLogin = true;
        ConsoleMenu.ledgerAction(user.getUserName());
    }

    public void register() {
        try {
            System.out.print("USERNAME: ");
            String username = input.next();
            User user = findUser(username);
            if (user != null) {
                System.out.println("\n### username already exists. Please select another username ###");
                return;
            }
            System.out.print("Password: ");
            String password = input.next();
            user = new User(username, password);
            usersList.add(user);
            DBConnection.saveUsersIntoFile(usersList);
            User.isLogin = true;
            ConsoleMenu.ledgerAction(user.getUserName());
        } catch (IllegalArgumentException ex) {
            System.out.println("\n### " + ex.getMessage() + " ####");
        }
    }

    private User findUser(String useName) {
        for (User user : usersList) {
            if (user.getUserName().equals(useName)) {
                return user;
            }
        }
        return null;
    }
}

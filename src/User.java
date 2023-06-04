import java.util.Objects;

public class User {
    private String userName;
    private String password;
    public static boolean isLogin = false;

    public User(String userName, String password) throws IllegalArgumentException {
        setUserName(userName);
        setPassword(password);
    }

    public void setUserName(String userName) throws IllegalArgumentException {
        if (!Character.isLetter(userName.charAt(0))) {
            throw new IllegalArgumentException("UserName should start with letter");
        }
        if (userName.length() < 4) {
            throw new IllegalArgumentException("UserName should be at least 4 characters");
        }
        this.userName = userName;
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if (password.length() < 5) {
            throw new IllegalArgumentException("Password should be at least 5 characters");
        }
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String toDataString() {
        return String.format("%s;%s", this.userName, this.password);
    }
}

package login;
import lombok.Data;
@Data

public class User {
    private int userId;
    private String userName;
    private String password;
    public User() {
    }
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}

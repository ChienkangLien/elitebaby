package forum.dao;

public abstract class DaoId {

    public final static String URL = "jdbc:mysql://localhost:3306/elitebaby";
    public final static String USER = "root";
    public final static String PASSWORD = "password";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

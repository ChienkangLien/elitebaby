package forum.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class DaoId {

    public final static String URL = "jdbc:mysql://localhost:3306/elitebaby";
    public final static String USER = "root";
    public final static String PASSWORD = "password";

    protected static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}

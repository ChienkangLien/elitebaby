package com.tibame.web.dao;

import com.tibame.web.vo.User;

import java.sql.*;


public class UserDao {
    private String URL = "jdbc:mysql://localhost:3306/elitebaby";
    private String USER = "root";
    private String PASSWORD = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String userName, String password) {
        String sql = "select * from member where user_name = ? and user_password = ?;";
        User user = null;
        System.out.println("UserDao" + userName + password);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"));
            }
            System.out.println("UserDao-user: " + user);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package dao;

import java.sql.*;  
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.UserLogin;  
  

public class UserLoginDao {  
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cookie";  
    private static final String USER = "root";  
    private static final String PASSWORD = "lyn20031211";  
  
    public List<UserLogin> getUserLogins() {  
        List<UserLogin> loginList = new ArrayList<>();  
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);  
             Statement stmt = conn.createStatement();  
             ResultSet rs = stmt.executeQuery("SELECT * FROM user_login")) {  
              
            while (rs.next()) {  
                UserLogin login = new UserLogin();   
                login.setUsername(rs.getString("username"));  
                login.setLoginTime(rs.getTimestamp("logindate"));  
                login.setIpAddress(rs.getString("ip"));  
                loginList.add(login); 
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return loginList;  
    }
}

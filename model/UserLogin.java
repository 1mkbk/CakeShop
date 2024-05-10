package model;

import java.sql.Timestamp;  
  
public class UserLogin {  
    private int id;  
    private String username;  
    private Timestamp loginTime;  
    private String ipAddress;  
  
    // 构造方法（可选）  
    public UserLogin() {  
    }  
  
    public UserLogin(int id, String username, Timestamp loginTime, String ipAddress) {  
        this.id = id;  
        this.username = username;  
        this.loginTime = loginTime;  
        this.ipAddress = ipAddress;  
    }  
  
    // Getter方法  
    public int getId() {  
        return id;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public Timestamp getLoginTime() {  
        return loginTime;  
    }  
  
    public String getIpAddress() {  
        return ipAddress;  
    }  
  
    // Setter方法  
    public void setId(int id) {  
        this.id = id;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public void setLoginTime(Timestamp loginTime) {  
        this.loginTime = loginTime;  
    }  
  
    public void setIpAddress(String ipAddress) {  
        this.ipAddress = ipAddress;  
    }  
}
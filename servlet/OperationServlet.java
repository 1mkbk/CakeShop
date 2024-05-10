package servlet;

import java.io.IOException;  
import java.sql.*;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@WebServlet(name = "admin_operation",urlPatterns = "/admin/operation")
public class OperationServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Map<String, Object>> userList = new ArrayList<>();  
        try {  
            // 1. 建立数据库连接  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211"); 
            //执行查询
            String sql = "SELECT * FROM operation";  
            pstmt = conn.prepareStatement(sql);  
            rs = pstmt.executeQuery();          
            // 处理查询结果  
            while (rs.next()) {  
                Map<String, Object> user = new HashMap<>();  
                user.put("operator_name", rs.getString("operator_name"));  
                user.put("operation_time", rs.getString("operation_time"));  
                user.put("id", rs.getInt("id"));
                user.put("ip",rs.getString("ip_address"));
                user.put("operation_name",rs.getString("operation_name"));
                userList.add(user);  
            }  
            // 将数据放入请求属性中  
            request.setAttribute("userList", userList);  
              
        } catch (ClassNotFoundException | SQLException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭资源  
            try {  
                if (rs != null) rs.close();  
                if (pstmt != null) pstmt.close();  
                if (conn != null) conn.close();  
            } catch (SQLException ex) {  
                ex.printStackTrace();  
            }  
        }  
        // 转发请求到JSP页面  
        request.getRequestDispatcher("Operation.jsp").forward(request, response);  
    }  
}
package servlet;

import java.io.IOException;  
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import dao.OperationDao;  
  
@WebServlet(name = "admin_browsing_log",urlPatterns = "/admin/browsing")
public class AdminBrowsingServlet extends HttpServlet { 
	private OperationDao opdao= new OperationDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Map<String, Object>> userList = new ArrayList<>();  
        
        String ue = request.getParameter("ue");
        Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateNowStr = sdf.format(d);
    	String ipAddress = request.getHeader("X-Forwarded-For");  
        if (ipAddress == null) {  
            ipAddress = request.getRemoteAddr();  
        }
        try {
			opdao.insertOperationLog("查询用户浏览记录", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        try {  
            // 1. 建立数据库连接  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211"); 
            //执行查询
            String sql = "SELECT username, dwelltime, goodsid, goodsname FROM userbrowsing";  
            pstmt = conn.prepareStatement(sql);  
            rs = pstmt.executeQuery();          
            // 处理查询结果  
            while (rs.next()) {  
                Map<String, Object> user = new HashMap<>();  
                user.put("username", rs.getString("username"));  
                user.put("dwellTime", rs.getInt("dwelltime"));  
                user.put("goodsid", rs.getInt("goodsid"));  
                user.put("goodsname",rs.getString("goodsname"));
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
        request.getRequestDispatcher("browsing.jsp").forward(request, response);  
    }  
}
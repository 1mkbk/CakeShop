package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.Order;
import model.User;
import service.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "user_logout",urlPatterns = "/user_logout")
public class UserLogoutServlet extends HttpServlet {
	private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false); // 不创建新会话  
        if (session != null) {  
            // 从会话中获取用户名  
            String username = (String) session.getAttribute("username");                
            // 获取IP地址  
            String ipAddress = request.getRemoteAddr();  
            // 获取当前时间  
            Date logoutTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String dateNowStr = sdf.format(logoutTime);
    		User u = new User();
    		u.setip(ipAddress);
    		u.setlogoutdate(dateNowStr);
    		u.setUsername(username);
    		uService.inserto(u);
            request.getSession().removeAttribute("user");
        response.sendRedirect("index.jsp");
    }
}
}

package servlet;

import model.User;
import service.UserService;
import utils.DataSourceUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "user_login",urlPatterns = "/user_login")
public class UserLoginServlet extends HttpServlet {
    private UserService uService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户请求，获取请求参数
        String ue = request.getParameter("ue");
        String password = request.getParameter("password");
        //判断
        User user = uService.login(ue, password);
        //根据判断结果给出不同响应
        if(user==null) {
            request.setAttribute("failMsg", "用户名、邮箱或者密码错误，请重新登录！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
        }else {
            request.getSession().setAttribute("user", user);
            String ipAddress = request.getHeader("X-Forwarded-For");  
            if (ipAddress == null) {  
                ipAddress = request.getRemoteAddr();  
            }
            Date d = new Date();
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String dateNowStr = sdf.format(d);
    		request.getSession().setAttribute("username", ue);  
            request.getSession().setAttribute("loginTime", dateNowStr);  
            request.getSession().setAttribute("ipAddress", ipAddress);
            user.setip(ipAddress);
            user.setlogindate(dateNowStr);
            uService.insert(user);
            request.getRequestDispatcher("/user_center.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

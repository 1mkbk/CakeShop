package servlet;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OperationDao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "admin_order_status",urlPatterns = "/admin/order_status")
public class AdminOrderStatusServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    private OperationDao opdao= new OperationDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String ue = request.getParameter("ue");
        Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateNowStr = sdf.format(d);
    	String ipAddress = request.getHeader("X-Forwarded-For");  
        if (ipAddress == null) {  
            ipAddress = request.getRemoteAddr();  
        }
        try {
			opdao.insertOperationLog("更改订单状态", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//获取订单参数：id和status
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        //业务处理
        oService.updateStatus(id, status);
        //页面跳转
        response.sendRedirect("/CakeShop/admin/order_list?status="+status);
    }
}

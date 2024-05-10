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

@WebServlet(name = "admin_order_delete",urlPatterns = "/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    private OperationDao opdao= new OperationDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ue = request.getParameter("ue");
        Date d = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateNowStr = sdf.format(d);
    	String ipAddress = request.getHeader("X-Forwarded-For");  
        if (ipAddress == null) {  
            ipAddress = request.getRemoteAddr();  
        }
        try {
			opdao.insertOperationLog("删除订单", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        oService.delete(id);
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }
}

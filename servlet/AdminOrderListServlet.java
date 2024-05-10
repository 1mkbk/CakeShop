package servlet;

import model.Page;
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

@WebServlet(name = "admin_order_list",urlPatterns = "/admin/order_list")
public class AdminOrderListServlet extends HttpServlet {
    private OrderService oService = new OrderService();
    private OperationDao opdao= new OperationDao();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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
			opdao.insertOperationLog("查询订单", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//接收订单的状态参数
        int status = 0;
        if(request.getParameter("status") != null) {
            status=Integer.parseInt(request.getParameter("status") ) ;
        }
        request.setAttribute("status", status);
        //接收分页参数，进行分页处理。
        int pageNumber = 1;
        if(request.getParameter("pageNumber") != null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber") ) ;
            }
            catch (Exception e)
            {

            }
        }
        if(pageNumber<=0)
            pageNumber=1;
        //查询订单和订单项信息
        Page p = oService.getOrderPage(status,pageNumber);
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p = oService.getOrderPage(status,pageNumber);
            }
        }
        request.setAttribute("p", p);
        request.getRequestDispatcher("/admin/order_list.jsp").forward(request, response);
    }
}

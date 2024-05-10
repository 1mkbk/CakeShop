package servlet;

import service.GoodsService;

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

@WebServlet(name = "admin_goods_delete",urlPatterns = "/admin/goods_delete")
public class AdminGoodsDeleteServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    private OperationDao opdao= new OperationDao();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
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
			opdao.insertOperationLog("删除商品", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        gService.delete(id);
        request.getRequestDispatcher("goods_list").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}

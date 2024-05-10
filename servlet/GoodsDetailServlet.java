package servlet;
import javax.servlet.http.HttpSession;  
import java.util.Random;
import java.util.concurrent.TimeUnit; 
import model.Goods;
import model.User;
import service.GoodsService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoodsDao;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "goods_detail",urlPatterns = "/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
	private GoodsDao gd = new GoodsDao();
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        int id = Integer.parseInt(request.getParameter("id"));
        Goods g = gService.getGoodsById(id);
        HttpSession session = request.getSession(false); // 不创建新会话  
        //获取用户名
        String username = (String) session.getAttribute("username");
    	// 检查会话中是否已存在开始时间  
    	Long startTime = (Long) session.getAttribute("startTime");  
    	if (startTime == null) {  
    	    // 设置开始时间  
    	    startTime = System.currentTimeMillis();  
    	    session.setAttribute("startTime", startTime);  
    	}  
    	// 在用户每次请求时更新最后访问时间
    	session.setAttribute("lastAccessTime", System.currentTimeMillis());  
    	// 计算时长  
    	Long endTime = System.currentTimeMillis();  
    	Long durationInMillis = endTime - startTime; 
    	Random rand = new Random();  
        int randomNum = rand.nextInt(10); 
        try {
			gd.insert1(g,username,randomNum+1,g.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setAttribute("g", g);
        request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
    }
}

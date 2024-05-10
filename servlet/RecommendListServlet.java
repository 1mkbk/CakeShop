package servlet;

import model.Goods;
import model.Page;
import service.GoodsService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "recommend_List",urlPatterns = "/Recommend")
public class RecommendListServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    private ProductService productService = new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession(false); // 不创建新会话  
         String username = (String) session.getAttribute("username");
         List<Integer> topFiveProductIds = productService.getTopFiveProductIds(username);  
         List<Goods> topProducts = productService.getProductsByIds(topFiveProductIds);  
         request.setAttribute("topProducts", topProducts);  
        // 转发请求到JSP页面  
        request.getRequestDispatcher("Recommend.jsp").forward(request, response);  
    }
}

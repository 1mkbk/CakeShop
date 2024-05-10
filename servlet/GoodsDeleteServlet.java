package servlet;

import model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_delete",urlPatterns = "/goods_delete")
public class GoodsDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车的session对象
        Order o = (Order) request.getSession().getAttribute("order");
        //获取商品id
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        //执行删除操作
        o.delete(goodsid);
        response.getWriter().print("ok");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

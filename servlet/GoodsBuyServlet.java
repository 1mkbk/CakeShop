package servlet;

import model.Goods;
import model.Order;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "goods_buy",urlPatterns = "/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //声明购物车，并初始化
        Order o = null;
        //判断用户是否已经拥有购物车，如果没有，则生成一个；如果已经存在，就直接获取
        if(request.getSession().getAttribute("order") != null) {
            o = (Order) request.getSession().getAttribute("order");
        }else {
            o = new Order();
            request.getSession().setAttribute("order", o);
        }
        //从前端获取商品id，并查询数据库获取商品信息
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = gService.getGoodsById(goodsid);
        //判断库存是否为0，若为0，则拒绝加入购物车
        if(goods.getStock()>0) {
            o.addGoods(goods);
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

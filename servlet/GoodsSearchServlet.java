package servlet;

import service.GoodsService;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "goods_search",urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private GoodsService gService = new GoodsService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取输入框数据
        String keyword = request.getParameter("keyword");
        //获取分页信息
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
        {
            pageNumber=1;
        }
        //根据用户输入的keyword进行查询
        Page p =gService.getSearchGoodsPage(keyword,pageNumber);
        //根据查询到的实际数量进行分页
        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p =gService.getSearchGoodsPage(keyword,pageNumber);
            }
        }
        //将根据关键词查询到的数据放到请求中，并将请求转发到指定页面
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword,"utf-8"));
        request.getRequestDispatcher("goods_search.jsp").forward(request, response);
    }

}

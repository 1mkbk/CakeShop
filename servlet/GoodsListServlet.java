package servlet;
import model.Goods;
import model.Page;
import model.Type;
import service.GoodsService;
import service.TypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "goods_List",urlPatterns = "/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService=new GoodsService();
    private TypeService tService=new TypeService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=0;
        //获取参数类型ID，并判断是否为空
        if(request.getParameter("typeid")!=null)
        {
            id=Integer.parseInt(request.getParameter("typeid"));
        }

        //获取分页参数，并判断是否为空
        int pageNumber=1;
        if(request.getParameter("pageNumber")!=null) {
            try {
                pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
            }
            catch (Exception e)
            {
            }
        }

        //根据typeId查询type对象
        Type t=null;
        if(id!=0)
        {
            t=tService.selectTypeNameByID(id);
        }
        request.setAttribute("t",t);
        //根据分页参数获取商品具体数据
        if(pageNumber<=0)
            pageNumber=1;
        Page p=gService.selectPageByTypeID(id,pageNumber);


        if(p.getTotalPage()==0)
        {
            p.setTotalPage(1);
            p.setPageNumber(1);
        }
        else {
            if(pageNumber>=p.getTotalPage()+1)
            {
                p=gService.selectPageByTypeID(id,p.getTotalPage());
            }
        }

        request.setAttribute("p",p);
        request.setAttribute("id",String.valueOf(id));
        request.getRequestDispatcher("goods_list.jsp").forward(request,response);
    }
}

package servlet;

import model.Goods;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.OperationDao;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "admin_goods_edit",urlPatterns = "/admin/goods_edit")
public class AdminGoodsEditServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
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
			opdao.insertOperationLog("更改商品信息", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//接收管理员输入参数
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            //将参数封装为对象
            Goods g = new Goods();
            int pageNumber =1;
            int type=0;
            //遍历从前端获取到的列表
            for(FileItem item:list) {
                //判断是否为表单数据，是则返回true
                if(item.isFormField()) {
                    //将字段值保存到对象中
                    switch(item.getFieldName()) {
                        case "id":
                            g.setId(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "name":
                            g.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            g.setPrice(Float.parseFloat(item.getString("utf-8")));
                            break;
                        case "intro":
                            g.setIntro(item.getString("utf-8"));
                            break;
                        case "cover":
                            g.setCover(item.getString("utf-8"));
                            break;
                        case "image1":
                            g.setImage1(item.getString("utf-8"));
                            break;
                        case "image2":
                            g.setImage2(item.getString("utf-8"));
                            break;
                        case "stock":
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "typeid":
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "pageNumber":
                            pageNumber=Integer.parseInt(item.getString("utf-8"));
                            break;
                        case "type":
                            type = Integer.parseInt(item.getString("utf-8"));
                            break;
                    }
                }else {
                    //文件字段
                    if(item.getInputStream().available()<=0)continue;
                    //拿到文件名称、路径
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    String path = this.getServletContext().getRealPath("picture")+fileName;
                    //创建输入输出流
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();
                    switch(item.getFieldName()) {
                        case "cover":
                            g.setCover("picture"+fileName);
                            break;
                        case "image1":
                            g.setImage1("picture"+fileName);
                            break;
                        case "image2":
                            g.setImage2("picture"+fileName);
                            break;
                    }
                }
            }
            //调用业务层，进行数据保存
            gService.update(g);
            //请求转发
            request.getRequestDispatcher("goods_list?pageNumber="+pageNumber+"&type="+type).forward(request, response);
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}


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

@WebServlet(name = "AdminGoodsAddServlet",urlPatterns = "/admin/goods_add")
public class AdminGoodsAddServlet extends HttpServlet {
    private GoodsService gService = new GoodsService();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    private OperationDao opdao= new OperationDao();
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
			opdao.insertOperationLog("添加商品", ipAddress, dateNowStr, "admin");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	//接收参数
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //取得文件上传列表
            List<FileItem> list = upload.parseRequest(request);
            Goods g = new Goods();
            //遍历列表
            for(FileItem item:list) {
                //判断item是否为普通文本，是返回true，否则返回false
                if(item.isFormField()) {
                    switch(item.getFieldName()) {
                        case "name":
                            g.setName(item.getString("utf-8"));
                            break;
                        case "price":
                            g.setPrice(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "intro":
                            g.setIntro(item.getString("utf-8"));
                            break;
                        case "stock":
                            g.setStock(Integer.parseInt(item.getString("utf-8")));
                            break;
                        case "typeid":
                            g.setTypeid(Integer.parseInt(item.getString("utf-8")));
                            break;
                    }
                }else {
                    //文件字段表单
                    if(item.getInputStream().available()<=0)continue;
                    //取得文件名称
                    String fileName = item.getName();
                    fileName = fileName.substring(fileName.lastIndexOf("."));
                    fileName = "/"+new Date().getTime()+fileName;
                    //取得文件上传路径
                    String path = this.getServletContext().getRealPath("picture")+fileName;
                    //创建读写流
                    InputStream in = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(path);
                    //完成读写操作
                    byte[] buffer = new byte[1024];
                    int len=0;
                    while( (len=in.read(buffer))>0 ) {
                        out.write(buffer);
                    }
                    in.close();
                    out.close();
                    item.delete();

                    //封装对象
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
            //调用service的insert()方法
            gService.insert(g);
            request.getRequestDispatcher("goods_list").forward(request, response);
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

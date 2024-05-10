package service;

import java.sql.*;  
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import model.Goods;  
  
public class ProductService {  
  
    // 获取浏览次数最多的五款商品的ID  
    public List<Integer> getTopFiveProductIds(String name) {  
    	Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Integer> topProductIds = new ArrayList<>();   
        try {  
            // 1. 建立数据库连接  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211");  
            // 执行查询  
            String sql = "SELECT goodsid,COUNT(*) as view_count "
            		+ "FROM userbrowsing where username=? "
            		+ "GROUP BY goodsid "
            		+ "ORDER BY view_count DESC "
            		+ "LIMIT 5;";  
            pstmt = conn.prepareStatement(sql);  
            pstmt.setString(1, name);  
            rs = pstmt.executeQuery();          
            // 处理查询结果  
            while (rs.next()) {  
                topProductIds.add(rs.getInt("goodsid"));  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return topProductIds;  
    }  
    //获取购买数量最多的五款商品
    public List<Integer> getTopProductIds() {  
    	Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Integer> topProductIds = new ArrayList<>();   
        try {  
            // 1. 建立数据库连接  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211");  
            // 执行查询  
            String sql = "SELECT goods_id, SUM(amount) as total_quantity " +  
                    "FROM orderitem " +  
                    "GROUP BY goods_id " +  
                    "ORDER BY total_quantity DESC " +  
                    "LIMIT 5";   
            pstmt = conn.prepareStatement(sql);  
            rs = pstmt.executeQuery();          
            // 处理查询结果  
            while (rs.next()) {  
                topProductIds.add(rs.getInt("goods_id"));  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return topProductIds;  
    }  
    //获取无购买记录的商品ID
    public List<Integer> getNoRecordIds()
    {
    	Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Integer> ProductIds = new ArrayList<>();   
        List<Integer> NoProductIds = new ArrayList<>(); 
        try {  
            // 1. 建立数据库连接  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211");  
            // 执行查询  
            String sql = "SELECT id FROM goods";
            pstmt = conn.prepareStatement(sql);  
            rs = pstmt.executeQuery();          
            // 处理查询结果  
            while (rs.next()) {  
                ProductIds.add(rs.getInt("id"));  
            }  
            String sql1 = "SELECT DISTINCT goods_id FROM orderitem";  
            pstmt = conn.prepareStatement(sql1);  
            rs = pstmt.executeQuery(); 
            while (rs.next()) {  
                NoProductIds.add(rs.getInt("goods_id"));  
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        Set<Integer> set1 = new HashSet<>(ProductIds);  
        Set<Integer> set2 = new HashSet<>(NoProductIds);  
        // 使用差集操作找出set1中有而set2中没有的元素  
        set1.removeAll(set2); 
        return new ArrayList<>(set1); 
    }
    // 根据商品ID列表获取商品信息  
    public List<Goods> getProductsByIds(List<Integer> productIds) { 
    	Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null; 
        List<Goods> products = new ArrayList<>();  
        try {  
            // 1. 建立数据库连接  
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cookie", "root", "lyn20031211");  
            // 执行查询  
            String sql = "SELECT * FROM goods where id=?";
            for (Integer n : productIds) {  
            	 pstmt = conn.prepareStatement(sql);  
                 pstmt.setInt(1, n);  
                 rs = pstmt.executeQuery();
                 //处理查询结果
                 while (rs.next()) {  
                     Goods product = new Goods();  
                     product.setId(rs.getInt("id"));  
                     product.setCover(rs.getString("cover"));
                     product.setPrice(rs.getFloat("price"));
                     product.setName(rs.getString("name"));   
                     // 设置其他商品属性  
                     products.add(product);  
                 }
            }        
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return products; 
    }  
}
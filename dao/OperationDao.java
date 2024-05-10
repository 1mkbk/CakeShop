package dao;

import model.Operation;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import utils.DataSourceUtils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDao {
    public void insertOperationLog(String on_name,String ip,String on_time,String or_name) throws SQLException {
        QueryRunner r = new QueryRunner(DataSourceUtils.getDataSource());
        String sql ="insert into operation (operation_name,ip_address,operation_time,operator_name) values(?,?,?,?)";
        r.update(sql,on_name,ip,on_time,or_name);
    }
}

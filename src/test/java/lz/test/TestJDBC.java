package lz.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class TestJDBC {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/lz?"
                + "useUnicode=true&characterEncoding=UTF8";
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(url,"root","123456");
            System.out.println("成功加载MySQL驱动程序");
            sql = "select name from t_user where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"38015227-b8fa-4c10-b7ea-79658fc407c7");
            rs = ps.executeQuery();
            if(rs.next()){
            	String name = rs.getString(1);
            	System.out.println("name="+name);
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	rs.close();
        	ps.close();
            conn.close();
        }
    }
}
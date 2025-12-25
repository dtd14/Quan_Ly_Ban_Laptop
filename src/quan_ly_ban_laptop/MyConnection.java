/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan_ly_ban_laptop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class MyConnection {
    private static final String user = "root";
    private static final String password = "";
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String dbname = "quan_li_lap_top";
    private static final String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
    
        public static Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Loi ket noi DB: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs, Statement st) {
        try {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            System.out.println("Lỗi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}

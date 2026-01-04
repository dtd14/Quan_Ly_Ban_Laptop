package csdl.TestConnect;

import java.sql.*;

public class ConnectMySQL {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/nhanvien" 
                       + "?useUnicode=true&characterEncoding=UTF-8"
                       + "&useSSL=false&serverTimezone=UTC";           
            String user = "root";
            String pass = "";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
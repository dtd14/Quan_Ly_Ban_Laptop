/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Dao;



import java.sql.DriverManager;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author nguye
 */
public class MyConnection {
   private String username ="root";
   private String password ="";
   private String host = "localhost";
   private String port = "3306";
   private String dbName = "quan_ly_lap_top";
   private String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName+ "?useUnicode=true&characterEncoding=utf-8";
   private Connection conn = null;
   
   public Connection openConnection(){
       try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return conn;
   }
   public void closeConnection(){
       if(conn != null){
           try {
               conn.close();
           } catch (Exception ex) {
              ex.printStackTrace();
           }
       }
   }
}

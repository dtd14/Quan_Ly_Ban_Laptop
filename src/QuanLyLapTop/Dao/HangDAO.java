/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Dao;

import QuanLyLapTop.Mode.Hang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class HangDAO {
    private MyConnection myConnection = null;
    private Connection conn;
    public List<Hang> getttHang(){
        List<Hang> dsHang = new ArrayList<>();
        String sql = "SELECT * FROM hang";
        myConnection = new MyConnection();
        conn = myConnection.openConnection();
        try {
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {  
                    String maHang = rs.getString("MaHang");
                    String tenHang = rs.getString("TenHang");
                    String moTa = rs.getString("MoTa");
                    dsHang.add(new Hang(maHang, tenHang, moTa));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dsHang;
    }
    public boolean insertHang(Hang h){
        myConnection = new MyConnection();
        PreparedStatement ps = null;
        String sql = "Insert into hang (MaHang,TenHang,MoTa) VALUES (?,?,?)";
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1, h.getMaHang());  
              ps.setString(2, h.getTenHang());
              ps.setString(3, h.getMota());
              int row = ps.executeUpdate();
              if(row > 0){
                  return true;
              }
              else{
                  return false;
              }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public boolean updateHang(Hang h){
        myConnection = new MyConnection();
        PreparedStatement ps = null;
        String sql = "Update hang set TenHang = ?, MoTa = ? Where MaHang = ?" ;
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1, h.getTenHang());
              ps.setString(2, h.getMota());
              ps.setString(3, h.getMaHang());  
              int row = ps.executeUpdate();
              if(row > 0){
                  return true;
              }
              else{
                  return false;
              }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public boolean deleteHang(String maHang){
        myConnection = new MyConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM hang WHERE MaHang = ?";
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1, maHang); 
              int row = ps.executeUpdate();
              if(row > 0){
                  return true;
              }
              else{
                  return false;
              }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}

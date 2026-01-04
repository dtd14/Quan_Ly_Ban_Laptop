/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Dao;

import QuanLyLapTop.Mode.LapTop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class LaptopDAO {
    private MyConnection myConnection;
    private Connection conn;
    public List<LapTop> getAllLaptop(){
        List<LapTop> dsLapTop = new ArrayList<>();
        myConnection = new MyConnection();
        conn = myConnection.openConnection();
        String sql = "SELECT l.MaLapTop,l.TenLapTop,l.MaHang,h.TenHang,l.Gia,l.SoLuong,l.MaNCC FROM Laptop l JOIN Hang h ON l.MaHang = h.MaHang";
        try {
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    String malt = rs.getString("MaLapTop");
                    String tenlt = rs.getString("TenLapTop");
                    String mahanglt = rs.getString("MaHang");
                    String tenhanglt = rs.getString("TenHang");
                    Double gialt = rs.getDouble("Gia");
                    int soluong = rs.getInt("SoLuong");
                    int mancc = rs.getInt("MaNCC");
                    dsLapTop.add(new LapTop(malt, tenlt, mahanglt,tenhanglt, gialt, soluong, mancc));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dsLapTop;
    }
    public boolean insertLaptop(LapTop lt){
        myConnection = new MyConnection();
        PreparedStatement ps = null;
        String sql = "Insert into laptop (MaLapTop,TenLapTop,MaHang,Gia,SoLuong,MaNCC) VALUES (?,?,?,?,?,?)";
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1,lt.getMaLapTop());  
              ps.setString(2, lt.getTenLapTop());
              ps.setString(3, lt.getMaHang());
              ps.setDouble(4, lt.getGia());
              ps.setInt(5, lt.getSoLuong());
              ps.setInt(6, lt.getMaNCC());
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
    public boolean updateLaptop(LapTop lt){
        myConnection = new MyConnection();  
        PreparedStatement ps = null;
        String sql = "Update laptop set TenLapTop = ?,MaHang = ?,Gia = ?,SoLuong = ?,MaNCC = ? Where MaLapTop = ?";
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1, lt.getTenLapTop());
              ps.setString(2, lt.getMaHang());
              ps.setDouble(3, lt.getGia());
              ps.setInt(4, lt.getSoLuong());
              ps.setInt(5, lt.getMaNCC());
              ps.setString(6,lt.getMaLapTop());
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
    public boolean deleteLaptop(String malt){
        myConnection = new MyConnection();
        PreparedStatement ps = null;
        String sql = "Delete from Laptop Where MaLapTop = ?";
        conn = myConnection.openConnection();
        try {
            if(conn != null){
              ps = conn.prepareStatement(sql);
              ps.setString(1,malt);  
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
    
    public List<LapTop> getdanhsachLaptop(String id){
        List<LapTop> dsLapTop = new ArrayList<>();
        myConnection = new MyConnection();
        conn = myConnection.openConnection();
        String sql = "SELECT l.MaLapTop,l.TenLapTop,l.MaHang,h.TenHang,l.Gia,l.SoLuong,l.MaNCC FROM Laptop l JOIN Hang h ON l.MaHang = h.MaHang WHERE l.MaHang = ?";
        try {
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                    String malt = rs.getString("MaLapTop");
                    String tenlt = rs.getString("TenLapTop");
                    String mahanglt = rs.getString("MaHang");
                    String tenhanglt = rs.getString("TenHang");
                    Double gialt = rs.getDouble("Gia");
                    int soluong = rs.getInt("SoLuong");
                    int mancc = rs.getInt("MaNCC");
                    dsLapTop.add(new LapTop(malt, tenlt, mahanglt,tenhanglt, gialt, soluong, mancc));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LaptopDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return dsLapTop;
    }
}

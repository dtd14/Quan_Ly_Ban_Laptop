package quan_ly_ban_laptop;


import QuanLyLapTop.Dao.HangDAO;
import QuanLyLapTop.Dao.MyConnection;
import QuanLyLapTop.Mode.Hang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import quan_ly_ban_laptop.NhaCungCap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguye
 */
public class NhaCungCapDAO {
    private MyConnection myConnection = null;
    private Connection conn;
    public List<NhaCungCap> getTTNcc(){
        List<NhaCungCap> dsNCC = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";
        myConnection = new MyConnection();
        conn = myConnection.openConnection();
        try {
            if(conn != null){
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {  
                    int maNCC = rs.getInt("MaNCC");
                    String tenNCC = rs.getString("TenNCC");
                    String diaChi = rs.getString("DiaChi");
                    String sdt = rs.getString("SDT");
                    String email = rs.getString("Email");
                    String ghiChu = rs.getString("GhiChu");
                    dsNCC.add(new NhaCungCap(maNCC,tenNCC, diaChi, sdt, email, ghiChu));
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
        return dsNCC;
    }
}

package qlnvv;

import csdl.TestConnect.ConnectMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
     public List<nhanvienn> getAll() {
        List<nhanvienn> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (Connection conn = ConnectMySQL.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new nhanvienn(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDouble(9), rs.getString(10)));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    public boolean insert(nhanvienn nv) {
        // Kiểm tra chặn ở tầng DAO (phòng hờ)
        if (nv.getNgaySinh() == null || nv.getNgaySinh().trim().isEmpty()) return false;
        if (nv.getSdt() != null && nv.getSdt().length() > 10) return false;
        String sql = "INSERT INTO nhanvien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectMySQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nv.getMaNV());
            pst.setString(2, nv.getHoTen());
            pst.setString(3, nv.getGioiTinh());
            pst.setString(4, nv.getNgaySinh());
            pst.setString(5, nv.getSdt());
            pst.setString(6, nv.getEmail());
            pst.setString(7, nv.getDiaChi());
            pst.setString(8, nv.getChucVu());
            pst.setDouble(9, nv.getLuong()); 
            pst.setString(10, nv.getTrangThai());
            return pst.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace();
            return false; 
        }
    }
    public boolean update(nhanvienn nv) {
        if (nv.getNgaySinh() == null || nv.getNgaySinh().trim().isEmpty()) return false;
        if (nv.getSdt() != null && nv.getSdt().length() > 10) return false;
        String sql = "UPDATE nhanvien SET HoTen=?, GioiTinh=?, NgaySinh=?, SDT=?, Email=?, DiaChi=?, ChucVu=?, Luong=?, TrangThai=? WHERE MaNV=?";
        try (Connection conn = ConnectMySQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, nv.getHoTen());
            pst.setString(2, nv.getGioiTinh());
            pst.setString(3, nv.getNgaySinh());
            pst.setString(4, nv.getSdt());
            pst.setString(5, nv.getEmail());
            pst.setString(6, nv.getDiaChi());
            pst.setString(7, nv.getChucVu());
            pst.setDouble(8, nv.getLuong());
            pst.setString(9, nv.getTrangThai());
            pst.setString(10, nv.getMaNV());
            return pst.executeUpdate() > 0;
        } catch (Exception e) { 
            e.printStackTrace();
            return false; 
        }
    }
    public boolean delete(String ma) {
        try (Connection conn = ConnectMySQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM nhanvien WHERE MaNV=?")) {
            pst.setString(1, ma);
            return pst.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }
    public List<nhanvienn> search(String keyword) {
        List<nhanvienn> list = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE MaNV LIKE ? OR HoTen LIKE ? OR DiaChi LIKE ? OR ChucVu LIKE ? OR SDT LIKE ? OR GioiTinh LIKE ? ";     
        try (Connection conn = ConnectMySQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {           
            String searchPattern = "%" + keyword + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            pst.setString(4, searchPattern);
            pst.setString(5, searchPattern);            
            pst.setString(6, searchPattern); 
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(new nhanvienn(
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
                            rs.getDouble(9), rs.getString(10)
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
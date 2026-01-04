/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author DELL
 */

package qlnhacungcapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {

    public List<NhaCungCap> getAll() {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nhacungcap";

        try (Connection con = ConnectMySQL.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new NhaCungCap(
                        rs.getInt("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("Email"),
                        rs.getString("GhiChu")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
  public boolean insert(NhaCungCap ncc) {
    String sql = "INSERT INTO nhacungcap (TenNCC, SDT, DiaChi, Email, GhiChu) VALUES (?, ?, ?, ?, ?)";

    try (
        Connection con = ConnectMySQL.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setString(1, ncc.getTenNCC());
        ps.setString(2, ncc.getSdt());
        ps.setString(3, ncc.getDiaChi());
        ps.setString(4, ncc.getEmail());
        ps.setString(5, ncc.getGhiChu());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
  public boolean delete(int maNCC) {
    String sql = "DELETE FROM nhacungcap WHERE MaNCC = ?";

    try (
        Connection con = ConnectMySQL.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setInt(1, maNCC);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public boolean update(NhaCungCap ncc) {
    String sql = "UPDATE nhacungcap "
               + "SET TenNCC = ?, SDT = ?, DiaChi = ?, Email = ?, GhiChu = ? "
               + "WHERE MaNCC = ?";

    try (
        Connection con = ConnectMySQL.getConnection();
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        ps.setString(1, ncc.getTenNCC());
        ps.setString(2, ncc.getSdt());
        ps.setString(3, ncc.getDiaChi());
        ps.setString(4, ncc.getEmail());
        ps.setString(5, ncc.getGhiChu());
        ps.setInt(6, ncc.getMaNCC());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // TEST DAO
    // TEST DAO
    public static void main(String[] args) {
        NhaCungCapDAO dao = new NhaCungCapDAO();
    }
   
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quan_Ly_Khach_Hang.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import quan_ly_ban_laptop.MyConnection;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    private Customer mapResultSet(ResultSet rs) {
        Customer cus = new Customer();
        try {
            cus.setId(rs.getInt("MaKH"));
            cus.setName(rs.getString("TenKH"));
            cus.setNumber(rs.getString("SDT"));
            cus.setAddress(rs.getString("DiaChi"));
            cus.setEmail(rs.getString("Email"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }

    //select
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from khachhang";

        try {
            conn = MyConnection.getConnect();
            if (conn != null) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    customers.add(mapResultSet(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(conn, null, rs, stmt);
        }
        return customers;
    }

    //add
    public boolean insert_customer(Customer customers) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO khachhang(TenKH, SDT, DiaChi, Email) VALUES (?,?,?,?)";
        try {
            conn = MyConnection.getConnect();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, customers.getName());
                ps.setString(2, customers.getNumber());
                ps.setString(3, customers.getAddress());
                ps.setString(4, customers.getEmail());
                int row_effect = ps.executeUpdate();
                return row_effect > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(conn, ps, null, null);
        }
        return false;
    }

    //update
    public boolean update_customer(Customer customer) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update khachhang set TenKH = ?, SDT = ?, DiaChi = ?, Email = ? where MaKH = ?";
        try {
            conn = MyConnection.getConnect();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getNumber());
                ps.setString(3, customer.getAddress());
                ps.setString(4, customer.getEmail());
                ps.setInt(5, customer.getId());
                int row_effect = ps.executeUpdate();
                return row_effect > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(conn, ps, null, null);
        }
        return false;
    }

    //delete
    public boolean delete_customer(Customer customer) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from khachhang where MaKH = ?";
        try {
            conn = MyConnection.getConnect();
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, customer.getId());
                int row_effect = ps.executeUpdate();
                return row_effect > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyConnection.closeConnection(conn, ps, null, null);
        }
        return false;
    }
}

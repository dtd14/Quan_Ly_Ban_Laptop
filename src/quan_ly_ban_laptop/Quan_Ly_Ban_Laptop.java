/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quan_ly_ban_laptop;

import QuanLyLapTop.Giaodien.JFrameQuanLyLapTop;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class Quan_Ly_Ban_Laptop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Quản lý Laptop");
        frame.setContentPane(new JFrameQuanLyLapTop());
        frame.setSize(800, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}

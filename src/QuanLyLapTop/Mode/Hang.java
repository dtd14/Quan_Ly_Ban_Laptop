/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Mode;

/**
 *
 * @author nguye
 */
public class Hang {
    private String MaHang;
    private String TenHang;
    private String Mota;

    public Hang(String MaHang, String TenHang, String Mota) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.Mota = Mota;
    }

    public Hang() {
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    @Override
    public String toString() {
        return getTenHang();
    }
    
    
}

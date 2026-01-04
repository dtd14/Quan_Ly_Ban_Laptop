/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Mode;

/**
 *
 * @author nguye
 */
public class LapTop {
    private String MaLapTop;
    private String TenLapTop;
    private String MaHang;
    private String TenHang;
    private Double Gia;
    private int SoLuong;
    private int MaNCC;

    public LapTop(String MaLapTop, String TenLapTop, String MaHang, String TenHang, Double Gia, int SoLuong, int MaNCC) {
        this.MaLapTop = MaLapTop;
        this.TenLapTop = TenLapTop;
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.Gia = Gia;
        this.SoLuong = SoLuong;
        this.MaNCC = MaNCC;
    }

    public LapTop() {
    }

    public String getMaLapTop() {
        return MaLapTop;
    }

    public void setMaLapTop(String MaLapTop) {
        this.MaLapTop = MaLapTop;
    }

    public String getTenLapTop() {
        return TenLapTop;
    }

    public void setTenLapTop(String TenLapTop) {
        this.TenLapTop = TenLapTop;
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

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }
    
    
    
}

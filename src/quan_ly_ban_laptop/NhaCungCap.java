/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quan_ly_ban_laptop;

/**
 *
 * @author nguye
 */
public class NhaCungCap {
    private final int maNCC;
    private final String tenNCC;
    private final String diaChi;
    private final String sdt;
    private final String email;
    private final String ghiChu;

    public NhaCungCap(int maNCC, String tenNCC, String diaChi,
                      String sdt, String email, String ghiChu) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.ghiChu = ghiChu;
    }
    
    public int getMaNCC() { return maNCC; }
    public String getTenNCC() { return tenNCC; }
    public String getDiaChi() { return diaChi; }
    public String getSdt() { return sdt; }
    public String getEmail() { return email; }
    public String getGhiChu() { return ghiChu; }

    @Override
    public String toString() {
        return maNCC + " - " + tenNCC;  
    }
    
}

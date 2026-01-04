/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyLapTop.Giaodien;

import QuanLyLapTop.Dao.HangDAO;
import QuanLyLapTop.Dao.LaptopDAO;
import QuanLyLapTop.Mode.Hang;
import QuanLyLapTop.Mode.LapTop;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import quan_ly_ban_laptop.NhaCungCap;
import quan_ly_ban_laptop.NhaCungCapDAO;

/**
 *
 * @author nguye
 */
public class JFrameQuanLyLapTop extends JPanel implements ActionListener{
    
    //THÔNG TIN CHI TIẾT
    private JPanel jPnTTChiTiet,jpnttct2,jpnttct3,jpnttct4,jpnttct5,jpnttct6,jpnttct7,jpnttct8,jpnttct9,jpnttct10,jpnttct11,jpnttct12;
    private JButton btnThemlt,btnSualt,btnXoalt;
    private JTextField txtTimkiemlt,txtMalt,txtTenlt,txtGialt,txtSoluonglt;
    private JLabel lblTimkiemlt,lblHanglt,lblTenlt,lblMalt,lblGialt,lblSoluonglt,lblMaNCC;
    private JComboBox<Hang> cbHanglt;
    private JComboBox<NhaCungCap> cbMancc;
    private JTable table;
    private DefaultTableModel modelTable;
    private LaptopDAO ltdao;
    private List<LapTop> listDataLaptop = new ArrayList<>();
    private JList<LapTop> listLaptop;
    private NhaCungCapDAO nccdao;
    
    //DANH SÁCH HÃNG
    private JButton btnThemh,btnXoah,btnSuah;
    private JLabel lblTT,lblct;
    private List<Hang> listDatahang = new ArrayList<>();
    private JList<Hang> listHang;
    private DefaultListModel<Hang> modelHang;
    private JPanel jPnDSHang,jpnDS1,jpnDS2;
    private HangDAO hdao;
    private JTextField txtMaHang,txtTenHang;
    private JTextArea txtMoTa;
    
    //TITLE
    private JPanel jPntt;
    private JButton btnLammoi,btnThoat;
    
    public JFrameQuanLyLapTop() {
        initJFrameLapTop();
    }
    private void initJFrameLapTop() {
        setLayout(new BorderLayout());
        initTitle();
        initDanhSachHang();
        initThongTinChiTiet();
        addAction();
    }
    private void initTitle(){
        Font f = new Font("Arial",Font.BOLD,20);
        jPntt = new JPanel();
        add(jPntt,BorderLayout.NORTH);
        lblTT = new JLabel("Quản lý lap top");
        lblTT.setFont(f);
        jPntt.setBackground(Color.red);
        jPntt.setOpaque(true);
        lblTT.setHorizontalAlignment(JLabel.CENTER);
        jPntt.add(lblTT);
    }
    private void initDanhSachHang(){
        jPnDSHang = new JPanel();
        add(jPnDSHang,BorderLayout.WEST);
        jPnDSHang.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,0),BorderFactory.createLineBorder(Color.BLACK)));
        jPnDSHang.setLayout(new BorderLayout());
        jPnDSHang.setPreferredSize(new Dimension(230,300));
        
        //JPanel chứa danh sách
        TitledBorder ttBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Danh sách hãng", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial",Font.BOLD,20), Color.RED);
        jpnDS1 = new JPanel();
        jpnDS1.setBorder(ttBorder);
        
        //Hiển thị danh sách hãng
        hdao = new HangDAO();
        listDatahang = hdao.getttHang();
        modelHang = new DefaultListModel<>();
        for(Hang h : listDatahang){
            modelHang.addElement(h);
        }
        listHang = new JList<>(modelHang);
        JScrollPane sc = new JScrollPane(listHang);
        sc.setPreferredSize(new Dimension(200,300));        
        jpnDS1.add(sc);
        
        // Bắt sự kiện khi mà chọn vào 1 item sẽ hiện tất cả sản phẩm sang ra bảng thông tin chi tiết
        listHang.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    Hang h = listHang.getSelectedValue();
                    if(h != null){
                        loadTableByHang(h.getMaHang());
                    }
                }
            }
        });

        // JPanel chứa button
        jpnDS2 = new JPanel(new GridLayout(2, 2, 5, 5));
        btnThemh = new JButton("Thêm hãng");
        btnSuah = new JButton("Sửa hãng");
        btnXoah = new JButton("Xóa hãng");
        btnLammoi = new JButton("Làm mới");
        jpnDS2.add(btnThemh);
        jpnDS2.add(btnSuah);
        jpnDS2.add(btnXoah);
        jpnDS2.add(btnLammoi);
        
        
        jPnDSHang.add(jpnDS1,BorderLayout.CENTER);
        jPnDSHang.add(jpnDS2,BorderLayout.SOUTH);
    }
    private void initThongTinChiTiet(){
        jPnTTChiTiet = new JPanel();
        jPnTTChiTiet.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createLineBorder(Color.BLACK)));
        this.add(jPnTTChiTiet,BorderLayout.CENTER);
        jPnTTChiTiet.setLayout(new BorderLayout());
        
        //Jpanel chứa tìm kiếm
        jpnttct2 = new JPanel();
        jPnTTChiTiet.add(jpnttct2,BorderLayout.NORTH);
        txtTimkiemlt = new JTextField();
        txtTimkiemlt.setPreferredSize(new Dimension(150,25));
        lblTimkiemlt = new JLabel("Tìm kiếm");
        jpnttct2.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        jpnttct2.add(txtTimkiemlt);
        jpnttct2.add(lblTimkiemlt);
        

        //JPanel chứa table và và text
        jpnttct3 = new JPanel();
        jpnttct3.setLayout(new BorderLayout());
        jPnTTChiTiet.add(jpnttct3,BorderLayout.CENTER);
        

        // Dùng để Jtable
        jpnttct4 = new JPanel();
        String[] col = {"Mã Laptop","Tên Laptop","Tên hãng","Giá","Số lượng","Mã NCC","Mã hãng"};
        Object [][] row = {};
        modelTable = new DefaultTableModel(row,col);
        table = new JTable(modelTable);
        JScrollPane sctb = new JScrollPane(table);
        sctb.setPreferredSize(new Dimension(520,150));
        jpnttct4.add(sctb);
        jpnttct3.add(jpnttct4,BorderLayout.NORTH);
        loadTable();
        // JPanel dùng để chứa các txt
        jpnttct5 = new JPanel();
        jpnttct5.setLayout(new GridLayout(3, 2, 10, 10));
        jpnttct5.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 50));
        jpnttct3.add(jpnttct5,BorderLayout.CENTER);
        Dimension lbl = new Dimension(100, 25);
        Dimension txt = new Dimension(150, 25);
        
        jpnttct6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblHanglt = new JLabel("Hãng");
        cbHanglt = new JComboBox<>();
        lblHanglt.setPreferredSize(lbl);
        cbHanglt.setPreferredSize(txt);
        loadcbHang();
        jpnttct6.add(lblHanglt);
        jpnttct6.add(cbHanglt);
        
        jpnttct7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblMalt = new JLabel("Mã Laptop");
        txtMalt = new JTextField();
        lblMalt.setPreferredSize(lbl);
        txtMalt.setPreferredSize(txt);
        jpnttct7.add(lblMalt);
        jpnttct7.add(txtMalt);
        
        jpnttct8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblTenlt = new JLabel("Tên Laptop");
        txtTenlt = new JTextField();
        lblTenlt.setPreferredSize(lbl);
        txtTenlt.setPreferredSize(txt);
        jpnttct8.add(lblTenlt);
        jpnttct8.add(txtTenlt);
        
        
        jpnttct9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblGialt = new JLabel("Giá");
        txtGialt = new JTextField();
        lblGialt.setPreferredSize(lbl);
        txtGialt.setPreferredSize(txt);
        jpnttct9.add(lblGialt);
        jpnttct9.add(txtGialt);
        
        jpnttct10 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblSoluonglt = new JLabel("Số lượng");
        txtSoluonglt = new JTextField();
        lblSoluonglt.setPreferredSize(lbl);
        txtSoluonglt.setPreferredSize(txt);
        jpnttct10.add(lblSoluonglt);
        jpnttct10.add(txtSoluonglt);
        
        jpnttct11 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        lblMaNCC = new JLabel("Mã nhà cung cấp");
        cbMancc = new JComboBox<>();
        lblMaNCC.setPreferredSize(lbl);
        cbMancc.setPreferredSize(txt);
        loadcbNcc();
        jpnttct11.add(lblMaNCC);
        jpnttct11.add(cbMancc);
        
        jpnttct5.add(jpnttct6);
        jpnttct5.add(jpnttct11);
        jpnttct5.add(jpnttct7);
        jpnttct5.add(jpnttct8);
        jpnttct5.add(jpnttct9);
        jpnttct5.add(jpnttct10);
        

        //JPanel chứa button thực hiện lt
        jpnttct12 = new JPanel();
        jpnttct12.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        btnThemlt = new JButton("Thêm");
        btnSualt = new JButton("Sửa");
        btnXoalt = new JButton("Xóa");
        btnThoat = new JButton("Thoát");
        jPnTTChiTiet.add(jpnttct12,BorderLayout.SOUTH);
        jpnttct12.add(btnThemlt);
        jpnttct12.add(btnSualt);
        jpnttct12.add(btnXoalt);
        jpnttct12.add(btnThoat);
        
        //Bắt sự kiện khi click vào bảng table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    int row = table.getSelectedRow();
                    if(row >= 0){
                        txtMalt.setText(modelTable.getValueAt(row, 0).toString());
                        txtMalt.setEnabled(false);
                        txtTenlt.setText(modelTable.getValueAt(row, 1).toString());
                        txtGialt.setText(modelTable.getValueAt(row, 3).toString());
                        txtSoluonglt.setText(modelTable.getValueAt(row, 4).toString());
                        String maHang = modelTable.getValueAt(row, 6).toString();
                        int maNcc = Integer.parseInt(modelTable.getValueAt(row, 5).toString());
                        setSelectedHang(maHang);
                        setSelectedNCC(maNcc);                          
                    }
                }
            }
        
        });
        txtTimkiemlt.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                timKiemLaptop();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timKiemLaptop();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timKiemLaptop();
            }
        });
    }
    private void timKiemLaptop(){
        String keyword = txtTimkiemlt.getText().trim().toLowerCase();
        modelTable.setRowCount(0); 
        for(LapTop lt : listDataLaptop){
            if(lt.getMaLapTop().toLowerCase().contains(keyword)
               || lt.getTenLapTop().toLowerCase().contains(keyword)
               || lt.getTenHang().toLowerCase().contains(keyword)){
                modelTable.addRow(new Object[]{
                    lt.getMaLapTop(),
                    lt.getTenLapTop(),
                    lt.getTenHang(),
                    lt.getGia(),
                    lt.getSoLuong(),
                    lt.getMaNCC(),
                    lt.getMaHang()
                });
            }
        }
    }

    private void setSelectedHang(String maHang){
        for(int i = 0; i < cbHanglt.getItemCount(); i++){
            Hang h = cbHanglt.getItemAt(i);
            if(h.getMaHang().equals(maHang)){
                cbHanglt.setSelectedIndex(i);
                break;
            }
        }
    }
    private void setSelectedNCC(int maNcc){
        for(int i = 0; i < cbMancc.getItemCount(); i++){
            NhaCungCap ncc = cbMancc.getItemAt(i);
            if (ncc.getMaNCC() == maNcc) {
                cbMancc.setSelectedIndex(i);
                break;
            }
        }
    }

    private void addAction(){
        btnThemh.addActionListener(this);
        btnSuah.addActionListener(this);
        btnXoah.addActionListener(this);
        btnThemlt.addActionListener(this);
        btnSualt.addActionListener(this);
        btnXoalt.addActionListener(this);
        btnLammoi.addActionListener(this);
        btnThoat.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Thêm")) {
            themLaptop();
        }else if (e.getActionCommand().equals("Sửa")) {
            suaLaptop();
        }else if (e.getActionCommand().equals("Xóa")) {
            xoaLaptop();
        }else if (e.getActionCommand().equals("Thêm hãng")) {
            themHang();
        }else if (e.getActionCommand().equals("Sửa hãng")) {
            suaHang();
        }else if (e.getActionCommand().equals("Xóa hãng")) {
            xoaHang();
        }
        else if (e.getActionCommand().equals("Làm mới")) {
            clear();
            loadTable();
        }
        else if (e.getActionCommand().equals("Thoát")) {
            thoat();
        }
    }
    private void themLaptop(){
        try {
            ltdao = new LaptopDAO();
            if(txtMalt.getText().trim().isEmpty() || txtTenlt.getText().trim().isEmpty() || txtGialt.getText().trim().isEmpty() || txtSoluonglt.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
                return;
            }
            String malt = txtMalt.getText().trim();
            String tenlt = txtTenlt.getText().trim();
            Double gialt;
            int soluonglt;
            try {
                gialt = Double.parseDouble(txtGialt.getText().trim());
                soluonglt = Integer.parseInt(txtSoluonglt.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá và Số lượng phải là số hợp lệ!");
                return;
            }
            if(gialt <= 0){
                JOptionPane.showMessageDialog(this, "Giá phải lớn hơn 0");
                txtGialt.requestFocus();
                return;
            }

            if(soluonglt <= 0){
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                txtSoluonglt.requestFocus();
                return;
            }
            Hang hang = (Hang) cbHanglt.getSelectedItem();
            String mahang = hang.getMaHang();
            String tenhang = hang.getTenHang();
            NhaCungCap Ncc = (NhaCungCap) cbMancc.getSelectedItem();
            int maNcc = Ncc.getMaNCC();
            
            LapTop lt = new LapTop(malt, tenlt, mahang,tenhang, gialt, soluonglt, maNcc);
            if(ltdao.insertLaptop(lt)){
                JOptionPane.showMessageDialog(this,"Thêm thành công ");
                loadTable();
                clear();
            }
            else{
                JOptionPane.showMessageDialog(this,"Mã Laptop đã tồn tại!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
    }
    private void suaLaptop(){
        try {
            int row = table.getSelectedRow();
            if(row >= 0){
                String malt = txtMalt.getText().trim();
                String tenlt = txtTenlt.getText().trim();
                Double gialt = Double.parseDouble(txtGialt.getText().toString().trim());
                int soluonglt = Integer.parseInt(txtSoluonglt.getText().toString().trim());
                Hang hang = (Hang) cbHanglt.getSelectedItem();
                String mahang = hang.getMaHang();
                String tenhang = hang.getTenHang();
                NhaCungCap Ncc = (NhaCungCap) cbMancc.getSelectedItem();
                int maNcc = Ncc.getMaNCC();

                LapTop lt = new LapTop(malt, tenlt, mahang,tenhang, gialt, soluonglt, maNcc);
                int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn sửa Laptop này không","Xác nhận sửa",JOptionPane.YES_NO_OPTION);
                
                ltdao = new LaptopDAO();
                if(confirm == JOptionPane.YES_OPTION){
                    if(ltdao.updateLaptop(lt)){
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                        clear();
                        loadTable();
                    }  
                }
                
            }
            else{
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Laptop để sửa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void xoaLaptop(){
        try {
            int row = table.getSelectedRow();
            if(row >= 0){
                String malt = (String) modelTable.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(
                            this,
                            "Bạn có chắc muốn xóa laptop này không?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION
                        );
                if(confirm == JOptionPane.YES_OPTION){
                    ltdao = new LaptopDAO();
                    if(ltdao.deleteLaptop(malt)){
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                        clear();
                        loadTable();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this,"Vui lòng chọn Laptop để xóa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    private void themHang(){
        txtMaHang = new JTextField();
        txtTenHang = new JTextField();
        txtMoTa = new JTextArea(4, 20); // 4 dòng, 20 cột
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);
        scrollMoTa.setPreferredSize(new Dimension(250, 80));
        Object[] message = {
            "Mã hãng:", txtMaHang,
            "Tên hãng:", txtTenHang,
            "Mô tả:", txtMoTa
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Thêm hãng Laptop",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if(option == JOptionPane.OK_OPTION){
            String maHang = txtMaHang.getText().trim();
            String tenHang = txtTenHang.getText().trim();
            String moTa = txtMoTa.getText().trim();

            if(maHang.isEmpty() || tenHang.isEmpty()){
                JOptionPane.showMessageDialog(this,"Mã hãng và Tên hãng không được để trống");
                return;
            }

            Hang h = new Hang(maHang, tenHang, moTa);
            hdao = new HangDAO();

            if(hdao.insertHang(h)){
                JOptionPane.showMessageDialog(this,"Thêm hãng "+h.getTenHang()+"thành công");
                reloadHang();
            }else{
                JOptionPane.showMessageDialog(this,"Thêm hãng thất bại");
            }
        }
    }
    private void suaHang(){
        Hang hangCu = listHang.getSelectedValue();
        if(hangCu == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hãng cần sửa");
            return;
        }

        txtMaHang = new JTextField(hangCu.getMaHang());
        txtMaHang.setEnabled(false); // khóa mã hãng

        txtTenHang = new JTextField(hangCu.getTenHang());

        txtMoTa = new JTextArea(4, 20);
        txtMoTa.setText(hangCu.getMota());
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);

        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);
        scrollMoTa.setPreferredSize(new Dimension(250, 80));

        Object[] message = {
            "Mã hãng:", txtMaHang,
            "Tên hãng:", txtTenHang,
            "Mô tả:", scrollMoTa
        };

        int option = JOptionPane.showConfirmDialog(this,message,"Sửa hãng Laptop",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);

        if(option == JOptionPane.OK_OPTION){
            String tenHangMoi = txtTenHang.getText().trim();
            String moTaMoi = txtMoTa.getText().trim();

            if(tenHangMoi.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tên hãng không được để trống");
                return;
            }

            Hang hangMoi = new Hang(hangCu.getMaHang(),tenHangMoi,moTaMoi);

            hdao = new HangDAO();
            if(hdao.updateHang(hangMoi)){
                JOptionPane.showMessageDialog(this, "Cập nhật hãng thành công");
                reloadHang();
                loadTable();
            }else{
                JOptionPane.showMessageDialog(this, "Cập nhật hãng thất bại");
            }
        }
    }
    private void xoaHang(){
        Hang h = listHang.getSelectedValue();
        if(h == null){
            JOptionPane.showMessageDialog(this,"Vui lòng chọn hãng cần xóa");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa hãng: " + h.getTenHang() + " ?","Xác nhận xóa",JOptionPane.YES_NO_OPTION);

        if(confirm == JOptionPane.YES_OPTION){
            hdao = new HangDAO();
            if(hdao.deleteHang(h.getMaHang())){
                JOptionPane.showMessageDialog(this,"Xóa hãng thành công");
                reloadHang();
                loadTable();
            }
        }
    }
    private void thoat(){
        System.exit(0);
    }
    private void clear(){
        txtMalt.setText("");
        txtTenlt.setText("");
        txtGialt.setText("");
        txtSoluonglt.setText("");
        txtMalt.requestFocus();
        listHang.clearSelection();
    }
    private void loadcbHang(){
        cbHanglt.removeAllItems();
        for(Hang h : listDatahang){
          cbHanglt.addItem(h);
        }
    }
    private void loadcbNcc(){
        cbMancc.removeAllItems();
        nccdao = new NhaCungCapDAO();
        List<NhaCungCap> listNCC = nccdao.getTTNcc();
        for(NhaCungCap ncc : listNCC){
            cbMancc.addItem(ncc);
        }
    }
    private void loadTableByHang(String maHang){
        modelTable.setRowCount(0);
        ltdao = new LaptopDAO();
        listDataLaptop = ltdao.getdanhsachLaptop(maHang);

        for(LapTop lt : listDataLaptop){
            modelTable.addRow(new Object[]{
                lt.getMaLapTop(),
                lt.getTenLapTop(),
                lt.getTenHang(),
                lt.getGia(),
                lt.getSoLuong(),
                lt.getMaNCC(),
                lt.getMaHang()
            });
        }
    }
    private void loadTable(){
        modelTable.setRowCount(0);
        ltdao = new LaptopDAO();
        listDataLaptop = ltdao.getAllLaptop();
        for(LapTop lt : listDataLaptop){
            modelTable.addRow(new Object[]{
                lt.getMaLapTop(),
                lt.getTenLapTop(),
                lt.getTenHang(),
                lt.getGia(),
                lt.getSoLuong(),
                lt.getMaNCC(),
                lt.getMaHang()
            });
        }
    }
    private void reloadHang(){
        hdao = new HangDAO();
        listDatahang = hdao.getttHang();

        // ===== Reload JList Hãng =====
        modelHang.clear();
        for(Hang h : listDatahang){
            modelHang.addElement(h);
        }

        loadcbHang();
        loadcbNcc();
    }
    
    
    
}

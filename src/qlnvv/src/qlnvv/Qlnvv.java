package qlnvv;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Qlnvv extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMa, txtTen, txtNgaySinh, txtSDT, txtEmail, txtDiaChi, txtLuong, txtSearch;
    private JComboBox<String> cbGioiTinh, cbChucVu, cbTrangThai;
    private JButton btnAdd, btnUpdate, btnDelete, btnSearch; 
    public Qlnvv() {
        initGui();
        addAction();
        loadTable();
    }
    private void initGui() {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("HỆ THỐNG QUẢN LÝ NHÂN VIÊN");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pnNorth.add(lblTitle);
        add(pnNorth, BorderLayout.NORTH);
        JPanel pnWest = new JPanel();
        pnWest.setLayout(new BoxLayout(pnWest, BoxLayout.Y_AXIS));
        pnWest.setPreferredSize(new Dimension(300, 0));
        pnWest.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

        pnWest.add(new JLabel("Mã Nhân Viên:"));
        pnWest.add(txtMa = new JTextField(20));
        pnWest.add(new JLabel("Họ Tên:"));
        pnWest.add(txtTen = new JTextField(20));
        pnWest.add(new JLabel("Giới Tính:"));
        pnWest.add(cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"}));
        pnWest.add(new JLabel("Ngày Sinh:"));
        pnWest.add(txtNgaySinh = new JTextField(20));
        pnWest.add(new JLabel("Số Điện Thoại:"));
        pnWest.add(txtSDT = new JTextField(20));
        pnWest.add(new JLabel("Email:"));
        pnWest.add(txtEmail = new JTextField(20));
        pnWest.add(new JLabel("Địa Chỉ:"));
        pnWest.add(txtDiaChi = new JTextField(20));
        pnWest.add(new JLabel("Chức Vụ:"));
        pnWest.add(cbChucVu = new JComboBox<>(new String[]{"Nhân Viên", "Trưởng Phòng", "Giám Đốc"}));
        pnWest.add(new JLabel("Lương:"));
        pnWest.add(txtLuong = new JTextField(20));
        pnWest.add(new JLabel("Trạng Thái:"));
        pnWest.add(cbTrangThai = new JComboBox<>(new String[]{"Đang làm việc", "Đã nghỉ"}));
        add(pnWest, BorderLayout.WEST);
        String[] cols = {"Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SĐT", "Email", "Địa Chỉ", "Chức Vụ", "Lương", "Trạng Thái"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel pnSouth = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnAdd = new JButton("THÊM");
        btnUpdate = new JButton("SỬA");
        btnDelete = new JButton("XÓA");
        txtSearch = new JTextField(15);
        btnSearch = new JButton("TÌM KIẾM");
        pnSouth.add(btnAdd);
        pnSouth.add(btnUpdate);
        pnSouth.add(btnDelete);
        pnSouth.add(new JLabel(" | Tìm kiếm:"));
        pnSouth.add(txtSearch);
        pnSouth.add(btnSearch);
        add(pnSouth, BorderLayout.SOUTH);
    }
    private void addAction() {
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this); 
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) fillForm(row);
            }
        });
    }
    private void loadTable() {
        model.setRowCount(0);
        DAO dao = new DAO();
        for (nhanvienn nv : dao.getAll()) {
            addRowToModel(nv);
        }
    }
    private void addRowToModel(nhanvienn nv) {
        model.addRow(new Object[]{
            nv.getMaNV(), nv.getHoTen(), nv.getGioiTinh(), nv.getNgaySinh(),
            nv.getSdt(), nv.getEmail(), nv.getDiaChi(), nv.getChucVu(),
            nv.getLuong(), nv.getTrangThai()
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnAdd) newNV();
        else if (src == btnUpdate) updateNV();
        else if (src == btnDelete) deleteNV();
        else if (src == btnSearch) searchNV();
    }
    private void newNV() {
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã NV!");
            return;
        }
        nhanvienn nv = getForm();
        if (new DAO().insert(nv)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            loadTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }
    private void updateNV() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng để sửa!");
            return;
        }
        if (new DAO().update(getForm())) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadTable();
        }
    }
    private void deleteNV() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        String ma = model.getValueAt(row, 0).toString();
        if (JOptionPane.showConfirmDialog(this, "Xóa nhân viên " + ma + "?", "Xác nhận", JOptionPane.YES_NO_OPTION) == 0) {
            if (new DAO().delete(ma)) {
                loadTable();
                clearFields();
            }
        }
    }
    private void searchNV() {
        String key = txtSearch.getText().trim();
        model.setRowCount(0);
        List<nhanvienn> list = new DAO().search(key);
        for (nhanvienn nv : list) addRowToModel(nv);
    }
    private void clearFields() {
        txtMa.setText(""); txtTen.setText(""); txtNgaySinh.setText("");
        txtSDT.setText(""); txtEmail.setText(""); txtDiaChi.setText("");
        txtLuong.setText(""); txtSearch.setText("");
        loadTable();
    }
    private void fillForm(int row) {
        txtMa.setText(model.getValueAt(row, 0).toString());
        txtTen.setText(model.getValueAt(row, 1).toString());
        cbGioiTinh.setSelectedItem(model.getValueAt(row, 2).toString());
        txtNgaySinh.setText(model.getValueAt(row, 3).toString());
        txtSDT.setText(model.getValueAt(row, 4).toString());
        txtEmail.setText(model.getValueAt(row, 5).toString());
        txtDiaChi.setText(model.getValueAt(row, 6).toString());
        cbChucVu.setSelectedItem(model.getValueAt(row, 7).toString());
        txtLuong.setText(model.getValueAt(row, 8).toString());
        cbTrangThai.setSelectedItem(model.getValueAt(row, 9).toString());
    }
    private nhanvienn getForm() {
        return new nhanvienn(
            txtMa.getText(), txtTen.getText(), cbGioiTinh.getSelectedItem().toString(),
            txtNgaySinh.getText(), txtSDT.getText(), txtEmail.getText(),
            txtDiaChi.getText(), cbChucVu.getSelectedItem().toString(),
            Double.parseDouble(txtLuong.getText().isEmpty() ? "0" : txtLuong.getText()),
            cbTrangThai.getSelectedItem().toString()
        );
    }
    public static void main(String[] args) {
        new Qlnvv().setVisible(true);
    }
}
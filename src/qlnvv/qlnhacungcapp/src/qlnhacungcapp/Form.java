/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author DELL
 */
package qlnhacungcapp;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Form extends JFrame implements ActionListener {

    // ===== THÊM BIẾN TOÀN CỤC =====
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtSearch, txtEmail, txtSDT, txtDiaChi, txtTen, txtMa;
    private JTextArea txtNote;
    private JButton btnAdd, btnClear, btnDelete, btnUpdate;

    public Form() {
        initGui();
        addAction();

        // ===== GỌI LOAD DATA =====
        loadTable();
    }

    // ===== HÀM LOAD DỮ LIỆU TỪ MYSQL =====
    private void loadTable() {
        model.setRowCount(0);
        NhaCungCapDAO dao = new NhaCungCapDAO();

        for (NhaCungCap ncc : dao.getAll()) {
            model.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getSdt(),
                ncc.getDiaChi(),
                ncc.getEmail(),
                ncc.getGhiChu()
            });
        }
    }

    public static void main(String[] args) {
        new Form().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == btnAdd){
            newNcc();
        }else if(src == btnUpdate){
            updateNcc();
        }else if(src == btnDelete){
            deleteNcc();
        }else{
            clear();
        }

    }

    private void initGui() {
    // ===== FRAME =====
        setTitle("Quản lý nhà cung cấp");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== NORTH =====
        JPanel pnNorth = new JPanel();
        pnNorth.setBackground(new Color(0, 0, 153));

        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ CUNG CẤP");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));

        pnNorth.add(lblTitle);
        add(pnNorth, BorderLayout.NORTH);

        // ===== WEST =====
        JPanel pnWest = new JPanel(new GridBagLayout());
        pnWest.setPreferredSize(new Dimension(330, 0));
        pnWest.setBorder(BorderFactory.createTitledBorder("Thông tin NCC"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Mã NCC
        gbc.gridx = 0; gbc.gridy = 0;
        pnWest.add(new JLabel("Mã NCC:"), gbc);

        gbc.gridx = 1;
        txtMa = new JTextField(15);
        txtMa.setEditable(false);
        pnWest.add(txtMa, gbc);

        // Tên NCC
        gbc.gridx = 0; gbc.gridy++;
        pnWest.add(new JLabel("Tên NCC:"), gbc);

        gbc.gridx = 1;
        txtTen = new JTextField(15);
        pnWest.add(txtTen, gbc);

      // Địa chỉ
      gbc.gridx = 0; gbc.gridy++;
      pnWest.add(new JLabel("Địa chỉ:"), gbc);

       gbc.gridx = 1;
       txtDiaChi = new JTextField(15);
       pnWest.add(txtDiaChi, gbc);

       // SĐT
       gbc.gridx = 0; gbc.gridy++;
       pnWest.add(new JLabel("SĐT:"), gbc);

       gbc.gridx = 1;
       txtSDT = new JTextField(15);
       pnWest.add(txtSDT, gbc);

       // Email
       gbc.gridx = 0; gbc.gridy++;
       pnWest.add(new JLabel("Email:"), gbc);

       gbc.gridx = 1;
       txtEmail = new JTextField(15);
       pnWest.add(txtEmail, gbc);

       // Ghi chú
       gbc.gridx = 0; gbc.gridy++;
       gbc.anchor = GridBagConstraints.NORTHWEST;
       pnWest.add(new JLabel("Ghi chú:"), gbc);

       gbc.gridx = 1;
       txtNote = new JTextArea(4, 15);
       pnWest.add(new JScrollPane(txtNote), gbc);
       add(pnWest, BorderLayout.WEST);


        // ===== CENTER =====
        model = new DefaultTableModel(
                new String[]{"Mã NCC", "Tên NCC", "SĐT", "Địa chỉ", "Email", "Ghi chú"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== SOUTH =====
        JPanel pnSouth = new JPanel();

        btnAdd = new JButton("THÊM");
        btnUpdate = new JButton("SỬA");
        btnDelete = new JButton("XÓA");
        btnClear = new JButton("CLEAR");

        pnSouth.add(btnAdd);
        pnSouth.add(btnUpdate);
        pnSouth.add(btnDelete);
        pnSouth.add(btnClear);

        add(pnSouth, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void addAction() {
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row  = table.getSelectedRow();
                if(row >=0){
                    txtMa.setText(model.getValueAt(row, 0)== null ? "" : model.getValueAt(row, 0).toString());
                    txtTen.setText(model.getValueAt(row, 1)== null ? "" : model.getValueAt(row, 1).toString());
                    txtSDT.setText(model.getValueAt(row, 2)== null ? "" : model.getValueAt(row, 2).toString());
                    txtDiaChi.setText(model.getValueAt(row, 3)== null ? "" : model.getValueAt(row, 3).toString());
                    txtEmail.setText(model.getValueAt(row, 4)== null ? "" : model.getValueAt(row, 4).toString());
                    txtNote.setText(model.getValueAt(row, 5)== null ? "" : model.getValueAt(row, 5).toString());
                }
            }
            
        });
    }

    private void newNcc() {
    String ten = txtTen.getText().trim();
    String sdt = txtSDT.getText().trim();
    String diaChi = txtDiaChi.getText().trim();
    String email = txtEmail.getText().trim();
    String ghiChu = txtNote.getText().trim();

    if (ten.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên NCC không được để trống!");
        txtTen.requestFocus();
        return;
    }

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn thêm nhà cung cấp?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {
        NhaCungCap ncc = new NhaCungCap(0, ten, sdt, diaChi, email, ghiChu);
        NhaCungCapDAO dao = new NhaCungCapDAO();

        if (dao.insert(ncc)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }
}
    private void deleteNcc() {
    int row = table.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần xóa!");
        return;
    }

    int ma = Integer.parseInt(model.getValueAt(row, 0).toString());

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa nhà cung cấp này?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {
        NhaCungCapDAO dao = new NhaCungCapDAO();
        if (dao.delete(ma)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công!");
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
        }
    }
}
   private void updateNcc() {
    int row = table.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp cần sửa!");
        return;
    }

    if (txtTen.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên NCC không được để trống!");
        txtTen.requestFocus();
        return;
    }

    int ma = Integer.parseInt(txtMa.getText().trim());
    String ten = txtTen.getText().trim();
    String sdt = txtSDT.getText().trim();
    String diaChi = txtDiaChi.getText().trim();
    String email = txtEmail.getText().trim();
    String ghiChu = txtNote.getText().trim();

    int choice = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn cập nhật nhà cung cấp?",
            "Xác nhận cập nhật",
            JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {
        NhaCungCap ncc = new NhaCungCap(ma, ten, sdt, diaChi, email, ghiChu);
        NhaCungCapDAO dao = new NhaCungCapDAO();

        if (dao.update(ncc)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        }
    }
}

   private void clear() {
    txtMa.setText("");
    txtTen.setText("");
    txtSDT.setText("");
    txtDiaChi.setText("");
    txtEmail.setText("");
    txtNote.setText("");
    table.clearSelection();
}

}

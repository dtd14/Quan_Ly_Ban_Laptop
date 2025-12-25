///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Quan_Ly_Khach_Hang.View;
//
//import Quan_Ly_Khach_Hang.Controller.CustomerController;
//import Quan_Ly_Khach_Hang.Model.Customer;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.HeadlessException;
//import java.util.List;
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.RowFilter;
//import javax.swing.border.Border;
//import javax.swing.border.TitledBorder;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableRowSorter;
//
///**
// *
// * @author Admin
// */
//public class CustomerView extends JFrame {
//
//    public JButton btn_add;
//    public JButton btn_clear;
//    public JButton btn_update;
//    public JButton btn_delete;
//    public JButton btn_exit;
//    public JTable tbl;
//    public JTextField txt_id;
//    public JTextField txt_search;
//    public JTextField txt_name;
//    public JTextField txt_number;
//    public JTextField txt_address;
//    public JTextField txt_email;
//    public DefaultTableModel tableModel;
//    private TableRowSorter<DefaultTableModel> sorter; 
//
//    public CustomerView(String title) throws HeadlessException {
//        super(title);
//        initUI();
//        initTableFilter();
//        change();
//        CustomerController controller = new CustomerController(this);
//
//    }
////////////////////////////////////////////
//    public static void main(String[] args) {
//        CustomerView view = new CustomerView("Customer Management");
//        view.setVisible(true);
//        view.txt_name.requestFocus();
//    }
////////////////////////////////////////////
//    private void initTableFilter()
//    {
//        tableModel = (DefaultTableModel) tbl.getModel();
//        sorter = new TableRowSorter<>(tableModel);
//        tbl.setRowSorter(sorter);}
//    private void change(){
//        txt_search.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                filter();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                filter();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                filter();
//            }
//        });
//    }
//    private void filter()
//    {
//        String keyword = txt_search.getText().trim();
//        if(keyword.length()==0) sorter.setRowFilter(null);
//        else
//        {
//            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
//        }
//    }
//    public void initUI() {
//        this.setSize(1000, 650);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        JPanel PnBorder = new JPanel(new BorderLayout());
//        //north
//        JPanel Pn_north = new JPanel();
//        Pn_north.setPreferredSize(new Dimension(0, 45));
//        JLabel title = new JLabel("Quản Lý Khách Hàng");
//        title.setHorizontalAlignment(JLabel.CENTER);
//        Font f = new Font("Arial", Font.BOLD, 25);
//        title.setFont(f);
//        Pn_north.setBackground(new Color(0, 0, 153));
//        title.setForeground(Color.WHITE);
//        Pn_north.add(title);
//        PnBorder.add(Pn_north, BorderLayout.NORTH);
//        //
//
//        //west
//        JPanel Pn_west = new JPanel();
//        Border bd = BorderFactory.createLineBorder(Color.BLUE, 3);
//        TitledBorder ttbd = new TitledBorder(bd, "Thông tin khách hàng");
//        Font font_bd = new Font("Arial", Font.ITALIC, 20);
//        ttbd.setTitleFont(font_bd);
//        Pn_west.setPreferredSize(new Dimension(330, 200));
//        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 35));
//        JPanel id = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 13));
//        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 13));
//        JPanel number = new JPanel(new FlowLayout(FlowLayout.LEFT, 19, 13));
//        JPanel address = new JPanel(new FlowLayout(FlowLayout.LEFT, 38, 13));
//        JPanel email = new JPanel(new FlowLayout(FlowLayout.LEFT, 42, 13));
//
//        JLabel lbl_id = new JLabel("Mã Khách Hàng:");
//        JLabel lbl_search = new JLabel("Tìm kiếm:");
//        JLabel lbl_name = new JLabel("Tên Khách Hàng:");
//        JLabel lbl_number = new JLabel("Số Điện Thoại:");
//        JLabel lbl_address = new JLabel("Địa chỉ:");
//        JLabel lbl_email = new JLabel("Email:");
//        Font font_lbl = new Font("Arial", Font.ITALIC + Font.BOLD, 11);
//        lbl_id.setFont(font_lbl);
//        lbl_search.setFont(new Font("Arial", Font.BOLD, 15) {
//        });
//        lbl_name.setFont(font_lbl);
//        lbl_number.setFont(font_lbl);
//        lbl_address.setFont(font_lbl);
//        lbl_email.setFont(font_lbl);
//        lbl_search.setForeground(Color.red);
//
//        txt_id = new JTextField(15);
//        txt_search = new JTextField(20);
//        txt_name = new JTextField(15);
//        txt_number = new JTextField(15);
//        txt_address = new JTextField(15);
//        txt_email = new JTextField(15);
//        txt_id.setEnabled(false);
//
//        search.add(lbl_search);
//        search.add(txt_search);
//        id.add(lbl_id);
//        id.add(txt_id);
//        name.add(lbl_name);
//        name.add(txt_name);
//        number.add(lbl_number);
//        number.add(txt_number);
//        address.add(lbl_address);
//        address.add(txt_address);
//        email.add(lbl_email);
//        email.add(txt_email);
//
//        JPanel PN_TT = new JPanel();
//        PN_TT.setLayout(new BoxLayout(PN_TT, BoxLayout.Y_AXIS));
//        PN_TT.add(search);
//        PN_TT.add(id);
//        PN_TT.add(name);
//        PN_TT.add(number);
//        PN_TT.add(address);
//        PN_TT.add(email);
//
//        JPanel PN_BUT = new JPanel();
//        PN_BUT.setLayout(new BoxLayout(PN_BUT, BoxLayout.Y_AXIS));
//
//        btn_add = new JButton("THÊM KHÁCH HÀNG");
//        btn_clear = new JButton("XÓA NỘI DUNG");
//
//        Dimension btnSize = new Dimension(170, 50);
//
//        btn_add.setMaximumSize(btnSize);
//        btn_clear.setMaximumSize(btnSize);
//
//        btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
//        btn_clear.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        PN_BUT.add(Box.createVerticalStrut(30));
//        PN_BUT.add(btn_add);
//        PN_BUT.add(Box.createVerticalStrut(25)); // khoảng cách giữa 2 nút
//        PN_BUT.add(btn_clear);
//
//        Pn_west.add(PN_TT);
//        Pn_west.add(PN_BUT);
//        Pn_west.setBorder(ttbd);
//        PnBorder.add(Pn_west, BorderLayout.WEST);
//        //
//
//        //center
//        JPanel Pn_Center = new JPanel(new BorderLayout(7, 7));
//        JPanel table = new JPanel(new BorderLayout());
//        TitledBorder border = BorderFactory.createTitledBorder("Thông tin chi tiết");
//        Font tittle_tbl = new Font("Arial", Font.BOLD, 20);
//        border.setTitleFont(tittle_tbl);
//        border.setTitleJustification(TitledBorder.CENTER);
//        table.setBorder(border);
//        String[] tableHeader = {"Mã KH", "Tên KH", "Số đt", "Địa chỉ", "Email"};
//        tableModel = new DefaultTableModel(tableHeader, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        tbl = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(tbl);
//        tableScrollPane.setPreferredSize(new Dimension(550, 440));
//        tbl.getTableHeader().setReorderingAllowed(false);
//        table.add(tableScrollPane, BorderLayout.CENTER);
//        Pn_Center.add(table, BorderLayout.NORTH);
//        JPanel but_suaxoa = new JPanel();
//        but_suaxoa.setLayout(new GridLayout(1, 3, 25, 0));
//        but_suaxoa.setBorder(BorderFactory.createEmptyBorder(80, 30, 50, 30));
//
//        btn_update = new JButton("SỬA THÔNG TIN");
//        btn_delete = new JButton("XÓA KHÁCH HÀNG");
//        btn_exit = new JButton("THOÁT");
//
//        but_suaxoa.add(btn_update);
//        but_suaxoa.add(btn_delete);
//        but_suaxoa.add(btn_exit);
//        Pn_Center.add(but_suaxoa, BorderLayout.SOUTH);
//        PnBorder.add(Pn_Center, BorderLayout.CENTER);
//        //
//        this.add(PnBorder);
//    }
//public void showTable(List<Customer> list) {
//    tableModel.setRowCount(0);
//    for (Customer c : list) {
//        tableModel.addRow(new Object[]{
//    c.getId(),
//    c.getName(),
//    c.getNumber(),
//    c.getAddress(),
//    c.getEmail()
//});
//    }
//}
//}
//
//
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quan_Ly_Khach_Hang.View;

import Quan_Ly_Khach_Hang.Controller.CustomerController;
import Quan_Ly_Khach_Hang.Model.Customer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class CustomerView extends JFrame {

    private JButton btn_add;
    private JButton btn_clear;
    private JButton btn_update;
    private JButton btn_delete;
    private JButton btn_exit;
    private JTable tbl;
    private JTextField txt_id;
    private JTextField txt_search;
    private JTextField txt_name;
    private JTextField txt_number;
    private JTextField txt_address;
    private JTextField txt_email;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public CustomerView(String title) throws HeadlessException {
        super(title);
        initUI();
        initTableFilter();
        change();
        CustomerController controller = new CustomerController(this);
    }

    public JButton getBtnAdd() { return btn_add; }
    public JButton getBtnClear() { return btn_clear; }
    public JButton getBtnUpdate() { return btn_update; }
    public JButton getBtnDelete() { return btn_delete; }
    public JButton getBtnExit() { return btn_exit; }
    public JTable getTable() { return tbl; }
    public DefaultTableModel getTableModel() { return tableModel; }
    
    public String getCustomerId() {
        return txt_id.getText().trim();
    }
    
    public void setCustomerId(String id) {
        txt_id.setText(id);
    }
    
    public String getCustomerName() {
        return txt_name.getText().trim();
    }
    
    public void setCustomerName(String name) {
        txt_name.setText(name);
    }
    
    public String getCustomerNumber() {
        return txt_number.getText().trim();
    }
    
    public void setCustomerNumber(String number) {
        txt_number.setText(number);
    }
    
    public String getCustomerAddress() {
        return txt_address.getText().trim();
    }
    
    public void setCustomerAddress(String address) {
        txt_address.setText(address);
    }
    
    public String getCustomerEmail() {
        return txt_email.getText().trim();
    }
    
    public void setCustomerEmail(String email) {
        txt_email.setText(email);
    }
    
    public void clearForm() {
        txt_id.setText("");
        txt_name.setText("");
        txt_number.setText("");
        txt_address.setText("");
        txt_email.setText("");
        tbl.clearSelection();
        txt_name.requestFocus();
    }
    
    public void focusName() {
        txt_name.requestFocus();
    }
    
    public void focusNumber() {
        txt_number.requestFocus();
    }
    
    public void focusEmail() {
        txt_email.requestFocus();
    }
    
    public int getSelectedRow() {
        return tbl.getSelectedRow();
    }
    
    public int getSelectedRowCount() {
        return tbl.getSelectedRowCount();
    }
    
    public Object getTableValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }
    
    public static void main(String[] args) {
        CustomerView view = new CustomerView("Customer Management");
        view.setVisible(true);
        view.txt_name.requestFocus();
    }
    
    private void initTableFilter() {
        tableModel = (DefaultTableModel) tbl.getModel();
        sorter = new TableRowSorter<>(tableModel);
        tbl.setRowSorter(sorter);
    }
    
    private void change() {
        txt_search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }
        });
    }
    
    private void filter() {
        String keyword = txt_search.getText().trim();
        if(keyword.length()==0) sorter.setRowFilter(null);
        else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }
    
    public void initUI() {
        this.setSize(1000, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel PnBorder = new JPanel(new BorderLayout());
        PnBorder.setBackground(new Color(245, 245, 245));
        
        //north
        JPanel Pn_north = new JPanel();
        Pn_north.setPreferredSize(new Dimension(0, 60));
        JLabel title = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        title.setHorizontalAlignment(JLabel.CENTER);
        Font f = new Font("Arial", Font.BOLD, 28);
        title.setFont(f);
        Pn_north.setBackground(new Color(41, 128, 185)); 
        title.setForeground(Color.WHITE);
        Pn_north.add(title);
        PnBorder.add(Pn_north, BorderLayout.NORTH);
        
        //west
        JPanel Pn_west = new JPanel();
        Pn_west.setBackground(Color.WHITE);
        Border bd = BorderFactory.createLineBorder(new Color(52, 152, 219), 2);
        TitledBorder ttbd = new TitledBorder(bd, "Thông tin khách hàng");
        Font font_bd = new Font("Arial", Font.BOLD, 18);
        ttbd.setTitleFont(font_bd);
        ttbd.setTitleColor(new Color(41, 128, 185));
        Pn_west.setPreferredSize(new Dimension(350, 200));
        
        JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        JPanel id = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel number = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel address = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel email = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        search.setBackground(Color.WHITE);
        id.setBackground(Color.WHITE);
        name.setBackground(Color.WHITE);
        number.setBackground(Color.WHITE);
        address.setBackground(Color.WHITE);
        email.setBackground(Color.WHITE);

        JLabel lbl_id = new JLabel("Mã Khách Hàng:");
        JLabel lbl_search = new JLabel("Tìm kiếm:");
        JLabel lbl_name = new JLabel("Tên Khách Hàng:");
        JLabel lbl_number = new JLabel("Số Điện Thoại:");
        JLabel lbl_address = new JLabel("Địa chỉ:");
        JLabel lbl_email = new JLabel("Email:");
        
        Font font_lbl = new Font("Arial", Font.PLAIN, 13);
        Font font_search = new Font("Arial", Font.BOLD, 14);
        
        lbl_id.setFont(font_lbl);
        lbl_search.setFont(font_search);
        lbl_name.setFont(font_lbl);
        lbl_number.setFont(font_lbl);
        lbl_address.setFont(font_lbl);
        lbl_email.setFont(font_lbl);
        
        lbl_search.setForeground(new Color(231, 76, 60));
        
        Dimension lblSize = new Dimension(120, 25);
        lbl_id.setPreferredSize(lblSize);
        lbl_name.setPreferredSize(lblSize);
        lbl_number.setPreferredSize(lblSize);
        lbl_address.setPreferredSize(lblSize);
        lbl_email.setPreferredSize(lblSize);

        txt_id = new JTextField(16);
        txt_search = new JTextField(20);
        txt_name = new JTextField(16);
        txt_number = new JTextField(16);
        txt_address = new JTextField(16);
        txt_email = new JTextField(16);
        
        txt_id.setEnabled(false);
        txt_id.setBackground(new Color(236, 240, 241));
        
        Border textFieldBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(3, 5, 3, 5)
        );
        
        txt_id.setBorder(textFieldBorder);
        txt_search.setBorder(textFieldBorder);
        txt_name.setBorder(textFieldBorder);
        txt_number.setBorder(textFieldBorder);
        txt_address.setBorder(textFieldBorder);
        txt_email.setBorder(textFieldBorder);
        
        Font txtFont = new Font("Arial", Font.PLAIN, 14);
        txt_id.setFont(txtFont);
        txt_search.setFont(txtFont);
        txt_name.setFont(txtFont);
        txt_number.setFont(txtFont);
        txt_address.setFont(txtFont);
        txt_email.setFont(txtFont);

        search.add(lbl_search);
        search.add(txt_search);
        id.add(lbl_id);
        id.add(txt_id);
        name.add(lbl_name);
        name.add(txt_name);
        number.add(lbl_number);
        number.add(txt_number);
        address.add(lbl_address);
        address.add(txt_address);
        email.add(lbl_email);
        email.add(txt_email);

        JPanel PN_TT = new JPanel();
        PN_TT.setLayout(new BoxLayout(PN_TT, BoxLayout.Y_AXIS));
        PN_TT.setBackground(Color.WHITE);
        PN_TT.add(search);
        PN_TT.add(id);
        PN_TT.add(name);
        PN_TT.add(number);
        PN_TT.add(address);
        PN_TT.add(email);

        JPanel PN_BUT = new JPanel();
        PN_BUT.setLayout(new BoxLayout(PN_BUT, BoxLayout.Y_AXIS));
        PN_BUT.setBackground(Color.WHITE);

        btn_add = new JButton("THÊM KHÁCH HÀNG");
        btn_clear = new JButton("XÓA NỘI DUNG");

        Dimension btnSize = new Dimension(200, 45);
        btn_add.setMaximumSize(btnSize);
        btn_clear.setMaximumSize(btnSize);
        btn_add.setPreferredSize(btnSize);
        btn_clear.setPreferredSize(btnSize);

        btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn_clear.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btn_add.setBackground(new Color(46, 204, 113));
        btn_add.setForeground(Color.WHITE);
        btn_add.setFont(new Font("Arial", Font.BOLD, 13));
        btn_add.setFocusPainted(false);
        btn_add.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        btn_clear.setBackground(new Color(230, 126, 34));
        btn_clear.setForeground(Color.WHITE);
        btn_clear.setFont(new Font("Arial", Font.BOLD, 13));
        btn_clear.setFocusPainted(false);
        btn_clear.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        PN_BUT.add(Box.createVerticalStrut(25));
        PN_BUT.add(btn_add);
        PN_BUT.add(Box.createVerticalStrut(20));
        PN_BUT.add(btn_clear);

        Pn_west.add(PN_TT);
        Pn_west.add(PN_BUT);
        Pn_west.setBorder(ttbd);
        PnBorder.add(Pn_west, BorderLayout.WEST);
        
        //center
        JPanel Pn_Center = new JPanel(new BorderLayout(10, 10));
        Pn_Center.setBackground(new Color(245, 245, 245));
        Pn_Center.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel table = new JPanel(new BorderLayout());
        table.setBackground(Color.WHITE);
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "Thông tin chi tiết"
        );
        Font tittle_tbl = new Font("Arial", Font.BOLD, 18);
        border.setTitleFont(tittle_tbl);
        border.setTitleColor(new Color(41, 128, 185));
        border.setTitleJustification(TitledBorder.CENTER);
        table.setBorder(border);
        
        String[] tableHeader = {"Mã KH", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Email"};
        tableModel = new DefaultTableModel(tableHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tbl = new JTable(tableModel);
        
        // Tùy chỉnh table đẹp hơn
        tbl.setFont(new Font("Arial", Font.PLAIN, 13));
        tbl.setRowHeight(28);
        tbl.setSelectionBackground(new Color(52, 152, 219));
        tbl.setSelectionForeground(Color.WHITE);
        tbl.setGridColor(new Color(189, 195, 199));
        tbl.setShowGrid(true);
        
        // Header table đẹp hơn
        JTableHeader header = tbl.getTableHeader();
        header.setBackground(new Color(41, 128, 185));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        
        // Căn giữa cho cột Mã KH
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbl.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        
        // Set độ rộng cột
        tbl.getColumnModel().getColumn(0).setPreferredWidth(80);  // Mã KH
        tbl.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên
        tbl.getColumnModel().getColumn(2).setPreferredWidth(120); // SĐT
        tbl.getColumnModel().getColumn(3).setPreferredWidth(200); // Địa chỉ
        tbl.getColumnModel().getColumn(4).setPreferredWidth(150); // Email
        
        tbl.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane tableScrollPane = new JScrollPane(tbl);
        tableScrollPane.setPreferredSize(new Dimension(550, 450));
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 1));
        
        table.add(tableScrollPane, BorderLayout.CENTER);
        Pn_Center.add(table, BorderLayout.CENTER);
        
        // BUTTONS dưới table
        JPanel but_suaxoa = new JPanel();
        but_suaxoa.setLayout(new GridLayout(1, 3, 15, 0));
        but_suaxoa.setBorder(BorderFactory.createEmptyBorder(15, 30, 20, 30));
        but_suaxoa.setBackground(new Color(245, 245, 245));

        btn_update = new JButton("SỬA THÔNG TIN");
        btn_delete = new JButton("XÓA KHÁCH HÀNG");
        btn_exit = new JButton("THOÁT");
        
        Dimension bottomBtnSize = new Dimension(150, 45);
        btn_update.setPreferredSize(bottomBtnSize);
        btn_delete.setPreferredSize(bottomBtnSize);
        btn_exit.setPreferredSize(bottomBtnSize);
        
        // Màu nút UPDATE - Xanh dương
        btn_update.setBackground(new Color(52, 152, 219));
        btn_update.setForeground(Color.WHITE);
        btn_update.setFont(new Font("Arial", Font.BOLD, 13));
        btn_update.setFocusPainted(false);
        
        // Màu nút DELETE - Đỏ
        btn_delete.setBackground(new Color(231, 76, 60));
        btn_delete.setForeground(Color.WHITE);
        btn_delete.setFont(new Font("Arial", Font.BOLD, 13));
        btn_delete.setFocusPainted(false);
        
        // Màu nút EXIT - Xám
        btn_exit.setBackground(new Color(149, 165, 166));
        btn_exit.setForeground(Color.WHITE);
        btn_exit.setFont(new Font("Arial", Font.BOLD, 13));
        btn_exit.setFocusPainted(false);

        but_suaxoa.add(btn_update);
        but_suaxoa.add(btn_delete);
        but_suaxoa.add(btn_exit);
        
        Pn_Center.add(but_suaxoa, BorderLayout.SOUTH);
        PnBorder.add(Pn_Center, BorderLayout.CENTER);
        
        this.add(PnBorder);
    }
    
    public void showTable(List<Customer> list) {
        tableModel.setRowCount(0);
        for (Customer c : list) {
            tableModel.addRow(new Object[]{
                c.getId(),
                c.getName(),
                c.getNumber(),
                c.getAddress(),
                c.getEmail()
            });
        }
    }
}
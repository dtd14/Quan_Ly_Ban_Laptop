/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quan_Ly_Khach_Hang.Controller;

import Quan_Ly_Khach_Hang.Model.Customer;
import Quan_Ly_Khach_Hang.Model.CustomerDAO;
import Quan_Ly_Khach_Hang.View.CustomerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admin
 */
public class CustomerController implements ActionListener {
    
    private CustomerView view;
    private CustomerDAO cus_dao;
    private List<Customer> list;
    public CustomerController(CustomerView view) {
        this.view = view;
        addAction();
        cus_dao = new CustomerDAO();
        load();
    }
    
    public void display_form() {
        int select_row = view.getSelectedRow();
        if (select_row < 0) {
            return;
        }
        view.setCustomerId(view.getTableValueAt(select_row, 0).toString());
        view.setCustomerName(view.getTableValueAt(select_row, 1).toString());
        view.setCustomerNumber(view.getTableValueAt(select_row, 2).toString());
        view.setCustomerAddress(view.getTableValueAt(select_row, 3).toString());
        view.setCustomerEmail(view.getTableValueAt(select_row, 4).toString());
    }
    
    public void addAction() {
        view.getBtnAdd().addActionListener(this);
        view.getBtnClear().addActionListener(this);
        view.getBtnDelete().addActionListener(this);
        view.getBtnExit().addActionListener(this);
        view.getBtnUpdate().addActionListener(this);
        view.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    display_form();
                }
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == view.getBtnAdd()) {
            add_cus();
        } else if (src == view.getBtnClear()) {
            clear_cus();
        } else if (src == view.getBtnDelete()) {
            delete_cus();
        } else if (src == view.getBtnExit()) {
            exit_cus();
        } else if (src == view.getBtnUpdate()) {
            update_cus();
        }
    }
    
    private void load() {
        list = cus_dao.getAll();
        view.showTable(list);
    }
    
    public void add_cus() {
        try {
            String address = view.getCustomerAddress();
            String email = view.getCustomerEmail();
            String name = view.getCustomerName();
            String number = view.getCustomerNumber();
            if (address.isEmpty() || email.isEmpty() || name.isEmpty() || number.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không được bỏ trống thông tin !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                view.focusName();
                return;
            }
            if (!number.matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(view, 
                    "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0 !", 
                    "Cảnh báo", 
                    JOptionPane.WARNING_MESSAGE);
                view.focusNumber();
                return;
            }
            int choice = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn thêm khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                if (cus_dao.insert_customer(new Customer(0, name, number, address, email))) {
                    JOptionPane.showMessageDialog(view, "Thêm khách hàng thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    clear_cus();
                    load();
                    view.focusName();
                } else {
                    JOptionPane.showMessageDialog(view, "Thêm khách hàng thất bại !", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clear_cus() {
        view.clearForm();
    }
    
    public void update_cus() {
        try {
            String id = view.getCustomerId();
            String name = view.getCustomerName();
            String number = view.getCustomerNumber();
            String address = view.getCustomerAddress();
            String email = view.getCustomerEmail();
            if (address.isEmpty() || email.isEmpty() || name.isEmpty() || number.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Không được bỏ trống thông tin !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                view.focusName();
                return;
            }
            if (!number.matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(view, 
                    "Số điện thoại phải có 10 chữ số và bắt đầu bằng 0 !", 
                    "Cảnh báo", 
                    JOptionPane.WARNING_MESSAGE);
                view.focusNumber();
                return;
            }
            int choice = JOptionPane.showConfirmDialog(view, "Cập nhật khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                if (cus_dao.update_customer(new Customer(Integer.parseInt(id), name, number, address, email))) {
                    JOptionPane.showMessageDialog(view, "Cập nhật khách hàng thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    clear_cus();
                    load();
                    view.focusName();
                } else {
                    JOptionPane.showMessageDialog(view, "Cập nhật khách hàng thất bại !", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void delete_cus() {
        if (view.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Chọn khách hàng muốn xóa !", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String id = view.getCustomerId();
            int choice = JOptionPane.showConfirmDialog(view, "Bạn có muốn xóa khách hàng này ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                if (cus_dao.delete_customer(new Customer(Integer.parseInt(id), "", "", "", ""))) {
                    JOptionPane.showMessageDialog(view, "Xóa khách hàng thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    clear_cus();
                    load();
                    view.focusName();
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa khách hàng thất bại !", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exit_cus() {
        int choice = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn thoát ?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            view.dispose();            
        }
        view.focusName();
    }
    
}

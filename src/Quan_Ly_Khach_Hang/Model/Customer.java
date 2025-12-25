/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quan_Ly_Khach_Hang.Model;

/**
 *
 * @author Admin
 */
public class Customer {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer(int id, String name, String number, String address, String email) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.email = email;
    }
    public Customer(){}
    
    private int id ;
    private String name;
    private String number;
    private String address;
    private String email;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.entity;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

import domain.InfoOrder;
import utils.function.Utils;

public class Orders extends InfoOrder {
    // private Long id;
    private Long user_id;
    // private String name;
    // private String address;
    private Long product_id;
    // private String phone;
    // private Long price;
    private Long order_id;

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    // public Orders(Long id, Long user_id, Long product_id, String name, String
    // address, String phone, Long price,
    // Long order_id) {
    // this.id = id;
    // this.product_id = product_id;
    // this.user_id = user_id;
    // this.name = name;
    // this.address = address;
    // this.phone = phone;
    // this.price = price;
    // this.order_id = order_id;
    // }

    public Long getOrder_id() {
        return order_id;
    }

    public Orders(Long id, Long user_id, Long product_id, String name, String address, String phone, Long price,
            Long order_id) {
        super(id, name, address, phone, price);
        this.user_id = user_id;
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    // public Long getPrice() {
    // return price;
    // }

    // public void setPrice(Long price) {
    // this.price = price;
    // }

    // public String getPhone() {
    // return phone;
    // }

    // public void setPhone(String phone) {
    // this.phone = phone;
    // }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    // public Long getUser_id() {
    // return user_id;
    // }

    // public void setUser_id(Long user_id) {
    // this.user_id = user_id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public String getAddress() {
    // return address;
    // }

    // public void setAddress(String address) {
    // this.address = address;
    // }

    
    // định dạng format để đọc dữ liệu trong file
    public String toStringFormatted() {
        return super.getId() + "?" + this.user_id + "?" + this.product_id + "?" + super.getName() + "?"
                + super.getAddress() + "?"
                + super.getPhone() + "?" + super.getPrice() + "?" + this.order_id;
    }


    // in ra và điều chỉnh chữ căn lề và khoảng cách dùng để gọi bên các class handle
    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-7d%s | %s%-10d%s | %s%-20s%s | %s%-60s%s | %s%-15s%s | %s%-20s%s |",
                BOLD, YELLOW, super.getId(), RESET,
                GREEN, this.user_id, RESET,
                RED, this.product_id, RESET,
                BLUE, super.getName(), RESET,
                YELLOW, super.getAddress(), RESET,
                CYAN, super.getPhone(), RESET,
                BOLD, new Utils().formatPrice(super.getPrice()), RESET);
    }
    // in ra bảng
    public static void printTableOrderForAdmin(List<Orders> ordersList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+---------+------------+----------------------+--------------------------------------------------------------+-----------------+----------------------+\n");
        sb.append(
                "| ID    | UserID  | OrderID    | Name                 | Address                                                      | Phone           |    Amount            |\n");
        sb.append(
                "+-------+---------+------------+----------------------+--------------------------------------------------------------+-----------------+----------------------+\n");
        for (Orders order : ordersList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN + "+-------+---------+------------+----------------------+--------------------------------------------------------------+-----------------+----------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }

}

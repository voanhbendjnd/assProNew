package domain.entity;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

import domain.InfoOrder;
import utils.function.Utils;

public class OrderUser extends InfoOrder {
    // private Long id;
    private String nameProduct;
    // private String nameUser;
    // private String address;
    // private String phone;
    private Long qty;
    private Long userId;
    // private Long price;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

    // public Long getPrice() {
    // return price;
    // }

    // public void setPrice(Long price) {
    // this.price = price;
    // }

    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    // public OrderUser(Long id, String nameProduct, String nameUser, String
    // address, String phone, Long userId,
    // Long price, int status) {
    // this.id = id;
    // this.nameProduct = nameProduct;
    // this.nameUser = nameUser;
    // this.address = address;
    // this.phone = phone;
    // this.userId = userId;
    // this.price = price;
    // this.status = status;
    // }

    // public Long getId() {
    // return id;
    // }

    public Long getQty() {
        return qty;
    }

    public OrderUser(Long id, String nameProduct, String userName, String address, String phone, Long userId,
            Long price, int status) {
        super(id, userName, address, phone, price);
        this.nameProduct = nameProduct;
        this.userId = userId;
        this.status = status;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public String getNameProduct() {
        return nameProduct;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    // public String getNameUser() {
    // return nameUser;
    // }

    // public void setNameUser(String nameUser) {
    // this.nameUser = nameUser;
    // }

    // public String getAddress() {
    // return address;
    // }

    // public void setAddress(String address) {
    // this.address = address;
    // }

    // public String getPhone() {
    // return phone;
    // }

    // public void setPhone(String phone) {
    // this.phone = phone;
    // }

    public String toStringFormatted() {
        return super.getId() + "?" + this.nameProduct + "?" + super.getName() + "?" + super.getAddress() + "?"
                + super.getPhone() + "?" + this.userId + "?" + super.getPrice() + "?" + this.status;
    }

    // public String toStringFormatted2() {
    // return this.id + "?" + this.nameProduct + "?" + this.nameUser + "?" +
    // this.address + "?"
    // + this.phone + "?" + this.userId + "?" + this.qty + "?" + this.price;
    // }

    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-30s%s | %s%-30s%s | %s%-30s%s | %s%-60s%s | %s%-20s%s | %s%-20s%s |",
                BOLD, YELLOW, super.getId(), RESET,
                RED, this.nameProduct, RESET,
                BOLD, new Utils().formatPrice(super.getPrice()), RESET,
                YELLOW, super.getName(), RESET,
                GREEN, super.getAddress(), RESET,
                BLUE, super.getPhone(), RESET,
                this.status == 1 ? GREEN : this.status == 2 ? RED : YELLOW,
                this.status == 1 ? "Confirmed" : this.status == 2 ? "Not Confirmed" : "Undetermined", RESET);
    }

    public static void printTableOrderForUser(List<OrderUser> ordersList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n");
        sb.append(
                "| ID | Product | Price | Name | Address | Phone | Status |\n");
        sb.append(
                "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n");
        for (OrderUser order : ordersList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN +
                            "+-------+--------------------------------+--------------------------------+--------------------------------+--------------------------------------------------------------+----------------------+----------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }

}

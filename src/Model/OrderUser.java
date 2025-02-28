package Model;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.List;

public class OrderUser {
    private Long id;
    private String nameProduct;
    private String nameUser;
    private String address;
    private String phone;
    private Long qty;
    private Long userId;
    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public OrderUser(Long id, String nameProduct, String nameUser, String address, String phone, Long userId) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.nameUser = nameUser;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
    }

    public OrderUser(Long id, String nameProduct, String nameUser, String address, String phone, Long userId,
            Long qty) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.nameUser = nameUser;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toStringFormatted() {
        return this.id + "?" + this.nameProduct + "?" + this.nameUser + "?" + this.address + "?"
                + this.phone + "?" + this.userId;
    }

    public String toStringFormatted2() {
        return this.id + "?" + this.nameProduct + "?" + this.nameUser + "?" + this.address + "?"
                + this.phone + "?" + this.userId + "?" + this.qty;
    }

    @Override
    public String toString() {
        return String.format("| %s%s%-5d%s | %s%-30s%s | %s%-30s%s | %s%-80s%s | %s%-30s%s |",
                BOLD, YELLOW, this.id, RESET,
                RED, this.nameProduct, RESET, YELLOW, this.nameUser, RESET, BLUE, this.address, RESET, GREEN,
                this.phone, RESET);
    }

    public static void printTableOrderForUser(List<OrderUser> ordersList) {
        StringBuilder sb = new StringBuilder();
        sb.append(BOLD + CYAN);
        sb.append(
                "+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+--------------------------------+\n");
        sb.append(
                "| ID    | Product                        | Name                           | Address                                                                          | Phone                          |\n");
        sb.append(
                "+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+--------------------------------+\n");
        for (OrderUser order : ordersList) {
            sb.append(order.toString()).append("\n");
            sb.append(
                    CYAN + "+-------+--------------------------------+--------------------------------+----------------------------------------------------------------------------------+--------------------------------+\n"
                            + RESET);
        }
        System.out.println(sb.toString());
    }

}

package utils.function;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Cart;
import domain.entity.Product;
import handle.HandleCart;
import handle.HandleProduct;
import setupFile.AllFile;
import utils.constant.TargetEnum;

public class CartProduct {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    private static Scanner sc = new Scanner(System.in);

    public void cartProduct(Long userId) {
        Long cartId = 0L;
        HandleProduct reader = new HandleProduct();

        System.out.println(CYAN + "══════════════════════════════════════" + RESET);
        System.out.print(BOLD + GREEN + "Please enter ID of the product you want add to cart: " + RESET);
        Long idProduct = sc.nextLong();
        sc.nextLine();

        boolean checkProduct = false;
        String name = "", brand = "", desc = "", date = "";
        TargetEnum target = TargetEnum.GAMING;
        Long price = null, code = null, stock = null;

        List<Product> proList = new HandleProduct().read(AllFile.fileProductTxt);
        for (Product x : proList) {
            if (x.getCode().equals(idProduct)) {
                brand = x.getBrand();
                target = x.getTarget();
                name = x.getName();
                desc = x.getDescription();
                price = x.getPrice();
                stock = x.getStock();
                date = x.getDateCreate();
                code = x.getCode();
                checkProduct = true;
                break; // Thoát vòng lặp khi tìm thấy sản phẩm
            }
        }

        if (stock == null || stock == 0) {
            System.out.println(RED + " This product is out of stock! Please choose another product." + RESET);
            return;
        }

        if (checkProduct) {
            // Hiển thị thông tin sản phẩm
            System.out.println(BLUE + " Product selected (ID = " + idProduct + ")" + RESET);
            System.out.println(YELLOW + "---------------------------------------" + RESET);
            System.out.println(BOLD + " Name: " + name + RESET);
            System.out.println(BOLD + " Brand: " + brand + RESET);
            System.out.println(BOLD + " Description: " + desc + RESET);
            System.out.println(BOLD + " Price: " + new Utils().formatPrice(price) + RESET);
            System.out.println(YELLOW + "---------------------------------------" + RESET);
            System.out.print(BOLD + GREEN + "===> Add to Cart(y/n): " + RESET);
            char question = sc.nextLine().charAt(0);
            if (question == 'y' || question == 'Y') {
                try {
                    // Kiểm tra và cập nhật giỏ hàng
                    HandleCart cartHandler = new HandleCart(); // tạo đối tượng
                    List<Cart> cartList = cartHandler.read(AllFile.fileCartTxt); // lất dữ liệu
                    Long qty = 1L; // khởi tạo cho qty = 1

                    if (cartList != null) { // không có sản phẩm nào
                        for (Cart cart : cartList) {
                            if (cart.getProductId().equals(idProduct)) { // giống sản phẩm
                                qty = cart.getQty() + 1; // thì lấy cái qty có trong db + 1
                                cartHandler.deleteIt(AllFile.fileCartTxt, Optional.of(cart.getId())); // Xóa bản
                                                                                                      // ghi cũ
                                break;
                            }
                        }
                    }

                    // Tạo bản ghi mới cho giỏ hàng
                    cartId = cartList == null || cartList.isEmpty() ? 1L // nếu là lần đầu thêm vô cart cho id = 1;
                            : cartList.get(cartList.size() - 1).getId() + 1; // lấy id của cart cuói cùng + 1
                    Cart newCart = new Cart(cartId, userId, name, price, qty, price * qty, idProduct); // lưu
                    cartHandler.addNew(AllFile.fileCartTxt, newCart);

                    // Giảm stock và cập nhật sản phẩm
                    Long currentStock = stock - 1L;
                    Product updatedProduct = new Product(code, name, brand, target, price, desc, currentStock, date); // tạo
                                                                                                                      // mới
                                                                                                                      // luôn
                                                                                                                      // cho
                                                                                                                      // chất
                    reader.deleteIt(AllFile.fileProductTxt, Optional.of(code)); // xóa cái cũ
                    if (currentStock > 0) {
                        reader.addNew(AllFile.fileProductTxt, updatedProduct); // thêm cái mới vô db
                    }

                    System.out.println(BOLD + BLUE + " Order placed successfully!" + RESET);
                } catch (Exception e) {
                    System.out
                            .println(RED + " An error occurred while processing your order: " + e.getMessage() + RESET);
                }
            }
        } else {
            System.out.println(RED + " Product not found!" + RESET);
        }
    }
}

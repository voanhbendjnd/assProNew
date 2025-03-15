package utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Cart;
import domain.entity.OrderUser;
import domain.entity.Orders;
import handle.HandleCart;
import handle.HandleOrder;
import handle.HandleOrderUser;
import setupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class OpenCart {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    private static Scanner sc = new Scanner(System.in);

    public static void inforAndHandleOrder(Long userId, Long cartIdCurrent, String name, Long price) {
        List<OrderUser> newOrder = new ArrayList<>();

        System.out.println(BOLD + GREEN + " Please enter order details:" + RESET);
        System.out.print(" Name: ");
        String nameUser = sc.nextLine();
        System.out.print(" Address: ");
        String address = sc.nextLine();
        System.out.print(" Phone Number: ");
        String phone = sc.nextLine();
        HandleOrder order = new HandleOrder();
        List<Orders> orderList = new HandleOrder().read(AllFile.fileOrderTxt);
        Long orderId = orderList == null || orderList.isEmpty() ? 1L // nếu là lần đầu thêm vô cart
                // // cho id = 1
                : orderList.get(orderList.size() - 1).getId() + 1; // lấy id của cart cuói cùng + 1

        List<OrderUser> orderUserList = new HandleOrderUser().read(AllFile.fileOrderUserTxt);
        Long orderUserId = orderUserList == null || orderUserList.isEmpty() ? 1L
                : orderUserList.get(orderUserList.size() - 1).getId() + 1;

        order.addNew(AllFile.fileOrderTxt,
                new Orders(orderId, userId, cartIdCurrent, nameUser, address, phone, price, orderUserId));
        HandleOrderUser orderUser = new HandleOrderUser();
        orderUser.addNew(AllFile.fileOrderUserTxt,
                new OrderUser(orderUserId, name, nameUser, address, phone, userId, price, 0));
        System.out.println(BOLD + YELLOW + " Order placed successfully!" + RESET);
        System.out.println(BOLD + " Your Order Details:" + RESET);
        newOrder.add(new OrderUser(
                orderUserId, name, nameUser, address, phone, userId, price, 0));

        OrderUser.printTableOrderForUser(newOrder);
    }

    @SuppressWarnings("static-access")
    public void openCart(Long userId) {

        // List<OrderUser> newOrder = new ArrayList<>();
        List<Cart> list = new HandleCart().read(AllFile.fileCartTxt);
        List<Cart> cartList = new ArrayList<>();
        for (Cart x : list) {
            if (x.getUserId().equals(userId)) {
                cartList.add(x);
            }
        }
        new Cart().printTable(cartList);
        if (!cartList.isEmpty()) {
            System.out.print(BOLD + GREEN + "Buy product or exit cart(y/n): " + RESET);
            char q = sc.nextLine().charAt(0);
            if (q == 'y' || q == 'Y') {
                try {
                    System.out.print(BOLD + BLUE + "Id of product: " + RESET);
                    Long cartIdCurrent = Long.parseLong(sc.nextLine());
                    System.out.print(BOLD + YELLOW + "Keep the current quantity?(y/n): " + RESET);
                    char qq = sc.nextLine().charAt(0);
                    String name = "";
                    Long price = null;
                    Long qty = null;
                    Long productId = null;
                    for (Cart x : cartList) {
                        if (x.getId().equals(cartIdCurrent)) {
                            name += x.getName();
                            price = x.getPrice();
                            qty = x.getQty();
                            productId = x.getProductId();
                        }
                    }

                    if (qq == 'y') {

                        System.out.println(BLUE + " Product selected (ID = " + cartIdCurrent + ")" + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.println(BOLD + " Name: " + name + RESET);
                        System.out.println(BOLD + " Price: " + new Utils().formatPrice(price) + RESET);
                        System.out.println(BOLD + " Quantity: " + qty + RESET);
                        System.out.println(BOLD + " Total: " + new Utils().formatPrice(price * qty) + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.print(BOLD + YELLOW + "Buy(y/n)?: " + RESET);
                        char qqq = sc.nextLine().charAt(0);
                        if (qqq == 'y') {
                            new HandleCart().deleteIt(AllFile.fileCartTxt, Optional.of(cartIdCurrent));
                            inforAndHandleOrder(userId, cartIdCurrent, name, price * qty);

                        }

                    } else {
                        System.out.print(BOLD + GREEN + " Please enter quantity: " + RESET);
                        Long quantity = Long.parseLong(sc.nextLine());
                        System.out.println(BLUE + " Product selected (ID = " + cartIdCurrent + ")" + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.println(BOLD + " Name: " + name + RESET);
                        System.out.println(BOLD + " Price: " + new Utils().formatPrice(price) + RESET);
                        System.out.println(BOLD + " Quantity: " + quantity + RESET);
                        System.out.println(BOLD + " Total: " + new Utils().formatPrice(price * quantity) + RESET);
                        System.out.println(YELLOW + "---------------------------------------" + RESET);
                        System.out.print(BOLD + YELLOW + "Buy(y/n)?: " + RESET);
                        char qqq = sc.nextLine().charAt(0);
                        if (qqq == 'y') {
                            // xử lý quantity trong giỏ
                            if (qty - quantity <= 0) {
                                new HandleCart().deleteIt(AllFile.fileCartTxt, Optional.of(cartIdCurrent));
                                inforAndHandleOrder(userId, cartIdCurrent, name, price * quantity);

                            } else {
                                new HandleCart().addNew(AllFile.fileCartTxt, new Cart(cartIdCurrent, userId,
                                        name, price, qty - quantity, (qty - quantity) * price, productId));
                                new HandleCart().deleteIt(AllFile.fileCartTxt, Optional.of(cartIdCurrent));
                                inforAndHandleOrder(userId, cartIdCurrent, name, price * quantity);

                            }

                        }

                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println(BOLD + BLUE + "Exiting view your cart..." + RESET);
            }

        }

    }
}

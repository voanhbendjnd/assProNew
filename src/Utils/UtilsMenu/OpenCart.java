package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Handle.HandleCart;
import Handle.HandleOrder;
import Handle.HandleOrderUser;
import Model.Cart;
import Model.OrderUser;
import Model.Orders;
import SetupFile.AllFile;

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

    public void openCart(Long id) {
        Scanner sc = new Scanner(System.in);
        List<OrderUser> newOrder = new ArrayList<>();
        List<Cart> list = new HandleCart().read(new AllFile().fileCartTxt);
        List<Cart> cartList = new ArrayList<>();
        for (Cart x : list) {
            if (x.getUserId().equals(id)) {
                cartList.add(x);
            }
        }
        new Cart().printTable(cartList);
        System.out.print(BOLD + GREEN + "Buy product or exit cart(y/n): " + RESET);
        char q = sc.nextLine().charAt(0);
        if (q == 'y' || q == 'Y') {
            try {
                System.out.print(BOLD + BLUE + "buy 1 or buy all order(1/n): " + RESET);
                char b = sc.nextLine().charAt(0);
                if (b == '1') {
                    System.out.print(BOLD + RED + "Id of product: " + RESET);
                    Long cartIdCurrent = Long.parseLong(sc.nextLine());
                    System.out.print(BOLD + YELLOW + "Keep the current quantity?(y/n): " + RESET);
                    char qq = sc.nextLine().charAt(0);
                    String name = "";
                    Long price = null;
                    Long qty = null;

                    if (qq == 'y') {
                        for (Cart x : cartList) {
                            if (x.getId().equals(cartIdCurrent)) {
                                name += x.getName();
                                price = x.getPrice();
                                qty = x.getQty();
                            }
                        }

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
                            System.out.println(BOLD + GREEN + " Please enter order details:" + RESET);
                            System.out.print(" Name: ");
                            String nameUser = sc.nextLine();
                            System.out.print(" Address: ");
                            String address = sc.nextLine();
                            System.out.print(" Phone Number: ");
                            String phone = sc.nextLine();
                            HandleOrder order = new HandleOrder();
                            List<Orders> orderList = new HandleOrder().read(new AllFile().fileOrderTxt);
                            Long orderId = orderList == null || orderList.isEmpty() ? 1L // nếu là lần đầu thêm vô cart
                                                                                         // // cho id = 1
                                    : orderList.get(orderList.size() - 1).getId() + 1; // lấy id của cart cuói cùng + 1
                            order.addOrder(new AllFile().fileOrderTxt,
                                    new Orders(orderId, id, cartIdCurrent, nameUser, address, phone));
                            List<OrderUser> orderUserList = new HandleOrderUser().read(new AllFile().fileOrderUserTxt);
                            Long orderUserId = orderUserList == null || orderUserList.isEmpty() ? 1L
                                    : orderUserList.get(orderUserList.size() - 1).getId() + 1;
                            HandleOrderUser orderUser = new HandleOrderUser();
                            orderUser.addOrder(new AllFile().fileOrderUserTxt,
                                    new OrderUser(orderUserId, name, nameUser, address, phone, id));
                            System.out.println(BOLD + YELLOW + " Order placed successfully!" + RESET);
                            System.out.println(BOLD + " Your Order Details:" + RESET);
                            newOrder.add(new OrderUser(
                                    orderUserId, name, nameUser, address, phone, id));
                            OrderUser.printTableOrderForUser(newOrder);
                        }

                    }

                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }
}

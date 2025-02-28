package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Handle.HandleCart;
import Handle.HandleOrder;
import Handle.HandleOrderUser;
import Handle.HandleProduct;
import Model.Cart;
import Model.OrderUser;
import Model.Orders;
import Model.Products;
import SetupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class BuyProduct {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";

    public void buyProduct(Long ide) {
        Long orderId = 0L;
        List<OrderUser> orderUserList = new ArrayList<>();
        HandleProduct reader = new HandleProduct();
        Scanner sc = new Scanner(System.in);

        System.out.println(CYAN + "══════════════════════════════════════" + RESET);
        System.out.print(BOLD + GREEN + "Please enter ID of the product you want to buy: " + RESET);
        Long id = sc.nextLong();
        sc.nextLine();

        boolean checkProduct = false;
        String name = "", brand = "", target = "", desc = "", date = "";
        Long price = null, code = null, stock = null;

        List<Products> proList = new HandleProduct().read(new AllFile().fileProductTxt);
        for (Products x : proList) {
            if (x.getCode().equals(id)) {
                brand = x.getBrand();
                target = x.getTarget();
                name = x.getName();
                desc = x.getDescription();
                price = x.getPrice();
                stock = x.getStock();
                date = x.getDateCreate();
                code = x.getCode();
                checkProduct = true;
                break;
            }
        }

        if (stock == 0 || stock == 0) {
            System.out.println(RED + " This product is out of stock! Please choose another product." + RESET);
            // checkProduct = false;
            return;
        }

        if (checkProduct) {
            List<Orders> orderList = new HandleOrder().read(new AllFile().fileOrderTxt);
            for (Orders x : orderList) {
                if (x.getId() >= orderId) {
                    orderId = x.getId();
                }
            }

            System.out.println(BLUE + " Product selected (ID = " + id + ")" + RESET);
            System.out.println(YELLOW + "---------------------------------------" + RESET);
            System.out.println(BOLD + " Name: " + name + RESET);
            System.out.println(BOLD + " Brand: " + brand + RESET);
            System.out.println(BOLD + " Description: " + desc + RESET);
            System.out.println(BOLD + " Price: " + new Utils().formatPrice(price) + RESET);
            System.out.println(YELLOW + "---------------------------------------" + RESET);
            // System.out.print(BOLD + GREEN + "Do you want to buy? (y/n): " + RESET);
            System.out.print(BOLD + GREEN + "===> Add to Cart(y/n): " + RESET);

            char question = sc.nextLine().charAt(0);
            if (question == 'y') {
                Long currentStock = stock - 1L;
                Products pro = new Products(code, name, brand, target, price, desc, currentStock, date);
                reader.deleteProduct(new AllFile().fileProductTxt, code);
                System.out.println("89");

                if (currentStock == 0) {
                    reader.deleteProduct(new AllFile().fileProductTxt, code);
                } else {
                    reader.addProduct(new AllFile().fileProductTxt, pro);
                }

                System.out.println(CYAN + "══════════════════════════════════════" + RESET);

                // <-
                // System.out.println(BOLD + GREEN + " Please enter order details:" + RESET);
                // System.out.print(" Name: ");
                // String nameUser = sc.nextLine();
                // System.out.print(" Address: ");
                // String address = sc.nextLine();
                // System.out.print(" Phone Number: ");
                // String phone = sc.nextLine();

                // HandleOrder order = new HandleOrder();
                // order.addOrder(new AllFile().fileOrderTxt, new Orders(orderId + 1L, ide, id,
                // nameUser, address, phone));
                // HandleOrderUser orderUser = new HandleOrderUser();
                // orderUser.addOrder(new AllFile().fileOrderUserTxt,
                // new OrderUser(orderId + 1L, name, nameUser, address, phone, ide));

                // orderUserList.add(new OrderUser(orderId + 1L, name, nameUser, address, phone,
                // ide));

                // ->
                Long qty = null;
                List<Cart> cartList = new HandleCart().read(new AllFile().fileCartTxt);
                if (cartList == null) {
                    qty = 1L;
                    System.out.println("122");

                } else {
                    for (Cart x : cartList) {
                        if (x.getProductId().equals(id)) {
                            qty = x.getQty() + 1;
                            System.out.println("128");

                            break;
                        } else {
                            qty = 1L;
                        }
                    }
                }
                System.out.println("----0-0-0-0-0-0-0-----");

                HandleCart cart = new HandleCart();
                cart.addOrder(new AllFile().fileCartTxt,
                        new Cart(orderId + 1L, ide, name, price, qty, price * qty, id));
                System.out.println(BOLD + GREEN + "\n Order placed successfully!" + RESET);
                System.out.println(BOLD + " Your Order Details:" + RESET);
                OrderUser.printTableOrderForUser(orderUserList);
            }
        } else {
            System.out.println(RED + " Product not found!" + RESET);
        }
    }
}

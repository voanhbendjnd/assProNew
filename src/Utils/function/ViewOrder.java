package utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.OrderUser;
import domain.entity.Orders;
import handle.HandleOrder;
import handle.HandleOrderUser;
import setupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class ViewOrder {
    // color
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm
    private static Scanner sc = new Scanner(System.in);

    public void viewOrderFromAdmin() {
        System.out.println(BOLD + GREEN + "---List order from user---" + RESET);
        List<Orders> orderList = new HandleOrder().read(new AllFile().fileOrderTxt);
        Orders.printTableOrderForAdmin(orderList);
        // order by user
        List<OrderUser> orderUserList = new HandleOrderUser().read(new AllFile().fileOrderUserTxt);
        while (true) {
            System.out.print(BOLD + BLUE + ">>>Order Confirmation with (y/n): " + RESET);
            String c = sc.nextLine();
            if (c.equals("y")) {
                System.out.print(BOLD + YELLOW + "Please enter the order id you want to confirm: " + RESET);
                String id = sc.nextLine();
                boolean check = false;
                Long orderId = null;
                for (Orders x : orderList) {
                    if (x.getId().equals(Long.parseLong(id))) {
                        check = true;
                        orderId = x.getOrder_id();
                        break;
                    }
                }
                if (check) {
                    new HandleOrder().deleteIt(new AllFile().fileOrderTxt, Optional.of(Long.parseLong(id)));
                    for (OrderUser x : orderUserList) {
                        if (x.getId().equals(orderId)) {
                            new HandleOrderUser().addNew(new AllFile().fileOrderUserTxt,
                                    new OrderUser(x.getId(),
                                            x.getNameProduct(),
                                            x.getName(),
                                            x.getAddress(),
                                            x.getPhone(),
                                            x.getUserId(),
                                            x.getPrice(),
                                            1));
                            new HandleOrderUser().deleteIt(new AllFile().fileOrderUserTxt, Optional.of(orderId));
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println(BOLD + RED + "Order id with " + id + " is incorrect, please re-enter" + RESET);
                }
            } else {
                break;
            }
        }
        System.out.println(BOLD + BLUE + "Exiting view order user..." + RESET);

    }

    public void viewOrderFromUser(Long id) {
        System.out.println(BOLD + GREEN + "---List order---" + RESET);
        List<OrderUser> orderList = new HandleOrderUser().read(new AllFile().fileOrderUserTxt);
        List<OrderUser> orderFind = new ArrayList<>();
        for (OrderUser x : orderList) {
            if (x.getUserId().equals(id)) {
                orderFind.add(x);
            }
        }
        OrderUser.printTableOrderForUser(orderFind);
    }
}

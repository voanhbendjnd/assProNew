package utils.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.OrderUserImpl;
import domain.entity.OrderImpl;
import handle.HandleOrder;
import handle.HandleOrderUser;
import setupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class ViewOrder {
    // hàm này để thay đổi trạng thái của đơn hàng
    public static void changeStatus(List<OrderImpl> orderList, List<OrderUserImpl> orderUserList, String id,
            int status) {
        boolean check = false;
        Long orderId = null;
        Long stId = null;
        Long stUserId = null;
        Long stProductId = null;
        String stName = "";
        String stAddress = "";
        String stPhone = "";
        Long stPrice = null;

        for (OrderImpl x : orderList) {
            if (x.getId().equals(Long.parseLong(id))) {
                check = true;
                stId = x.getId();
                stUserId = x.getUser_id();
                stProductId = x.getProduct_id();
                stName = x.getName();
                stAddress = x.getAddress();
                stPhone = x.getPhone();
                stPrice = x.getPrice();
                orderId = x.getOrder_id();
                break;
            }
        }
        if (check) {
            // xóa đơn hàng order vì đã đánh dấu
            new HandleOrder().deleteIt(AllFile.fileOrderTxt, Optional.of(Long.parseLong(id)));
            // status = 3 thì chưa tính cần xóa đơn hàng này
            if (status == 3) {
                new HandleOrder().addNew(AllFile.fileOrderTxt,
                        new OrderImpl(stId, stUserId, stProductId, stName, stAddress, stPhone, stPrice, orderId));
            }
            // thêm đơn mới lấy dữ liệu từ đơn cũ đổi status
            for (OrderUserImpl x : orderUserList) {
                if (x.getId().equals(orderId)) {
                    new HandleOrderUser().addNew(AllFile.fileOrderUserTxt,
                            new OrderUserImpl(x.getId(),
                                    x.getNameProduct(),
                                    x.getName(),
                                    x.getAddress(),
                                    x.getPhone(),
                                    x.getUserId(),
                                    x.getPrice(),
                                    status));
                    // xóa cái đơn hàng cũ
                    new HandleOrderUser().deleteIt(AllFile.fileOrderUserTxt, Optional.of(orderId));
                    break;
                }
            }
        } else {
            System.out.println(BOLD + RED + "Order id with " + id + " is incorrect, please re-enter" + RESET);
        }
    }

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
        List<OrderImpl> orderList = new HandleOrder().read(AllFile.fileOrderTxt);
        OrderImpl.printTableOrderForAdmin(orderList);
        // order by user
        List<OrderUserImpl> orderUserList = new HandleOrderUser().read(AllFile.fileOrderUserTxt);
        while (true) {
            System.out.print(BOLD + CYAN + ">>> Order Confirmation with (y/n): " + RESET);
            String c = sc.nextLine();
            if (c.equals("y")) {
                System.out.print(BOLD + YELLOW + " Please enter the order id you want to confirm: " + RESET);
                String id = sc.nextLine();
                System.out.println(GREEN + "┌───────────────────┐" + RESET);
                System.out.println(GREEN + "│ 1. Confirmed      │" + RESET);
                System.out.println(GREEN + "│ 2. Not Confirmed  │" + RESET);
                System.out.println(GREEN + "│ 3. Undetermined   │" + RESET);
                System.out.println(GREEN + "└───────────────────┘" + RESET);
                System.out.print(BOLD + BLUE + " Please enter status for this order: " + RESET);
                String c2 = sc.nextLine();
                if (c2.equals("1")) {
                    changeStatus(orderList, orderUserList, id, 1);
                } else if (c2.equals("2")) {
                    changeStatus(orderList, orderUserList, id, 2);
                } else {
                    changeStatus(orderList, orderUserList, id, 3);
                }

            } else {
                break;
            }
        }
        System.out.println(BOLD + RED + " Exiting view order user..." + RESET);

    }

    public void viewOrderFromUser(Long id) {
        System.out.println(BOLD + GREEN + "---List order---" + RESET);
        List<OrderUserImpl> orderList = new HandleOrderUser().read(AllFile.fileOrderUserTxt);
        List<OrderUserImpl> orderFind = new ArrayList<>();
        for (OrderUserImpl x : orderList) {
            if (x.getUserId().equals(id)) {
                orderFind.add(x);
            }
        }
        OrderUserImpl.printTableOrderForUser(orderFind);
    }
}

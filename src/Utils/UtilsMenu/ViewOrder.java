package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Handle.HandleOrder;
import Handle.HandleOrderUser;
import Model.OrderUser;
import Model.Orders;
import SetupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class ViewOrder {

    public void viewOrderFromAdmin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---List order from user---");
        List<Orders> orderList = new HandleOrder().read(new AllFile().fileOrderTxt);
        Orders.printTableOrderForAdmin(orderList);

        System.out.print("Order Confirmation with (y/n): ");
        

    }

    public void viewOrderFromUser(Long id) {
        System.out.println("---List order---");
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

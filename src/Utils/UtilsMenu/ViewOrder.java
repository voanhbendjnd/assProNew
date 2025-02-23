package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.List;

import Handle.HandleOrder;
import Handle.HandleOrderUser;
import Model.OrderUser;
import Model.Orders;
import SetupFile.AllFile;

public class ViewOrder {
    public void viewOrderFromAdmin() {
        System.out.println("---List order from user---");
        List<Orders> orderList = new HandleOrder().read(new AllFile().fileOrderTxt);
        Orders.printTableOrderForAdmin(orderList);
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

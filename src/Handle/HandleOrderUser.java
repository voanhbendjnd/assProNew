package Handle;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Model.OrderUser;
import SetupFile.AllFile;

public class HandleOrderUser {
    public List<OrderUser> read(String fileOrder) {
        List<OrderUser> orderList = new ArrayList<>();
        try {
            File myFile = new File(fileOrder);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] orders = data.split("\\?");
                Long id = Long.parseLong(orders[0]);
                String nameP = orders[1];
                String name = orders[2];
                String address = orders[3];
                String phone = orders[4];
                Long userId = Long.parseLong(orders[5]);
                orderList.add(new OrderUser(id, nameP, name, address, phone, userId));

            }

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return orderList;
    }

    public void writeFile(String fileName, List<OrderUser> orderList) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (OrderUser x : orderList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addOrder(String fileName, OrderUser order) {
        List<OrderUser> orderList = read(new AllFile().fileOrderUserTxt);
        orderList.add(order);
        writeFile(fileName, orderList);
    }

    public void deleteOrder(String fileName, Long id) {
        List<OrderUser> orderList = read(fileName);
        boolean productFound = false;
        for (Iterator<OrderUser> iterator = orderList.iterator(); iterator.hasNext();) {
            OrderUser order = iterator.next();
            if (order.getId().equals(id)) {
                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            writeFile(fileName, orderList);
            // System.out.println("Product deleted successfully.");
        } else {
            // System.out.println("Product not found.");
        }
    }
}

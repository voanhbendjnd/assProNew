package handle;

import java.io.File;
/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.OrderUser;
import setupFile.AllFile;

public class HandleOrderUser implements Handle<OrderUser> {
    @Override
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
                Long price = Long.parseLong(orders[6]);
                int status = Integer.parseInt(orders[7]);
                orderList.add(new OrderUser(id, nameP, name, address, phone, userId, price, status));

            }

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return orderList;
    }

    @Override
    public void writeFile(String fileName, List<OrderUser> orderList) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (OrderUser x : orderList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNew(String fileName, OrderUser order) {
        List<OrderUser> orderList = read(new AllFile().fileOrderUserTxt);
        orderList.add(order);
        writeFile(fileName, orderList);
    }

    @Override
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
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

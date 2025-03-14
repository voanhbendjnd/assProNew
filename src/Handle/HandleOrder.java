
package handle;

import domain.entity.Orders;
import setupFile.AllFile;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class HandleOrder implements Handle<Orders> {
    @Override
    public List<Orders> read(String fileOrder) {
        List<Orders> orderList = new ArrayList<>();
        try {
            File myFile = new File(fileOrder);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] orders = data.split("\\?");
                Long id = Long.parseLong(orders[0]);
                Long id_user = Long.parseLong(orders[1]);
                Long product_id = Long.parseLong(orders[2]);
                String name = orders[3];
                String address = orders[4];
                String phone = orders[5];
                Long price = Long.parseLong(orders[6]);
                Long orderId = Long.parseLong(orders[7]);
                orderList.add(new Orders(id, id_user, product_id, name, address, phone, price, orderId));

            }
            sc.close();

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return orderList;
    }

    @Override
    public void writeFile(String fileName, List<Orders> orderList) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Orders x : orderList) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNew(String fileName, Orders order) {
        List<Orders> orderList = read(AllFile.fileOrderTxt);
        orderList.add(order);
        writeFile(fileName, orderList);
    }

    @Override
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<Orders> orderList = read(fileName);
        boolean productFound = false;
        for (Iterator<Orders> iterator = orderList.iterator(); iterator.hasNext();) {
            Orders order = iterator.next();
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

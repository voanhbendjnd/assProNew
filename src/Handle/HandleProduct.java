package handle;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import domain.entity.Product;
import utils.constant.TargetEnum;

public class HandleProduct implements Handle<Product> {
    @Override
    public List<Product> read(String fileProducts) {
        List<Product> productsList = new ArrayList<>();
        try {
            File myFile = new File(fileProducts);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] products = data.split("\\?");
                Long codePhone = Long.parseLong(products[0]);
                String name = products[1];
                String brand = products[2];
                TargetEnum target = TargetEnum.valueOf(products[3]);
                Long price = Long.parseLong(products[4]);
                String description = products[5];
                Long stock = Long.parseLong(products[6]);
                String date = products[7];
                productsList.add(new Product(codePhone, name, brand, target, price, description, stock, date));

            }
            sc.close();

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return productsList;
    }

    @Override
    public void writeFile(String fileName, List<Product> product) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Product x : product) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNew(String fileName, Product product) {
        List<Product> proList = read("products.txt");
        proList.add(product);
        writeFile(fileName, proList);
    }

    @Override
    public void deleteIt(String fileName, Optional<?> codeOptional) {
        Long code = Long.parseLong(codeOptional.get().toString());
        List<Product> proList = read(fileName);
        boolean productFound = false;
        for (Iterator<Product> iterator = proList.iterator(); iterator.hasNext();) {
            Product product = iterator.next();
            if (product.getCode().equals(code)) {
                iterator.remove();
                productFound = true;
                break;
            }
        }
        if (productFound) {
            writeFile(fileName, proList);
        } else {
            System.out.println("Product not found.");
        }
    }

}

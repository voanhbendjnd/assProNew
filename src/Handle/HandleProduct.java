package Handle;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Model.Products;

public class HandleProduct {
    public List<Products> read(String fileProducts) {
        List<Products> productsList = new ArrayList<>();
        try {
            File myFile = new File(fileProducts);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] products = data.split("\\?");
                Long codePhone = Long.parseLong(products[0]);
                String name = products[1];
                String brand = products[2];
                String target = products[3];
                Long price = Long.parseLong(products[4]);
                String description = products[5];
                Long stock = Long.parseLong(products[6]);
                String date = products[7];
                productsList.add(new Products(codePhone, name, brand, target, price, description, stock, date));

            }
            sc.close();

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return productsList;
    }

    public void writeFile(String fileName, List<Products> product) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Products x : product) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addProduct(String fileName, Products product) {
        List<Products> proList = read("products.txt");
        proList.add(product);
        writeFile(fileName, proList);
    }

    public void deleteProduct(String fileName, Long code) {
        List<Products> proList = read(fileName);
        boolean productFound = false;
        for (Iterator<Products> iterator = proList.iterator(); iterator.hasNext();) {
            Products product = iterator.next();
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

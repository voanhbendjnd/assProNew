package Utils.UtilsMenu;

import java.util.List;
import java.util.Scanner;

import Handle.HandleProduct;
import Model.Products;
import SetupFile.AllFile;

/**
 *
 * @author Vo Anh Ben - CE190709
 */

public class DeleteProduct {
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        List<Products> proList = new HandleProduct().read(new AllFile().fileProductTxt);
        System.out.print("Please enter id product you want to delete: ");
        Long id = sc.nextLong();
        for (Products x : proList) {
            if (x.getCode().equals(id)) {
                new HandleProduct().deleteProduct(new AllFile().fileProductTxt, id);
                System.out.println("Delete product success!");
                break;
            }
        }
    }
}

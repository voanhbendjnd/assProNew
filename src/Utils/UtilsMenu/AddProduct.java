package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Handle.HandleProduct;
import Model.Products;
import SetupFile.AllFile;

public class AddProduct {
    // Mã ANSI để đổi màu
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void addNewProduct() {
        Scanner sc = new Scanner(System.in);
        HandleProduct reader = new HandleProduct();
        System.out.print(BOLD + CYAN + "Please enter quantity of products to add: " + RESET);
        int n = sc.nextInt();
        sc.nextLine();

        List<Products> proListed = new ArrayList<>();

        while (n-- > 0) {
            System.out.println(BOLD + BLUE + "═════════════════════════" + RESET);
            System.out.println(BOLD + BLUE + "      ADD NEW PRODUCT     " + RESET);
            System.out.println(BOLD + BLUE + "═════════════════════════" + RESET);

            System.out.print(YELLOW + "Name: " + RESET);
            String name = sc.nextLine();
            System.out.print(YELLOW + "Brand: " + RESET);
            String brand = sc.nextLine();
            System.out.print(YELLOW + "Target: " + RESET);
            String target = sc.nextLine();
            System.out.print(YELLOW + "Price: " + RESET);
            Long price = sc.nextLong();
            sc.nextLine();
            System.out.print(YELLOW + "Description: " + RESET);
            String ds = sc.nextLine();

            boolean check = false;
            Long maxCode = -1L;
            List<Products> data = reader.read(new AllFile().fileProductTxt);

            for (Products x : data) {
                if (maxCode < x.getCode()) {
                    maxCode = x.getCode();
                }
            }

            for (Products x : data) {
                if (name.equalsIgnoreCase(x.getName()) && brand.equalsIgnoreCase(x.getBrand())
                        && ds.equalsIgnoreCase(x.getDescription()) && x.getPrice().equals(price)) {
                    System.out.print(YELLOW + "One or Many stock? (1/2): " + RESET);
                    int z = sc.nextInt();
                    Long stock = null;

                    if (z == 1) {
                        System.out.println(GREEN + " Current stock: " + x.getStock() + RESET);
                        System.out.print(YELLOW + "Enter stock quantity to add: " + RESET);
                        Long stockAdd = sc.nextLong();
                        x.setStock(x.getStock() + stockAdd);
                    } else {
                        System.out.println(GREEN + " Current stock: " + x.getStock() + RESET);
                        System.out.print(YELLOW + "Enter new stock quantity: " + RESET);
                        Long stockChange = sc.nextLong();
                        x.setStock(stockChange);
                    }

                    Products pro = new Products(x.getCode(), x.getName(), x.getBrand(), x.getTarget(), x.getPrice(),
                            x.getDescription(), x.getStock(), x.getDateCreate());
                    reader.deleteProduct(new AllFile().fileProductTxt, x.getCode());
                    reader.addProduct(new AllFile().fileProductTxt, pro);
                    proListed.add(x);
                    check = true;

                    System.out.println(GREEN + " Product updated successfully!" + RESET);
                }
            }

            if (!check) {
                System.out.print(YELLOW + "Stock: " + RESET);
                Long stock = sc.nextLong();
                sc.nextLine();
                System.out.print(YELLOW + "Date Created: " + RESET);
                String date = sc.nextLine();
                Products pro = new Products(maxCode + 1, name, brand, target, price, ds, stock, date);
                reader.addProduct(new AllFile().fileProductTxt, pro);
                proListed.add(pro);

                System.out.println(GREEN + " Product added successfully!" + RESET);
            }
        }

        System.out.println(BOLD + CYAN + "══════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "     PRODUCTS ADDED/UPDATED   " + RESET);
        System.out.println(BOLD + CYAN + "══════════════════════════════" + RESET);
        Products.printTable(proListed);
    }
}

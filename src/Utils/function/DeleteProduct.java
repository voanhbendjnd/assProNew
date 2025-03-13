package utils.function;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Products;
import handle.HandleProduct;
import setupFile.AllFile;

/**
 *
 * @author Nguyễn Hữu Lập - CE190709
 */

public class DeleteProduct {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\033[1m";
    private Scanner sc = new Scanner(System.in);

    public void deleteProduct() {
        // Mã màu ANSI
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";

        List<Products> proList = new HandleProduct().read(new AllFile().fileProductTxt);

        while (true) {
            String input;
            do {
                System.out.print(BOLD + BLUE + "Please enter id product you want to delete: " + RESET);
                input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(red + " Error: Product ID cannot be empty!" + reset);
                } else if (!input.matches("\\d+")) {
                    System.out.println(red + " Error: Please enter a valid number!" + reset);
                    input = "";

                }
            } while (input.isEmpty());

            Long id = Long.parseLong(input);
            // sc.nextLine();
            boolean found = false;

            for (Products x : proList) {
                if (x.getCode().equals(id)) {
                    new HandleProduct().deleteIt(new AllFile().fileProductTxt, Optional.of(id));
                    System.out.println(blue + BOLD + " Delete product success!" + reset);
                    found = true;
                    break;
                }
            }

            if (found) {
                break; // Thoát nếu xóa thành công
            } else {
                System.out.println(red + " Product does not exist in cart!" + reset);

                // Lặp lại cho đến khi nhập đúng "Y" hoặc "N"
                String choice;
                while (true) {
                    System.out.print(BOLD + GREEN + "Would you like to try again? (Y/N): " + RESET);
                    choice = sc.nextLine().trim().toUpperCase();

                    if (choice.equals("Y") || choice.equals("N")) {
                        break; // Thoát vòng lặp nếu nhập đúng
                    } else {
                        System.out.println(BOLD + red + " Error! Please select only (Y or N)" + reset);
                    }
                }

                if (choice.equals("N")) {
                    // System.out.println(" Exiting the program!");
                    break;
                }
            }
        }
    }
}

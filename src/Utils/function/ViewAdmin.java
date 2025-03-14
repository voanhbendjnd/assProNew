package utils.function;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import domain.entity.Accounts;
import handle.HandleAccount;
import setupFile.AllFile;
import utils.Intro;

public class ViewAdmin {
    private static Scanner sc = new Scanner(System.in);
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public static void deleteAccount() {
        List<Accounts> accountList = new HandleAccount().read(new AllFile().fileAccountTxt);
        Accounts.printTable(accountList);
        while (true) {
            System.out.print(BOLD + BLUE + "Please enter id account you want to delete: " + RESET);
            String id = sc.nextLine();
            try {
                Long.parseLong(id);
            } catch (Exception ex) {
                System.out.println("Not Number!");
            }
            boolean existsById = accountList.stream()
                    .anyMatch(x -> x.getId().equals(Long.parseLong(id)));
            if (existsById) {
                new HandleAccount().deleteIt(new AllFile().fileAccountTxt, Optional.of(id));
                System.out.println(BOLD + GREEN + "Delete Succesful" + RESET);
                break;

            } else {
                System.out.print(BOLD + RED + "Id with " + id + " not exists" + RESET);

            }
        }

    }

    public void getMenuManagerAccount() {
        new FindProduct().findAll();

        while (true) {
            System.out.println(CYAN + "╔═══════════════════════════════════╗" + RESET);
            System.out
                    .println(CYAN + "║    " + BOLD + "WELCOME TO MANAGER ACCOUNT" + RESET + CYAN + "     ║" + RESET);
            System.out.println(CYAN + "╠═══════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Account By Id           ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Add Account                  ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Delete Account               ║");
            System.out.println(CYAN + "║ " + GREEN + "4." + RESET + " Fetch All Account            ║");
            System.out.println(CYAN + "║ " + GREEN + "5." + RESET + " View Order Customer          ║");
            System.out.println(CYAN + "║ " + RED + "6." + RESET + " Logout                       ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                         ║");
            System.out.println(CYAN + "╚══════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(GREEN + " Searching for products..." + RESET);
                    // new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BLUE + " Adding a new product..." + RESET);
                    // new AddProduct().addNewProduct();
                } else if (q == 3) {
                    System.out.println(RED + " Deleting an account..." + RESET);
                    ViewAdmin.deleteAccount();

                } else if (q == 4) {
                    System.out.println(CYAN + " Displaying all products..." + RESET);
                    // new FindProduct().findAll();
                } else if (q == 5) {
                    System.out.println(YELLOW + " Viewing all customer orders..." + RESET);
                    // new ViewOrder().viewOrderFromAdmin();
                } else if (q == 6) {
                    System.out.println(RED + " Logging out..." + RESET);
                    break;
                } else if (q == 0) {
                    // System.out.println(RED + " Exiting... Goodbye!" + RESET);
                    new Intro().OuttroWelCome();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option (0 - 6)!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number 123." + RESET);
                sc.nextLine();
            }
        }
    }
}

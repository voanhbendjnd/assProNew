/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIew;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Handle.HandleOrder;
import Handle.HandleProduct;
import Model.FilterProduct;
import Model.Orders;
import Model.Products;
import SetupFile.AllFile;
import Utils.Intro;
import Utils.UtilsMenu.AddProduct;
import Utils.UtilsMenu.BuyProduct;
import Utils.UtilsMenu.DeleteProduct;
import Utils.UtilsMenu.FindProduct;
import Utils.UtilsMenu.ViewOrder;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.io.PrintStream;
import static java.lang.Character.isDigit;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Menu {
    public static final String RESET = "\u001B[0m"; // Reset về mặc định
    public static final String RED = "\u001B[31m"; // Màu đỏ
    public static final String GREEN = "\u001B[32m"; // Màu xanh lá
    public static final String YELLOW = "\u001B[33m";// Màu vàng
    public static final String BLUE = "\u001B[34m"; // Màu xanh dương
    public static final String CYAN = "\u001B[36m"; // Màu xanh biển
    public static final String BOLD = "\u001B[1m"; // In đậm

    public Scanner sc = new Scanner(System.in);

    public void getMenuUser(Long id) {
        new FindProduct().findAll();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║    " + BOLD + "WELCOME TO GROUP3 MOBILE" + RESET + CYAN + "        ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products                ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Find All Products            ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Buy Product                  ║");
            System.out.println(CYAN + "║ " + GREEN + "4." + RESET + " View Order                   ║");
            System.out.println(CYAN + "║ " + GREEN + "5." + RESET + " Logout                       ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                         ║");
            System.out.println(CYAN + "╚════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(GREEN + " Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BLUE + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 3) {
                    System.out.println(CYAN + " Processing your order..." + RESET);
                    new BuyProduct().buyProduct(id);
                } else if (q == 4) {
                    System.out.println(YELLOW + " Viewing your orders..." + RESET);
                    new ViewOrder().viewOrderFromUser(id);
                } else if (q == 5) {
                    System.out.println(RED + " Logging out..." + RESET);
                    break;
                } else if (q == 0) {
                    System.out.println(RED + " Exiting... Goodbye!" + RESET);
                    new Intro().OuttroWelCome();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option (0 - 5)!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }

    }

    public void getMenu(Long id) {
        new FindProduct().findAll();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║    " + BOLD + "WELCOME TO GROUP3 MOBILE" + RESET + CYAN + "        ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products                ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Add Products                 ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Delete Products              ║");
            System.out.println(CYAN + "║ " + GREEN + "4." + RESET + " Find All Products            ║");
            System.out.println(CYAN + "║ " + GREEN + "5." + RESET + " View Order Customer          ║");
            System.out.println(CYAN + "║ " + RED + "6." + RESET + " Logout                       ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                         ║");
            System.out.println(CYAN + "╚════════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(GREEN + " Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BLUE + " Adding a new product..." + RESET);
                    new AddProduct().addNewProduct();
                } else if (q == 3) {
                    System.out.println(RED + " Deleting a product..." + RESET);
                    new DeleteProduct().deleteProduct();
                } else if (q == 4) {
                    System.out.println(CYAN + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 5) {
                    System.out.println(YELLOW + " Viewing all customer orders..." + RESET);
                    new ViewOrder().viewOrderFromAdmin();
                } else if (q == 6) {
                    System.out.println(RED + " Logging out..." + RESET);
                    break;
                } else if (q == 0) {
                    System.out.println(RED + " Exiting... Goodbye!" + RESET);
                    new Intro().OuttroWelCome();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option (0 - 6)!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }
    }

    public void getMenuNotLogin() {
        new FindProduct().findAll();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(CYAN + "╔════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║  " + BOLD + "WELCOME TO GROUP3 MOBILE" + RESET + CYAN + "      ║" + RESET);
            System.out.println(CYAN + "╠════════════════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "1." + RESET + " Find Products            ║");
            System.out.println(CYAN + "║ " + GREEN + "2." + RESET + " Find All Products        ║");
            System.out.println(CYAN + "║ " + GREEN + "3." + RESET + " Login or Sign Up         ║");
            System.out.println(CYAN + "║ " + RED + "0." + RESET + " Exit                     ║");
            System.out.println(CYAN + "╚════════════════════════════════╝" + RESET);
            System.out.print(BOLD + YELLOW + " Select option: " + RESET);

            try {
                int q = sc.nextInt();

                if (q == 1) {
                    System.out.println(GREEN + " Searching for products..." + RESET);
                    new FindProduct().findProducts();
                } else if (q == 2) {
                    System.out.println(BLUE + " Displaying all products..." + RESET);
                    new FindProduct().findAll();
                } else if (q == 3) {
                    System.out.println(CYAN + " Redirecting to login..." + RESET);
                    CreateAccount app = new CreateAccount();
                    app.mainMethod();
                } else if (q == 0) {
                    System.out.println(RED + " Exiting... Goodbye!" + RESET);
                    new Intro().OuttroWelCome();
                    break;
                } else {
                    System.out.println(RED + " Please enter a valid option!" + RESET);
                }
            } catch (Exception ex) {
                System.out.println(RED + " Invalid input! Please enter a number." + RESET);
                sc.nextLine();
            }
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectfinal;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public Scanner sc = new Scanner(System.in);

    private static String filetxt = "products.txt";

    public static void findAll() {
        ReadFile reader = new ReadFile();
        System.out.println("---------Products List---------");
        List<Products> proList = reader.read(filetxt);
        Products.printTable(proList);
    }

    public static void findProducts() {
        Scanner sc = new Scanner(System.in);
        System.out.println("                                                                Select the product you want to search");
        System.out.println("                                                                -------------------------------------");
        System.out.println("");
        System.out.println("                                                                             ---Factory---         ");
        System.out.println("                                                                1.Iphone   2.Samsung   3.Xiaomi");
        System.out.println("                                                                4.Asus   5.Oppo   6.Vivo   7.Honor");
        System.out.println("                                                                8.Nokia   9.Realme");
        System.out.println("");
        System.out.println("                                                                              ---Target---          ");
        System.out.println("                                                                10.Gaming   11.Camera, Video   12.Thin and Light");
        System.out.println("                                                                13.Battery   14.Work, Study");
        System.out.println("");
        System.out.println("                                                                              ---Price---           ");
        System.out.println("                                                                15.Under 2 milion   16.From 2 to 4 milion");
        System.out.println("                                                                17.From 4 to 7 milion   18.From 7 to 13 milion");
        System.out.println("                                                                19.From 13 to 20 milion   20. From 20 milion");
        System.out.println("");
        System.out.println("                                                                           ---Sort by Price---      ");
        System.out.println("                                                                21.Ascending order   22.Descending order");
        System.out.println("                                                                23.No sort");
        System.out.print("Enter values here: ");
        String option = sc.nextLine();
        boolean checkSort = true;
        boolean checkSortDes = false;
        boolean checkSortAs = false;
        String[] ss = option.split("\\s+");
        List<String> op = new ArrayList<>();
        for (String x : ss) {
            if(x.equalsIgnoreCase("21")){
                checkSortAs = true;
                checkSort = false;
                checkSortDes = false;
            }
            if(x.equalsIgnoreCase("22")){
                checkSortDes = true;
                checkSortAs = false;
                checkSort = false;
            }
            if(x.equalsIgnoreCase("23")){
                checkSort = true;
                checkSortDes = false;
                checkSortAs = false;
            }
            switch (x) {
                case "1":
                    op.add("iphone");
                    break;
                case "2":
                    op.add("samsung");
                    break;
                case "3":
                    op.add("xiaomi");
                    break;
                case "4":
                    op.add("asus");
                    break;
                case "5":
                    op.add("oppo");
                    break;
                case "6":
                    op.add("vivo");
                    break;
                case "7":
                    op.add("honor");
                    break;
                case "8":
                    op.add("nokia");
                    break;
                case "9":
                    op.add("realme");
                    break;
                case "10":
                    op.add("gaming");
                    break;
                case "11":
                    op.add("camera, video");
                    break;
                case "12":
                    op.add("thin, light");
                    break;
                case "13":
                    op.add("battery");
                    break;
                case "14":
                    op.add("work, study");
                    break;
                case "15":
                    op.add("under 2 milion");
                    break;
                case "16":
                    op.add("from 2 to 4 milion");
                    break;
                case "17":
                    op.add("from 4 to 7 milion");
                    break;
                case "18":
                    op.add("from 7 to 13 milion");
                    break;
                case "19":
                    op.add("from 13 to 20 milion");
                    break;
                case "20":
                    op.add("from 20 milion");
                    break;
                default:
                    System.out.println("Code no exists");
                    break;
            }
        }
        
        List<Products> proList = new ReadFile().read(filetxt);
        List<Products> proFind = new ArrayList<>();
        for(Products x : proList){
            for(String y : op){
                if(x.getBrand().toLowerCase().equalsIgnoreCase(y.trim()) || x.getTarget().equalsIgnoreCase(y.trim())){
                    proFind.add(x);
                }
           }
        }
        if(checkSortAs){
             Collections.sort(proFind, new Comparator<Products>(){
            @Override
            public int compare(Products x, Products y) {
                if(x.getPrice() > y.getPrice()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            
        });
        }
        else if(checkSortAs){
             Collections.sort(proFind, new Comparator<Products>(){
            @Override
            public int compare(Products x, Products y) {
                if(x.getPrice() < y.getPrice()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            
        });
        }
        Products.printTable(proFind);
    }

    public static void findProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter options products");
        System.out.println("(if you want to select value, you must input 'price' at the end of the sentence)");
        System.out.print("Select for YourOptions: ");
        String op = sc.nextLine();
        boolean check = false;
        Long rentPriceTo = null;
        Long rentPriceFrom = null;
        String[] ss = op.split("\\s+");
        if (op.contains("price")) {
            System.out.print("Please enter price for rentPriceFrom: ");
            rentPriceFrom = sc.nextLong();
            System.out.print("Please enter price for rentPriceTo: ");
            rentPriceTo = sc.nextLong();
            check = true;
        }

        ReadFile reader = new ReadFile();
        List<Products> data = reader.read("products.txt");
        List<Products> proList = new ArrayList<>();
        HashSet<Products> se = new HashSet<>();
        if (check) {
            for (Products x : data) {
                for (String y : ss) {
                    if ((x.getName().toLowerCase().contains(y.toLowerCase())
                            || x.getDescription().toLowerCase().contains(y.toLowerCase()))
                            && x.getPrice() >= rentPriceFrom
                            && x.getPrice() <= rentPriceTo) {
                        if (!se.contains(x)) {
                            se.add(x);
                            proList.add(x);
                        }

                    }
                }
            }
        } else {
            for (Products x : data) {
                for (String y : ss) {
                    if (x.getName().toLowerCase().contains(y.toLowerCase())
                            || x.getDescription().toLowerCase().contains(y.toLowerCase())) {
                        proList.add(x);
                    }
                }
            }
        }

        if (proList.isEmpty()) {
            System.out.println("Not find");
        } else {
            Products.printTable(proList);
        }
    }

    public static void addProducts() {
        Scanner sc = new Scanner(System.in);
        ReadFile reader = new ReadFile();
        System.out.print("Please enter qty add products: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<Products> proListed = new ArrayList<>();

        while (n-- > 0) {
            System.out.println("------Form Add-------");

            System.out.print("   Name: ");
            String name = sc.nextLine();
            System.out.print("   Brand: ");
            String brand = sc.nextLine();
            System.out.print("   Target: ");
            String target = sc.nextLine();
            System.out.print("   Price: ");
            Long price = sc.nextLong();
            sc.nextLine();
            System.out.print("   Description: ");
            String ds = sc.nextLine();
            boolean check = false;
            Long maxCode = -1L;
            List<Products> data = reader.read(filetxt);
            for (Products x : data) {
                if (maxCode < x.getCode()) {
                    maxCode = x.getCode();
                }
            }
            for (Products x : data) {
                if (name.equalsIgnoreCase(x.getName()) && brand.equalsIgnoreCase(x.getBrand()) && ds.equalsIgnoreCase(x.getDescription()) && x.getPrice().equals(price)) {
                    System.out.print("One or Many stock?(1/2): ");
                    int z = sc.nextInt();
                    Long stock = null;
                    if (z == 1) {
                        System.out.println("Current stock product: " + x.getStock());
                        System.out.print("Please enter stock you want to add: ");
                        Long stockAdd = sc.nextLong();
                        x.setStock(x.getStock() + stockAdd);
                        Products pro = new Products(x.getCode(), x.getName(), x.getBrand(), x.getTarget(), x.getPrice(), x.getDescription(), x.getStock(), x.getDateCreate());
                        reader.deleteProduct(filetxt, name, brand, x.getCode());
                        reader.addProduct(filetxt, pro);
                        proListed.add(x);
                        check = true;
//                        System.out.println("------Success-------");

                    } else {
                        System.out.println("Current stock product: " + x.getStock());
                        System.out.print("Please enter qty stock change: ");
                        Long stockChange = sc.nextLong();
                        x.setStock(stockChange);
                        Products pro = new Products(x.getCode(), x.getName(), x.getBrand(), x.getTarget(), x.getPrice(), x.getDescription(), x.getStock(), x.getDateCreate());
                        reader.deleteProduct(filetxt, name, brand, x.getCode());
                        reader.addProduct(filetxt, pro);
                        proListed.add(x);
                        check = true;
//                        System.out.println("------Success-------");
                    }
//                    System.out.print("Stock: ");

//                    x.setStock(x.getStock() + stock);
                }
            }
            if (!check) {
                System.out.print("Stock: ");
                Long stock = sc.nextLong();
                sc.nextLine();
                System.out.print("DateCreate: ");
                String date = sc.nextLine();
                Products pro = new Products(maxCode + 1, name, brand,target, price, ds, stock, date);
                reader.addProduct(filetxt, pro);
                proListed.add(pro);
                System.out.println("------Success-------");
            }
        }
        System.out.println("Products added/updated:");

        Products.printTable(proListed);
    }

    public void buyProduct(Long ide) {
        Long orderId = 0L;
        ReadFile reader = new ReadFile();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter id product your choice: ");
        Long id = sc.nextLong();
        sc.nextLine();
        boolean checkProduct = false;
        String name = "";
        String brand = "";
        String target = "";
        String desc = "";
        Long price = null;
        Long code = null;
        Long stock = null;
        String date = "";
        List<Products> proList = new ReadFile().read(filetxt);
        for (Products x : proList) {
            if (x.getCode().equals(id)) {
                brand += x.getBrand();
                target += x.getTarget();
                name += x.getName();
                desc += x.getDescription();
                price = x.getPrice();
                stock = x.getStock();
                date = x.getDateCreate();
                code = x.getCode();
                checkProduct = true;
            }
        }
        if (stock == 0) {
            System.out.println("Please enter other product");
            checkProduct = false;
        }
        if (checkProduct) {
            List<Orders> orderList = new HandleOrder().read("order.txt");
            if (orderList.isEmpty()) {
                orderId = 0L;
            } else {
                for (Orders x : orderList) {
                    if (x.getId() >= orderId) {
                        orderId = x.getId();
                    }
                }
            }

            System.out.println("Product you choice with id = " + id);
            System.out.println("   Name: " + name);
            System.out.println("   Brand: " + brand);
            System.out.println("   Desc: " + desc);
            System.out.println("   Price: " + price);
            System.out.print("You want to buy(y/n): ");
            char question = sc.nextLine().charAt(0);
            if (question == 'y') {
                Long currentStock = stock - 1L;
                Products pro = new Products(code, name, brand, target, price, desc, currentStock, date);
                reader.deleteProduct(filetxt, name, brand, code);

                if (currentStock == 0) {
                    reader.deleteProduct(filetxt, name, brand, code);
                } else {
                    reader.addProduct(filetxt, pro);
                }
                System.out.println("Please enter information order");
                System.out.print("Name: ");
                String nameUser = sc.nextLine();
                System.out.print("Address: ");
                String address = sc.nextLine();
                System.out.print("Phone Number: ");
                String phone = sc.nextLine();
                HandleOrder order = new HandleOrder();
                order.addOrder("order.txt", new Orders(orderId + 1, ide, id, nameUser, address, phone));

                System.out.println("Order successfull");
            }
        } else {
            System.out.println("Not found");
        }
    }

    public static void tableOrder() {
        List<Orders> orderList = new HandleOrder().read("order.txt");
        Orders.printTableOrder(orderList);

    }

    public void getMenuUser(Long id) {
        findAll();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("---Select---");
            System.out.println("1.Find Phone");
            System.out.println("2.Find All Products");
            System.out.println("3.Buy Product");
            System.out.println("4.Add Product to Cart");
            System.out.println("0.Exit");
            System.out.print("Select option: ");
            int q = sc.nextInt();
            try {
                if (q == 1) {
                    findProducts();
                } else if (q == 2) {
                    findAll();
                } else if (q == 3) {
                    buyProduct(id);
                } else if (q == 0) {
                    break;
                } else if (q == 4) {

                } else {
                    System.out.print("Please enter for my option: ");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }

    public void getMenu(Long id) {
        findAll();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("---Select---");
            System.out.println("1.Find Phone");
            System.out.println("2.Add Phone");
            System.out.println("3.Delete Phone");
            System.out.println("4.Find All Products");
            System.out.println("5.View Order");
            System.out.println("0.Exit");
            System.out.print("Select option: ");
            int q = sc.nextInt();
            try {
                if (q == 1) {
                    findProducts();
                } else if (q == 2) {
                    addProducts();
                } else if (q == 3) {

                } else if (q == 4) {
                    findAll();
                } else if (q == 5) {
                    tableOrder();
                } else {
                    break;
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
    }

}

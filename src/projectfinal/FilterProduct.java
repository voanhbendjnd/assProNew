package projectfinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FilterProduct {
    private static String filetxt = "products.txt";

    public void findProducts() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the product you want to search");
        System.out.println("-------------------------------------");
        // System.out.println("");
        System.out.println("            ---Factory---         ");
        System.out.println("1.Iphone   2.Samsung   3.Xiaomi");
        System.out.println("4.Asus   5.Oppo   6.Vivo   7.Honor");
        System.out.println("8.Nokia   9.Realme");
        // System.out.println("");
        System.out.println("             ---Target---          ");
        System.out.println("10.Gaming   11.Camera, Video   12.Thin and Light");
        System.out.println("13.Battery   14.Work, Study");
        // System.out.println("");
        System.out.println("              ---Price---           ");
        System.out.println("15.Under 2 milion   16.From 2 to 4 milion");
        System.out.println("17.From 4 to 7 milion   18.From 7 to 13 milion");
        System.out.println("19.From 13 to 20 milion   20. From 20 milion");
        // System.out.println("");
        System.out.println("            ---Sort by Price---      ");
        System.out.println("21.Ascending order   22.Descending order");
        System.out.println("23.No sort");
        System.out.print("Enter values here: ");
        String option = sc.nextLine();
        boolean checkSort = true;
        boolean checkSortDes = false;
        boolean checkSortAs = false;
        String[] ss = option.split("\\s+");

        List<String> op = new ArrayList<>();
        for (String x : ss) {
            switch (x) {
                case "1":
                    op.add("apple");
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
                case "21":
                    checkSortAs = true;
                    checkSort = false;
                    break;
                case "22":
                    checkSortDes = true;
                    checkSort = false;
                    break;
                case "23":
                    checkSort = true;
                    checkSortDes = false;
                    checkSortAs = false;
                    break;
                default:
                    System.out.println("Code no exists");
                    break;
            }
        }
        List<Products> proList = new ReadFile().read(filetxt);
        List<Products> proFind = new ArrayList<>();
        boolean checkTarget = false;
        boolean checkFactory = false;
        boolean checkPrice = false;
        for (String x : ss) {
            if (x.equalsIgnoreCase("1") || x.equalsIgnoreCase("2") || x.equalsIgnoreCase("3") ||
                    x.equalsIgnoreCase("4") || x.equalsIgnoreCase("5") ||
                    x.equalsIgnoreCase("6") || x.equalsIgnoreCase("7") || x.equalsIgnoreCase("8") ||
                    x.equalsIgnoreCase("9")) {
                checkFactory = true;
                break;
            }

        }

        for (String x : ss) {
            if (x.equalsIgnoreCase("10") || x.equalsIgnoreCase("11") || x.equalsIgnoreCase("12") ||
                    x.equalsIgnoreCase("13") || x.equalsIgnoreCase("14")) {
                checkTarget = true;
                break;
            }
        }
        if (checkFactory && !checkTarget) {
            for (Products x : proList) {
                for (String y : op) {
                    if (x.getBrand().equalsIgnoreCase(y)) {
                        proFind.add(x);
                    }
                }
            }
        }
        if (checkTarget && !checkFactory) {
            for (Products x : proList) {
                for (String y : op) {
                    if (x.getTarget().equalsIgnoreCase(y)) {
                        proFind.add(x);
                    }
                }
            }
        }
        List<String> targetStr = new ArrayList<>();
        List<String> factoryStr = new ArrayList<>();
        if (checkFactory && checkTarget) {
            for (Products x : proList) {
                for (String y : op) {
                    if (x.getBrand().equalsIgnoreCase(y)) {
                        factoryStr.add(y);
                    }
                }
                for (String y : op) {
                    if (x.getTarget().equalsIgnoreCase(y)) {
                        targetStr.add(y);
                    }
                }
            }
        }
        if (checkFactory && checkTarget) {
            for (Products x : proList) {
                for (String y : targetStr) {
                    if (x.getTarget().equalsIgnoreCase(y)) {
                        for (String z : factoryStr) {
                            if (x.getBrand().equalsIgnoreCase(z)) {
                                if (!proFind.contains(x)) {
                                    proFind.add(x);
                                }

                            }
                        }
                    }
                }
            }
        }

        for (Products x : proList) {
            for (String y : op) {
                if (x.getBrand().toLowerCase().equalsIgnoreCase(y.trim()) && x.getTarget().equalsIgnoreCase(y.trim())) {
                    proFind.add(x);
                }
            }
        }
        if (checkSortAs && !checkSort) {
            Collections.sort(proFind, new Comparator<Products>() {
                @Override
                public int compare(Products x, Products y) {
                    if (x.getPrice() > y.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            });
        } else if (checkSortDes && !checkSort) {
            Collections.sort(proFind, new Comparator<Products>() {
                @Override
                public int compare(Products x, Products y) {
                    if (x.getPrice() < y.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            });
        }
        Products.printTable(proFind);
    }
}

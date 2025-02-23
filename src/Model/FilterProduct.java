package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Handle.HandleProduct;
import SetupFile.AllFile;

public class FilterProduct {

    public void findProducts() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the product you want to search");
        System.out.println("-------------------------------------");
        // System.out.println("");
        System.out.println("            ---Factory---         ");
        System.out.println("1.Apple   2.Samsung   3.Xiaomi");
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
        boolean checkPrice = false;
        String[] ss = option.split("\\s+");
        Long max_value = 200000000L;
        Long min_value = 1L;
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
                    max_value = 2000000L;
                    min_value = 1L;
                    checkPrice = true;
                    break;
                case "16":
                    max_value = 4000000L;
                    min_value = 2000000L;
                    checkPrice = true;
                    break;
                case "17":
                    max_value = 7000000L;
                    min_value = 4000000L;
                    checkPrice = true;
                    break;
                case "18":
                    max_value = 7000000L;
                    min_value = 13000000L;
                    checkPrice = true;
                    break;
                case "19":
                    max_value = 20000000L;
                    min_value = 13000000L;
                    checkPrice = true;
                    break;
                case "20":
                    max_value = 200000000L;
                    min_value = 20000000L;
                    checkPrice = true;
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
                    System.out.println("Code " + x + " does not exist.");
                    break;
            }
        }

        List<Products> proList = new HandleProduct().read(new AllFile().fileProductTxt);
        if (proList == null) {
            System.out.println("Error: Product list is null.");
            return;
        }
        List<Products> proFind = new ArrayList<>();
        boolean checkFactory = op.stream().anyMatch(x -> List
                .of("apple", "samsung", "xiaomi", "asus", "oppo", "vivo", "honor", "nokia", "realme").contains(x));
        boolean checkTarget = op.stream()
                .anyMatch(x -> List.of("gaming", "camera, video", "thin, light", "battery", "work, study")
                        .contains(x));

        if (!checkPrice) {
            if (checkFactory && !checkTarget) {
                for (Products x : proList) {
                    if (op.contains(x.getBrand().toLowerCase())) {
                        proFind.add(x);
                    }
                }
            }

            if (checkTarget && !checkFactory) {
                for (Products x : proList) {
                    if (op.contains(x.getTarget().toLowerCase())) {
                        proFind.add(x);
                    }
                }
            }

            if (checkFactory && checkTarget) {
                for (Products x : proList) {
                    if (op.contains(x.getBrand().toLowerCase()) &&
                            op.contains(x.getTarget().toLowerCase())) {
                        proFind.add(x);
                    }
                }
            }

        } else {
            if (checkFactory && checkTarget) {
                for (Products x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        for (String y : op) {
                            if (x.getTarget().equalsIgnoreCase(y)) {
                                for (String z : op) {
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
            } else if (checkFactory && !checkTarget) {
                for (Products x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        if (op.contains(x.getBrand().toLowerCase())) {
                            proFind.add(x);
                        }
                    }
                }
            } else if (!checkFactory && checkTarget) {
                for (Products x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        if (op.contains(x.getTarget().toLowerCase())) {
                            proFind.add(x);
                        }
                    }
                }
            } else {
                for (Products x : proList) {
                    if (x.getPrice() >= min_value && x.getPrice() <= max_value) {
                        proFind.add(x);
                    }
                }
            }

        }
        if (checkSortAs && !checkSortDes && !checkSort) {
            Collections.sort(proFind, new Comparator<Products>() {

                @Override
                public int compare(Products o1, Products o2) {
                    if (o1.getPrice() > o2.getPrice()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            });
        }
        if (!checkSortAs && checkSortDes && !checkSort) {
            Collections.sort(proFind, new Comparator<Products>() {

                @Override
                public int compare(Products o1, Products o2) {
                    if (o1.getPrice() < o2.getPrice()) {
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

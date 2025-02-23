package Utils.UtilsMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import Handle.HandleProduct;
import Model.Products;
import SetupFile.AllFile;

public class FindProduct {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void findAll() {
        HandleProduct reader = new HandleProduct();

        // Hiển thị tiêu đề với màu sắc nổi bật
        System.out.println(BOLD + CYAN + "════════════════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "             PRODUCT LIST               " + RESET);
        System.out.println(BOLD + CYAN + "════════════════════════════════════════" + RESET);

        List<Products> proList = reader.read(new AllFile().fileProductTxt);

        // Hiển thị danh sách sản phẩm với màu xanh
        System.out.println(GREEN);
        Products.printTable(proList);
        System.out.println(RESET);
    }

    public void findProducts() {
        Scanner sc = new Scanner(System.in);
        System.out.println(BOLD + CYAN + "══════════════════════════════════════════════" + RESET);
        System.out.println(BOLD + CYAN + "         SELECT THE PRODUCT TO SEARCH         " + RESET);
        System.out.println(BOLD + CYAN + "══════════════════════════════════════════════" + RESET);

        System.out.println(BOLD + YELLOW + " Factory " + RESET);
        System.out.println(BLUE + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(BLUE + "│ 1. Apple    │ 2. Samsung   │ 3. Xiaomi     │" + RESET);
        System.out.println(BLUE + "│ 4. Asus     │ 5. Oppo      │ 6. Vivo       │" + RESET);
        System.out.println(BLUE + "│ 7. Honor    │ 8. Nokia     │ 9. Realme     │" + RESET);
        System.out.println(BLUE + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Target " + RESET);
        System.out.println(GREEN + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(GREEN + "│ 10. Gaming        │ 11. Camera, Video      │" + RESET);
        System.out.println(GREEN + "│ 12. Thin & Light  │ 13. Battery            │" + RESET);
        System.out.println(GREEN + "│ 14. Work, Study   │                        │" + RESET);
        System.out.println(GREEN + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Price Range " + RESET);
        System.out.println(RED + "┌────────────────────────────────────────────┐" + RESET);
        System.out.println(RED + "│ 15. Under 2M       │ 16. 2M - 4M           │" + RESET);
        System.out.println(RED + "│ 17. 4M - 7M        │ 18. 7M - 13M          │" + RESET);
        System.out.println(RED + "│ 19. 13M - 20M      │ 20. Over 20M          │" + RESET);
        System.out.println(RED + "└────────────────────────────────────────────┘" + RESET);

        System.out.println(BOLD + YELLOW + " Sort by Price " + RESET);
        System.out.println("┌──────────────────────────────────────────────┐");
        System.out.println("│ 21. Ascending Order   │ 22. Descending Order │");
        System.out.println("│ 23. No Sort           │                      │");
        System.out.println("└──────────────────────────────────────────────┘");

        System.out.print(BOLD + CYAN + " Enter values here: " + RESET);
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
            proFind.addAll(proList);
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
            proFind.addAll(proList);
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
        if (checkSort) {
            proFind.addAll(proList);
        }

        Products.printTable(proFind);
    }
}

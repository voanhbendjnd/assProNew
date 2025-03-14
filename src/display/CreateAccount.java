package display;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import domain.entity.Accounts;
import handle.HandleAccount;
import setupFile.AllFile;
import utils.constant.RoleEnum;

public class CreateAccount {
    // Mã ANSI để đổi màu chữ
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";

    public void mainMethod() {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Menu menu = new Menu();
        HandleAccount reader = new HandleAccount();
        List<Accounts> accountList = reader.read(new AllFile().fileAccountTxt);
        List<Accounts> auth = new ArrayList<>();
        Long code = 0L;

        while (check) {
            System.out.println(CYAN + "════════════════════════════════════════" + RESET);
            System.out.println(CYAN + "       " + BOLD + "WELCOME TO GROUP3 MOBILE" + RESET + CYAN);
            System.out.println(CYAN + "════════════════════════════════════════" + RESET);
            System.out.print(YELLOW + ">>>>> Login or Sign up (1/2): " + RESET);

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println(RED + " Invalid input! Please enter 1 or 2." + RESET);
                sc.nextLine();
                continue;
            }

            if (choice == 1) {
                // Handle login
                System.out.print(YELLOW + ">>>>> Please enter username: " + RESET);
                String user = sc.nextLine();
                System.out.print(YELLOW + ">>>>> Please enter password: " + RESET);
                String password = sc.nextLine();

                boolean checkLogin = false;
                Long userId = null;
                for (Accounts acc : accountList) {
                    if (acc.getUsername().equals(user) && acc.getPassword().equals(password)) {
                        checkLogin = true;
                        userId = acc.getId();
                        System.out.println(GREEN + " Login success!" + RESET);
                        System.out.println(BLUE + "--------------------------------" + RESET);
                        if (acc.getRole().toString().equals("ADMIN")) {
                            menu.getMenu(userId);
                        } else if (acc.getRole().toString().equals("USER")) {
                            menu.getMenuUser(userId);
                        }
                        break;
                    }
                }
                for (Accounts acc : auth) {
                    if (acc.getUsername().equals(user) && acc.getPassword().equals(password)) {
                        checkLogin = true;
                        userId = acc.getId();
                        System.out.println(GREEN + " Login success!" + RESET);
                        System.out.println(BLUE + "--------------------------------" + RESET);
                        if (acc.getRole().toString().equals("ADMIN")) {
                            menu.getMenu(acc.getId());
                        } else if (acc.getRole().toString().equals("USER")) {
                            menu.getMenuUser(acc.getId());
                        }
                        break;
                    }
                }
                for (Accounts x : auth) {
                    if (user.equals(x.getUsername()) && password.equals(x.getPassword())) {
                        checkLogin = true;
                    }
                }

                if (!checkLogin) {
                    System.out.println(RED + "------------------------------------------------" + RESET);
                    System.out.println(RED + " Username or password incorrect, please try again." + RESET);
                }

            } else if (choice == 2) {
                System.out.print(YELLOW + ">>>>> Please enter email for new account: " + RESET);
                String email = sc.nextLine();
                System.out.print(YELLOW + ">>>>> Please enter username for new account: " + RESET);
                String user = sc.nextLine();
                System.out.print(YELLOW + ">>>>> Please enter password for new account: " + RESET);
                String password = sc.nextLine();

                boolean uniqueEmail = true;
                for (Accounts acc : accountList) {
                    if (acc.getEmail().equals(email) || acc.getUsername().equals(user)) {
                        uniqueEmail = false;
                        break;
                    }
                }

                if (uniqueEmail) {
                    if (accountList.isEmpty()) {
                        code = 0L;
                    } else {
                        for (Accounts x : accountList) {
                            if (x.getId() >= code) {
                                code = x.getId();
                            }
                        }
                    }

                    HandleAccount ar = new HandleAccount();
                    RoleEnum role = RoleEnum.USER;
                    // Long role = new Utils().filterRole(user, password) ? 1L : 2L;
                    auth.add(new Accounts(code + 1, user, password, email, role));
                    ar.addNew(new AllFile().fileAccountTxt, new Accounts(code + 1, user, password, email, role));
                    // auth.add(new Accounts(code + 1, user, password, email, role));
                    System.out.println(GREEN + ">>>  Account created successfully! <<<" + RESET);
                    System.out.println(BLUE + "-----------------------------" + RESET);
                } else {
                    System.out.println(RED + " Email or Username already exists!" + RESET);
                    System.out.println(RED + "---------------------------------" + RESET);
                }

            } else {
                System.out.println(RED + " Invalid choice, please enter 1 for Login or 2 for Sign up." + RESET);
            }
        }
        sc.close();
    }
}

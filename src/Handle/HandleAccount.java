package Handle;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.Accounts;
import Model.Products;
import SetupFile.AllFile;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.stream.Collectors;
import java.io.PrintStream;
import static java.lang.Character.isDigit;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
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
public class HandleAccount {
    public List<Accounts> read(String fileProducts) {
        List<Accounts> accountList = new ArrayList<>();
        try {
            File myFile = new File(fileProducts);
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] accounts = data.split("\\?");
                Long id = Long.parseLong(accounts[0]);
                String username = accounts[1];
                String password = accounts[2];
                String email = accounts[3];
                Long role = Long.parseLong(accounts[4]);
                accountList.add(new Accounts(id, username, password, email, role));

            }
            sc.close();

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return accountList;
    }

    public void writeFile(String fileName, List<Accounts> account) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Accounts x : account) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addAccount(String fileName, Accounts account) {
        List<Accounts> accountList = read(new AllFile().fileAccountTxt);
        accountList.add(account);
        writeFile(fileName, accountList);
    }

    public void deleteAccount(String fileName, String password) {
        List<Accounts> accountList = read(fileName);
        boolean accountFound = false;
        for (Iterator<Accounts> iterator = accountList.iterator(); iterator.hasNext();) {
            Accounts account = iterator.next();
            if (account.getPassword().equals(password)) {
                iterator.remove();
                accountFound = true;
                break;
            }
        }
        if (accountFound) {
            writeFile(fileName, accountList);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }
}
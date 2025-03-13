package handle;

import domain.entity.Accounts;
import setupFile.AllFile;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class HandleAccount implements Handle<Accounts> {
    @Override
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

    @Override
    public void writeFile(String fileName, List<Accounts> account) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Accounts x : account) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNew(String fileName, Accounts account) {
        List<Accounts> accountList = read(new AllFile().fileAccountTxt);
        accountList.add(account);
        writeFile(fileName, accountList);
    }

    @Override
    public void deleteIt(String fileName, Optional<?> passwordOptional) {
        String password = passwordOptional.get().toString();
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
package handle;

import domain.entity.Account;
import setupFile.AllFile;
import utils.constant.RoleEnum;

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
public class HandleAccount implements Handle<Account> {
    @Override
    public List<Account> read(String fileProducts) {
        List<Account> accountList = new ArrayList<>();
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
                RoleEnum role = RoleEnum.valueOf(accounts[4]);
                accountList.add(new Account(id, username, password, email, role));

            }
            sc.close();

        } catch (Exception ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return accountList;
    }

    @Override
    public void writeFile(String fileName, List<Account> account) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (Account x : account) {
                fw.write(x.toStringFormatted() + "\n");
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNew(String fileName, Account account) {
        List<Account> accountList = read(AllFile.fileAccountTxt);
        accountList.add(account);
        writeFile(fileName, accountList);
    }

    @Override
    public void deleteIt(String fileName, Optional<?> idOptional) {
        Long id = Long.parseLong(idOptional.get().toString());
        List<Account> accountList = read(fileName);
        boolean accountFound = false;
        for (Iterator<Account> iterator = accountList.iterator(); iterator.hasNext();) {
            Account account = iterator.next();
            if (account.getId().equals(id)) {
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
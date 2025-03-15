package utils.error;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.entity.Account;
import handle.HandleAccount;
import setupFile.AllFile;

public class ValidationUser {
    public String validUser(String name, String email, String password) {
        List<String> sb = new ArrayList<>();
        if (name.trim().isEmpty()) {
            sb.add("Username not empty!");
        }
        if (email.trim().isEmpty() || !email.contains("@")) {
            sb.add("Email not empty and uncorrect format!");
        }
        if (password.length() < 8) {
            sb.add("Password must be more than 8 characters");
        }
        List<Account> accList = new HandleAccount().read(AllFile.fileAccountTxt);
        boolean existsAccount = accList.stream()
                .anyMatch(x -> x.getEmail().equals(email));

        if (existsAccount && sb.isEmpty()) {
            sb.add("Email already exists!");
            sb.add("Please select other email!");
        }
        String s = sb.stream()
                .collect(Collectors.joining("\n"));
        return sb.isEmpty() ? "validation" : s;

    }
}

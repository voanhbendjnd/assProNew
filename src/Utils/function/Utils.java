package utils.function;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Utils {
    // public boolean filterRole(String user, String password) {
    // String Admin = "admin@@";
    // if (password.length() >= Admin.length() && user.length() >= Admin.length()) {
    // String rulePass = password.substring(password.length() - Admin.length());
    // String ruleUser = user.substring(user.length() - Admin.length());
    // return rulePass.equals(Admin) && ruleUser.equals(Admin);
    // }
    // return false;
    // }

    public String formatPrice(Long price) {
        String s = price.toString();
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                sb.append(".");
                count = 0;
            }
        }

        return sb.reverse().toString() + " vnd";
    }
}
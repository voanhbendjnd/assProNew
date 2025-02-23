package SetupFile;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long n = sc.nextLong();
        String s = n + "";
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        if (s.length() % 3 == 0) {
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                cnt++;
                if (cnt == 3 && i != s.length() - 1) {
                    sb.append(".");
                    cnt = 0;
                }
            }
        }

        else if (s.length() % 2 == 0) {
            cnt = 1;
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                cnt++;
                if (cnt == 3 && i != s.length() - 1) {
                    sb.append(".");
                    cnt = 0;
                }
            }
        } else {
            cnt = 2;
            for (int i = 0; i < s.length(); i++) {
                sb.append(s.charAt(i));
                cnt++;
                if (cnt == 3 && i != s.length() - 1) {
                    sb.append(".");
                    cnt = 0;
                }
            }
        }

        System.out.println(sb.toString() + " vnd");
    }
}

package utils;

/**
 *
 * @author Vo Anh Ben - CE190709
 */
public class Intro {
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";

    public void IntroWelCome() throws InterruptedException {
        String welcomeText = "██       ██    ███████   ██        ██████    ██████   ███      ███  ███████\n" +
                "██       ██    ██           ██        ██           ██    ██  ████    ████  ██\n" +
                "██  █   ██    █████      ██        ██           ██    ██  ██  ████  ██  █████\n" +
                "██ ███ ██    ██           ██        ██      █   ██    ██  ██   ██     ██  ██\n" +
                " ███ ███     ███████   ██████  ██████    ██████  ██   ██     ██  ███████";
        System.out.println(CYAN); // In màu CYAN
        for (String line : welcomeText.split("\n")) {
            System.out.println(line);
            Thread.sleep(300); // Hiệu ứng xuất hiện từ từ
        }
        System.out.println(RESET); // Reset màu về mặc định
    }

    public void IntroTo() throws InterruptedException {
        String welcomeText = "██████  " + "█████\n" +
                "    █      " + "█      █\n" +
                "    █      " + "█      █\n" +
                "    █      " + "█      █\n" +
                "    █      " + "█████";
        System.out.println(CYAN); // In màu CYAN
        for (String line : welcomeText.split("\n")) {
            System.out.println(line);
            Thread.sleep(300); // Hiệu ứng xuất hiện từ từ
        }
        System.out.println(RESET); // Reset màu về mặc định
    }

    public void IntroGROUP3() throws InterruptedException {
        String welcomeText = "█████    █████  " + "█████    " + "█      █    " + "█████   " + "██████\n" +
                "█           █     █  " + "█      █   " + "█      █    " + "█      █   " + "        █\n" +
                "█     ██  █  █      " + "█      █   " + "█      █   " + "█████   " + "     █\n" +
                "█      █   █   █    " + "█      █   " + "█      █    " + "█         " + "         █\n" +
                "█████    █     █  " + "█████    " + "█████     " + "█          " + "██████";

        System.out.println(CYAN); // In màu CYAN
        for (String line : welcomeText.split("\n")) {
            System.out.println(line);
            Thread.sleep(300); // Hiệu ứng xuất hiện từ từ
        }
        System.out.println(RESET); // Reset màu về mặc định
    }

    public void OuttroWelCome() throws InterruptedException {
        String thanksText = "  ████████ ██   ██     ███      ██     ██  ██   ██  ██████\n" +
                "       ██      ██   ██     █   █     ██     ██  ██  ██   █     \n" +
                "       ██      ██████    █████    ██ ██ ██  ██ ██    █████  \n" +
                "       ██      ██   ██    ██   ██   ██     ██  ██  ██            █  \n" +
                "       ██      ██   ██    ██   ██   ██     ██  ██   ██  ██████";
        System.out.println(CYAN); // In màu CYAN
        for (String line : thanksText.split("\n")) {
            System.out.println(line);
            Thread.sleep(300); // Hiệu ứng xuất hiện từ từ
        }
        System.out.println(RESET); // Reset màu về mặc định
    }
}

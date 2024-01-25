// import java.time.LocalDateTime;

import java.util.Scanner;

import clock.Clock;

public class App {
    public static void main(String[] args) throws Exception {
        boolean[] arguments = new boolean[3];
        if (args.length > 0) {
            for (int i = 0; i < args.length && i < arguments.length; i++) {
                arguments[i] = args[i].equals("1");
            }
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Use current system time? (y/n)");
            String input = in.next().toLowerCase();
            in.nextLine();
            arguments[0] = input.matches("^y.*");

            System.out.println("Use 24 hour clock? (y/n)");
            input = in.next().toLowerCase();
            in.nextLine();
            arguments[1] = input.matches("^y.*");

            System.out.println("Show seconds? (y/n)");
            input = in.next().toLowerCase();
            in.nextLine();
            arguments[2] = input.matches("^y.*");
            in.close();
        }
        Clock cloc = new Clock(arguments[0]).start(arguments[1], arguments[2]);
        while (cloc.isRunning()) {
        }
    }
}

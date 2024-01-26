import java.util.Scanner;

import clock.Clock;

public class App {
    public static void main(String[] args) throws Exception {
        Clock cloc = null;
        if (args.length > 0) {
            boolean[] arguments = new boolean[3];
            for (int i = 0; i < args.length && i < arguments.length; i++) {
                arguments[i] = args[i].equals("1");
            }
            cloc = new Clock(arguments[0]).start(arguments[1], arguments[2]);
        } else {
            boolean currTime, _24h, showSecs;

            Scanner in = new Scanner(System.in);
            System.out.println("Use 24 hour clock? (y/n)");
            String input = in.next().toLowerCase();
            in.nextLine();
            _24h = input.matches("^y.*");

            System.out.println("Show seconds? (y/n)");
            input = in.next().toLowerCase();
            in.nextLine();
            showSecs = input.matches("^y.*");

            System.out.println("Use current system time? (y/n)");
            input = in.next().toLowerCase();
            in.nextLine();
            currTime = input.matches("^y.*");

            if (currTime) {
                cloc = new Clock(currTime).start(_24h, showSecs);
            } else {
                int hour = -1,
                minute = -1,
                second = -1;
                while(hour < 0) {
                    System.out.print("Set clock's hour to: ");
                    if (!in.hasNextInt()) {
                        in.nextLine();
                        continue;
                    }
                    hour = in.nextInt() % 24;
                }
                in.nextLine();
                if (!_24h) {
                    String res = "";
                    while (!res.equals("am") && !res.equals("pm")) {
                        System.out.print("AM or PM? ");
                        res = in.nextLine().toLowerCase();
                    }
                    hour = hour % 12;
                    if (res.equals("pm")) {
                        hour += 12;  
                    }
                }
                while (minute < 0) {
                    System.out.print("Set clock's minutes to: ");
                    if (!in.hasNextInt()) {
                        in.nextLine();
                        continue;
                    }
                    minute = in.nextInt() % 60;
                }
                while (second < 0) {
                    System.out.print("Set clock's seconds to: ");
                    if (!in.hasNextInt()) {
                        in.nextLine();
                        continue;
                    }
                    second = in.nextInt() % 60;
                }
                cloc = new Clock((hour * 3600) + (minute * 60) + second).start(_24h, showSecs);
            }
            in.close();    
        }
        while (cloc.isRunning()) {
        }
    }
}

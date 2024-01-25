// import java.time.LocalDateTime;

import clock.Clock;

public class App {
    public static void main(String[] args) throws Exception {
        boolean[] arguments = new boolean[3];
        if (args.length > 0) {
            for (int i = 0; i < args.length && i < arguments.length; i++) {
                arguments[i] = args[i].equals("1");
            }
        }
        Clock cloc = new Clock(arguments[0]).start(arguments[1], arguments[2]);
        while (cloc.isRunning()) {
        }
    }
}

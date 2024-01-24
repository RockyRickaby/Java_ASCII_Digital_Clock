// import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws Exception {
        boolean[] arguments = new boolean[3];
        if (args.length > 0) {
            for (int i = 0; i < args.length && i < arguments.length; i++) {
                arguments[i] = args[i].equals("1");
            }
        }
        // LocalDateTime d = LocalDateTime.of(2014, 1, 1, 23, 59, 55);
        // Clock cloc = new Clock((d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond(), true).start(true);
        Clock cloc = new Clock(arguments[0], arguments[1]).start(arguments[2]);
        // cloc.stop()
        //     .setTime((d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond())
        //     .start(false);

        while (cloc.isRunning()) {
            //cloc.stop();
        }
        // ToClock.getTimeAsString(d.getHour(), d.getMinute(), d.getSecond(), true);
    }
}

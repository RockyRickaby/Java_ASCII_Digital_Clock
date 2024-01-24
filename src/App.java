import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) throws Exception {
        LocalDateTime d = LocalDateTime.of(2014, 1, 1, 23, 59, 55);
        //Clock cloc = new Clock((d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond(), false).start(false);
        Clock cloc = new Clock(true, false).start(false);
        // cloc.stop()
        //     .setTime((d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond())
        //     .start(false);

        while (cloc.isRunning()) {
            //cloc.stop();
        }
        // ToClock.getTimeAsString(d.getHour(), d.getMinute(), d.getSecond(), true);
    }
}

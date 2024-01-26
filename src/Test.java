import clock.Clock;

public class Test {
    public static void main(String[] args) {
        Clock clock = new Clock(86397);
        clock.start(true, false);
        while (clock.isRunning()) {
        }
    }
}

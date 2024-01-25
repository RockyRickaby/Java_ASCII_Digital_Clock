package clock;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
/**
 * The Clock class is self-explanatory, though it should be informed that
 * this is a DIGITAL clock, not an analogue clock.
 */
public class Clock {
    private int secs;
    private int prevSecs;
    private Timer timer;
    private String time;
    private boolean running, firstPrint;

    /**
     * Creates a new Clock. The time of the clock
     * is defined by the number of seconds passed
     * since midnight. The day/time zone does not matter.
     * 
     * @param secs seconds since midnight.
     */
    public Clock(int secs) {
        if (secs < 0) {
            secs = 0;
        }
        this.secs = secs;
        this.prevSecs = secs;
        this.running = false;
        this.firstPrint = true;
        this.time = null;
        timer = new Timer(true);
    }

    /**
     * Creates a new Clock whose time is defined
     * by the current system time (if so desired).
     * 
     * @param currentTime if {@code true}, this instance of clock
     * will use the current system time.
     */
    public Clock(boolean currentTime) {
        int timeL = 0;
        if (currentTime) {
            LocalDateTime d = LocalDateTime.now();
            timeL = (d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond();
        }
        this.secs = timeL;
        this.prevSecs = secs;
        this.time = null;
        this.timer = new Timer(true);
        this.running = false;
        this.firstPrint = true;
    }

    /**
     * Changes this Clock's time.
     * 
     * @param secs the number of seconds since midnight.
     * @return this Clock.
     */
    public Clock setTime(int secs) {
        if (secs < 0) {
            secs = 0;
        }
        this.secs = secs;
        return this;
    }
    
    /**
     * Checks if this Clock is currently running.
     * @return {@code true} if this Clock is running.
     * Returns {@code false} if otherwise.
     */
    public boolean isRunning() { return running; }

    /**
     * Starts this Clock.
     * 
     * @param _24hour if {@code true}, the 24 hour will be used to
     * represent this Clock.
     * @param includeSecs if {@code true}, seconds will be shown on this Clock.
     * @return this Clock.
     */
    public Clock start(boolean _24hour, boolean includeSecs) {
        int h24 = _24hour ? 24 : 12;
        if (running) {
            return this;
        }
        if (timer == null) {
            timer = new Timer(true);
        }
        this.running = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int hour = (secs / 3600) % 24,
                    mins = (secs / 60) % 60,
                    seconds = secs % 60;
                
                if (!includeSecs) {
                    int prevSecsHour = (prevSecs / 3600) % 24;
                    int prevSecsMins = (prevSecs / 60) % 60;
                    seconds = -1;
                    if (hour == prevSecsHour && mins == prevSecsMins) {
                        if (firstPrint) {
                            firstPrint = !firstPrint;
                            time = ToClock.getTimeAsString(hour, mins, seconds, h24);
                            printClock(time);
                        }
                        secs++;
                        return;
                    }
                    prevSecs = secs;
                }
                time = ToClock.getTimeAsString(hour, mins, seconds, h24);

                printClock(time);
                if (secs >= 86400) {
                    secs = 0;
                }
                secs++;
            }
        }, 0, 1000);
        return this;
    }

    /**
     * Stops this Clock.
     * @return this Clock.
     */
    public Clock stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        this.running = false;
        return this;
    }

    /**
     * This just prints something on the screen while also
     * "clearing" the terminal. In the case of this class, it'll
     * be the Clock itself.
     * @param clock whatever you might want to print.
     */
    private static void printClock(String clock) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.printf("%s\n", clock);
    }
}
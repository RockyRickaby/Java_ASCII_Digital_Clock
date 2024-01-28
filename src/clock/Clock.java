package clock;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
/**
 * The Clock class is self-explanatory. This class represents a DIGITAL
 * ASCII clock which may or may not use the current system time as its
 * current time.
 * <p>
 * It is possible to have the Clock be either a 12 or a 24 hour clock.
 * Being a 12 or 24 hour clock is not an instance attribute, but rather something that can
 * be defined when starting the clock. In other words, there is no need to create 
 * other instances of Clock if the format is a problem, as restarting it will be
 * enough (if only one clock is ever needed). This is also valid for whether the Clock
 * has to show the seconds passed or not.
 * <p>
 * The Clock is updated with the help of the {@link java.util.Timer} class, which, in the case of the Clock
 * class, fires up a new thread whenever the Clock is started
 * (for the first time or after having stopped before starting again).
 */
public class Clock {
    private int secs;
    private int prevSecs;
    private Timer timer;
    private String clock;
    private boolean running;

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
        this.secs = secs % 86400;
        this.prevSecs = Integer.MIN_VALUE;
        this.running = false;
        this.clock = null;
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
        this.prevSecs = Integer.MIN_VALUE;
        this.running = false;
        this.clock = null;
        this.timer = new Timer(true);
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
        this.secs = secs % 86400;
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
                        secs = (secs + 1) % 86400;
                        return;
                    }
                    prevSecs = secs;
                }
                clock = ToClock.getTimeAsString(hour, mins, seconds, _24hour);

                printClock(clock);
                secs = (secs + 1) % 86400;
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
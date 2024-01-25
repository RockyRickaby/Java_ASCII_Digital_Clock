package clock;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private int secs;
    private int prevSecs;
    private Timer timer;
    private String time;
    private boolean running, firstPrint;

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

    public Clock setTime(int secs) {
        if (secs < 0) {
            secs = 0;
        }
        this.secs = secs;
        return this;
    }
    
    public boolean isRunning() { return running; }

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

    public Clock stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        this.running = false;
        return this;
    }

    private static void printClock(String clock) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.printf("%s\n", clock);
    }
}
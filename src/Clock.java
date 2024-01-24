import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private long secs;
    private long prevSecs;
    private int h24;
    private Timer timer;
    private String time;
    private boolean running, firstPrint;

    public Clock(long secs, boolean h24) {
        this.secs = secs;
        this.prevSecs = secs;
        this.running = false;
        this.firstPrint = true;
        this.time = null;
        this.h24 = h24 ? 24 : 12;
        timer = new Timer(true);
    }

    public Clock(boolean currentTime, boolean h24) {
        long timeL = 0;
        if (currentTime) {
            LocalDateTime d = LocalDateTime.now();
            timeL = (d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond();
        }
        this.secs = timeL;
        this.prevSecs = secs;
        this.time = null;
        this.timer = new Timer(true);
        this.h24 = h24 ? 24 : 12;
        this.running = false;
        this.firstPrint = true;
    }

    public Clock setTime(long secs) {
        this.prevSecs = 0;
        this.secs = secs;
        return this;
    }
    
    public boolean isRunning() { return running; }

    public Clock start(boolean includeSecs) {
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
                long hour = (secs / 3600) % 24,
                     mins = (secs / 60) % 60;
                
                if (!includeSecs) {
                    long prevSecsHour = (prevSecs / 3600) % 24;
                    long prevSecsMins = (prevSecs / 60) % 60;
                    if (hour == prevSecsHour && mins == prevSecsMins) {
                        if (firstPrint) {
                            firstPrint = !firstPrint;
                            time = ToClock.getTimeAsString(hour, mins, -1, h24);
                            System.out.print("\033[H\033[2J");  
                            System.out.flush();  
                            System.out.printf("%s\n", time);
                        }
                        secs++;
                        return;
                    }
                    prevSecs = secs;
                    time = ToClock.getTimeAsString(hour, mins, -1, h24);
                } else {
                    time = ToClock.getTimeAsString(hour, mins, secs % 60, h24);
                }

                System.out.print("\033[H\033[2J");  
                System.out.flush();  
                System.out.printf("%s\n", time);
                if (secs == 86400) {
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
}
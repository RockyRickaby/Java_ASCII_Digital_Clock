import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private long secs;
    private int h24;
    private Timer timer;
    private String time;
    private String prevTime;
    private boolean running;

    public Clock(long secs, boolean h24) {
        this.secs = secs;
        this.time = null;
        this.prevTime = null;
        timer = new Timer(true);
        this.h24 = h24 ? 24 : 12;
        this.running = false;
    }

    public Clock(boolean currentTime, boolean h24) {
        if (currentTime) {
            LocalDateTime d = LocalDateTime.now();
            long timeL = (d.getHour() * 3600) + (d.getMinute() * 60) + d.getSecond();
            this.secs = timeL;
        } else {
            this.secs = 0;
        }
        this.time = this.prevTime = null;
        this.timer = new Timer(true);
        this.h24 = h24 ? 24 : 12;
        this.running = false;
    }

    public Clock setTime(long secs) {
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
                prevTime = time;
                long hour = (secs / 3600) % 24;
                
                if (!includeSecs) {
                    
                }
                if (includeSecs) {
                    time = ToClock.getTimeAsString(hour, (secs / 60) % 60, secs % 60, h24);
                } else {
                    time = ToClock.getTimeAsString(hour, (secs / 60) % 60, h24);
                }

                if (!time.equals(prevTime)) {
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();  
                    System.out.printf("%s\n", time);
                }
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
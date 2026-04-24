package BetterMuslimTracker;

import java.time.LocalTime;

public class Prayer extends Task {
    
    public Prayer(){}
    public Prayer(String taskName, LocalTime startTime, LocalTime endTime) {
        super(taskName, startTime, endTime);
    }
    
    
}

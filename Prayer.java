package BetterMuslimTracker;

import java.time.LocalTime;

public class Prayer extends Task {
    
    public Prayer(){}
    public Prayer(String taskName, LocalTime startTime, LocalTime endTime) {
        super(taskName + " prayer", startTime, endTime);
    }
    
    @Override
    public String toString() {
        return (
            "" + getTaskName()
            + "\nPrayer time: " + getStartEndTime()
            + "\nPrayer completion: " + (getCompletionStatus() ? "Yes" : "No")
        );
    }
}

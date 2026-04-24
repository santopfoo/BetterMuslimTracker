package BetterMuslimTracker;

import java.time.LocalTime;

public class Task {
    private String taskName;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isCompleted;
    
    public Task(){}
    
    public Task(String taskName, LocalTime startTime, LocalTime endTime) {
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isCompleted = false;
    }


    public String getTaskName() {
        return taskName;
    }

    public String getStartEndTime() {
        return (startTime.toString() + " => " + endTime.toString());
    }

    public boolean getCompletionStatus() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return (
            "Task name: " + taskName
            + "\nTask time: " + startTime + " - " + endTime
            + "\nTask done: " + (isCompleted ? "Yes" : "No")
        );
    }
}

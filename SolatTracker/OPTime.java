package SolatTracker;

import java.time.LocalTime;

public class OPTime {

    private LocalTime[] time = new LocalTime[5];

    public OPTime() {
        this.time[OPEnum.SUBUH.i()] = LocalTime.parse("06:00");
        this.time[OPEnum.ZOHOR.i()] = LocalTime.parse("13:20");
        this.time[OPEnum.ASAR.i()] = LocalTime.parse("16:40");
        this.time[OPEnum.MAGHRIB.i()] = LocalTime.parse("19:30");
        this.time[OPEnum.ISYAK.i()] = LocalTime.parse("20:30");
    }

    public LocalTime getTime(int index) {
        if (index < 0 || index > 4) {
            System.out.println("Index out of bounds");
            return null;
        } else {
            return time[index];
        }
    }

    // Prayer time starts at Subuh which is 6:00
    // However LocalTime resets the clock at midnight 0:00
    // Meaning when prayers should be Subuh, Zohor, Asar, Maghrib, Isyak
    // turns into Isyak, Subuh, Zohor, Asar, Maghrib, Isyak
    // 
    // Normalising time helps in making Subuh be the first Prayer,
    // and the "next day" will only happen after 6:00, Subuh time
    public int normaliseTime(LocalTime t) {
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime subuhTime = this.getTime(OPEnum.SUBUH.i());
        final int CYCLE_START_MINUTES = subuhTime.getHour() * 60 + subuhTime.getMinute();
    

        int minutes = t.getHour() * 60 + t.getMinute();
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }

    public int normaliseTimeIndex(int i) {
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime subuhTime = this.getTime(OPEnum.SUBUH.i());
        final int CYCLE_START_MINUTES = subuhTime.getHour() * 60 + subuhTime.getMinute();
    
        int minutes = this.getTime(i).getHour() * 60 + this.getTime(i).getMinute();
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }

    public int normaliseEndTimeIndex(int i) {
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime subuhTime = this.getTime(OPEnum.SUBUH.i());
        final int CYCLE_START_MINUTES = subuhTime.getHour() * 60 + subuhTime.getMinute();
    
        i = (i + 1) % time.length;

        int minutes = this.getTime(i).getHour() * 60 + this.getTime(i).getMinute() - 1;
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }

    public int size() {return time.length;}
    public String toString(int index) {return time[index].toString();}
}

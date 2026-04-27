package BetterMuslimTracker;

import java.time.LocalTime;

public class CompulsoryPrayersTime {

    private LocalTime[] prayersTime = new LocalTime[5];

    public CompulsoryPrayersTime() {
        this.prayersTime[Enum.SUBUH.getInt()] = LocalTime.parse("06:00");
        this.prayersTime[Enum.ZOHOR.getInt()] = LocalTime.parse("13:20");
        this.prayersTime[Enum.ASAR.getInt()] = LocalTime.parse("16:40");
        this.prayersTime[Enum.MAGHRIB.getInt()] = LocalTime.parse("19:30");
        this.prayersTime[Enum.ISYAK.getInt()] = LocalTime.parse("20:30");
    }

    public LocalTime getTime(int index) {
        if (index < 0 || index > 4) {
            System.out.println("Index out of bounds");
            return null;
        } else {
            return prayersTime[index];
        }
    }

    public int normaliseTime(LocalTime t) {
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime temp = this.getTime(CompulsoryPrayersTime.Enum.SUBUH.getInt());
        final int CYCLE_START_MINUTES = temp.getHour() * 60 + temp.getMinute();

        int minutes = t.getHour() * 60 + t.getMinute();
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }
    public int normaliseTimeIndex(int i) {
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime temp = this.getTime(CompulsoryPrayersTime.Enum.SUBUH.getInt());
        final int CYCLE_START_MINUTES = temp.getHour() * 60 + temp.getMinute();

        int minutes = this.getTime(i).getHour() * 60 + this.getTime(i).getMinute();
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }
    public int normaliseEndTimeIndex(int i) {
        i = (i + 1) % prayersTime.length;
        final int MINUTES_IN_A_DAY = 24 * 60;
        final LocalTime temp = this.getTime(CompulsoryPrayersTime.Enum.SUBUH.getInt());
        final int CYCLE_START_MINUTES = temp.getHour() * 60 + temp.getMinute();

        int minutes = this.getTime(i).getHour() * 60 + this.getTime(i).getMinute() - 1;
        if (minutes < CYCLE_START_MINUTES) {
            minutes += MINUTES_IN_A_DAY;
        }
        return minutes;
    }

    public int size() {
        return prayersTime.length;
    }

    public String toString(int index) {
        return prayersTime[index].toString();
    }

    public enum Enum {
        SUBUH,
        ZOHOR,
        ASAR,
        MAGHRIB,
        ISYAK;

        public Enum next() {
            Enum[] values = Enum.values();
            return values[(this.getInt() + 1) % values.length];
        }

        public int getInt() {
            return this.ordinal();
        }
    }
}

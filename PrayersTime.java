package BetterMuslimTracker;

import java.time.LocalTime;

public class PrayersTime {

    private LocalTime[] prayerTime = new LocalTime[5];
    
    public PrayersTime() {
        this.prayerTime[CompulsoryPrayersTimeEnum.SUBUH.ordinal()] = LocalTime.parse("06:00");
        this.prayerTime[CompulsoryPrayersTimeEnum.ZOHOR.ordinal()] = LocalTime.parse("13:20");
        this.prayerTime[CompulsoryPrayersTimeEnum.ASAR.ordinal()] = LocalTime.parse("16:40");
        this.prayerTime[CompulsoryPrayersTimeEnum.MAGHRIB.ordinal()] = LocalTime.parse("19:30");
        this.prayerTime[CompulsoryPrayersTimeEnum.ISYAK.ordinal()] = LocalTime.parse("20:30");
    }

    public LocalTime getPrayerTime(int index) {
        if (index < 0 || index > 4) {
            System.out.println("Index out of bounds");
            return LocalTime.parse("00:00");
        } else {
            return prayerTime[index];
        }
    }

    public int length() {
        return prayerTime.length;
    }

    public String getStringOfIndex(int index) {
        return prayerTime[index].toString();
    }
}

package BetterMuslimTracker;

import java.time.LocalTime;

public class CompulsoryPrayers {
    private Prayer[] compulsoryPrayers = new Prayer[5];
    private PrayerTime prayerTime = new PrayerTime();

    public CompulsoryPrayers() {
        for (CompulsoryPrayerTimeEnum e : CompulsoryPrayerTimeEnum.values()) {
            compulsoryPrayers[e.ordinal()] = new Prayer(
                e.toString(), 
                prayerTime.getPrayerTime(e.ordinal()),
                prayerTime.getPrayerTime(e.next().ordinal()).minusMinutes(1)
            );
        }

    }

    public Prayer getFromIndex(int index) {
        return compulsoryPrayers[index];
    }
}

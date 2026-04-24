package BetterMuslimTracker;

public enum CompulsoryPrayerTimeEnum {
    SUBUH,
    ZOHOR,
    ASAR,
    MAGHRIB,
    ISYAK;

    public CompulsoryPrayerTimeEnum next() {
        CompulsoryPrayerTimeEnum[] values = CompulsoryPrayerTimeEnum.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
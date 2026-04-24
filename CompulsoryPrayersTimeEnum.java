package BetterMuslimTracker;

public enum CompulsoryPrayersTimeEnum {
    SUBUH,
    ZOHOR,
    ASAR,
    MAGHRIB,
    ISYAK;

    public CompulsoryPrayersTimeEnum next() {
        CompulsoryPrayersTimeEnum[] values = CompulsoryPrayersTimeEnum.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
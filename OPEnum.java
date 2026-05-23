package SolatTracker;

public enum OPEnum {
    SUBUH,
    ZOHOR,
    ASAR,
    MAGHRIB,
    ISYAK;

    public OPEnum next() {
        OPEnum[] values = OPEnum.values();
        return values[(this.ordinal() + 1) % values.length];
    }

    public int i() {
        return this.ordinal();
    }
}
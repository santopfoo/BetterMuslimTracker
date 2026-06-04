package SolatTracker;

public enum OPEnum {
    SUBUH,
    ZOHOR,
    ASAR,
    MAGHRIB,
    ISYAK;
        
    private static OPEnum[] values = OPEnum.values();

    /**
     * Get next enum & if called on ISYAK will roolover to SUBUH
     * 
     * @return OPEnum
     */
    public OPEnum next() {
        return values[(this.i() + 1) % values.length];
    }

    public static OPEnum get(int i) {
        if (i < 0 || i > values.length) {
            throw new IndexOutOfBoundsException("OPEnum.get call out of bounds: " + i);
        } else return values[i];
    }

    /**
     * Shortform of ordinal
     * 
     * @return int
     */
    public int i() {
        return this.ordinal();
    }
}
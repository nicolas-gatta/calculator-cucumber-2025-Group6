package unit_converter.enums;

import unit_converter.IBaseUnit;

/**
 * Enum representing different energy units with their conversion factors to joules.
 */
public enum EnergyUnitEnum {
    /**
     * Electron volt (eV) with conversion factor to joules.
     */
    ELECTRON_VOLT(1.60218E-19),
    
    /**
     * Joule (J) - base unit with conversion factor 1.0.
     */
    JOULE(1.0),
    
    /**
     * Kilojoule (kJ) with conversion factor to joules.
     */
    KILOJOULE(1000.0),
    
    /**
     * Megajoule (MJ) with conversion factor to joules.
     */
    MEGAJOULE(1000000.0),
    
    /**
     * Calorie (cal) with conversion factor to joules.
     */
    CALORIE(4.184),
    
    /**
     * Kilocalorie (kcal) with conversion factor to joules.
     */
    KILOCALORIE(4184.0),
    
    /**
     * Watt-hour (Wh) with conversion factor to joules.
     */
    WATT_HOUR(3600.0),
    
    /**
     * Kilowatt-hour (kWh) with conversion factor to joules.
     */
    KILOWATT_HOUR(3600000.0),
    
    /**
     * British Thermal Unit (BTU) with conversion factor to joules.
     */
    BTU(1055.06),
    
    /**
     * Erg with conversion factor to joules.
     */
    ERG(1e-7);

    private final double toJoule;

    EnergyUnitEnum(double toJoule) {
        this.toJoule = toJoule;
    }

    /**
     * Returns the conversion factor from this energy unit to joules.
     * 
     * @return the conversion factor to joules
     */
    public double toJoule() {
        return toJoule;
    }

    /**
     * Converts a string representation to the corresponding EnergyUnitEnum value.
     * 
     * @param str the string to convert
     * @return the matching EnergyUnitEnum
     */
    public static EnergyUnitEnum fromString(String str) {
        return EnergyUnitEnum.valueOf(str.trim().toUpperCase().replace(" ", "_"));
    }
}

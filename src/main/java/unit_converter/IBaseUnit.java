package unit_converter;

/**
 * Interface representing a unit that can be converted to a base unit.
 * This interface defines the contract for any unit that can be converted to its base unit
 * in a unit conversion system.
 */
public interface IBaseUnit {
    /**
     * Converts the current unit to its base unit.
     * 
     * @return the conversion factor to the base unit
     */
    double toBase();
}

package calculator.api;

public class ProgrammerConversionRequest {
    private String fromUnit;
    private String toUnit;
    private String value;

    public ProgrammerConversionRequest() {
        // Empty constructor
    }

    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }

    public String getToUnit() { return toUnit; }
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}

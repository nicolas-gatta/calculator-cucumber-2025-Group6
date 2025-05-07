package calculator.api;

public class ConversionRequest {
    private String conversionType;
    private String fromUnit;
    private String toUnit;
    private String value;

    public ConversionRequest() {
        //Empty constructor
    }

    public String getConversionType() { return conversionType; }
    public void setConversionType(String conversionType) { this.conversionType = conversionType; }
    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String fromUnit) { this.fromUnit = fromUnit; }
    public String getToUnit() { return toUnit; }
    public void setToUnit(String toUnit) { this.toUnit = toUnit; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}

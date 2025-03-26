package calculator.gui;

// Enum pour les types de nombres supportés
public enum NumberType {
    INTEGER("Integer"),
    RATIONAL("Rational"),
    REAL("Real"),
    COMPLEX("Complex");
    
    private final String displayName;
    
    NumberType(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
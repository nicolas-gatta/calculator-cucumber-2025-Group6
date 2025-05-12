import "./Converter.css";
import Dropdown from "./Dropdown";
import {useState} from "react";
import ConverterBase from "./ConverterBase";
import PropTypes from "prop-types";


const Converter = ({ setConverterResult }) => {
    const [selectedConversionType, setSelectedConversionType] = useState("LENGTH");

    const conversionTypes = [
        {value: "LENGTH", label: "Length"},
        {value: "VOLUME", label: "Volume"},
        {value: "TEMPERATURE", label: "Temperature"},
        {value: "AREA", label: "Area"},
        {value: "DENSITY", label: "Density"},
        {value: "CURRENCY", label: "Currency"},
        {value: "PRESSURE", label: "Pressure"},
        {value: "SPEED", label: "Speed"},
        {value: "ENERGY", label: "Energy"},
        {value: "FORCE", label: "Force"},
        {value: "Time", label: "Time"},
    ];

    const conversionUnits = {
        LENGTH: [
            { value: "MILLIMETER", label: "Millimeter" },
            { value: "CENTIMETER", label: "Centimeter" },
            { value: "DECIMETER", label: "Decimeter" },
            { value: "METER", label: "Meter" },
            { value: "KILOMETER", label: "Kilometer" },
            { value: "INCH", label: "Inch" },
            { value: "FOOT", label: "Foot" },
            { value: "YARD", label: "Yard" },
            { value: "MILE", label: "Mile" },
            { value: "NAUTICAL_MILE", label: "Nautical Mile" },
            { value: "LIGHT_YEAR", label: "Light Year" },
            { value: "ANGSTROM", label: "Angstrom" },
        ],
        VOLUME: [
            { value: "MILLILITER", label: "Milliliter" },
            { value: "CENTILITER", label: "Centiliter" },
            { value: "DECILITER", label: "Deciliter" },
            { value: "LITER", label: "Liter" },
            { value: "CUBIC_METER", label: "Cubic Meter" },
            { value: "TEASPOON", label: "Teaspoon" },
            { value: "TABLESPOON", label: "Tablespoon" },
            { value: "FLUID_OUNCE", label: "Fluid Ounce" },
            { value: "CUP", label: "Cup" },
            { value: "PINT", label: "Pint" },
            { value: "QUART", label: "Quart" },
            { value: "GALLON", label: "Gallon" },
            { value: "CUBIC_INCH", label: "Cubic Inch" },
            { value: "CUBIC_FOOT", label: "Cubic Foot" },
        ],
        TEMPERATURE: [
            { value: "CELSIUS", label: "Celsius" },
            { value: "FAHRENHEIT", label: "Fahrenheit" },
            { value: "KELVIN", label: "Kelvin" },
        ],
        AREA: [
            { value: "SQUARE_MILLIMETER", label: "Square Millimeter" },
            { value: "SQUARE_METER", label: "Square Meter" },
            { value: "SQUARE_CENTIMETER", label: "Square Centimeter" },
            { value: "SQUARE_KILOMETER", label: "Square Kilometer" },
            { value: "SQUARE_INCH", label: "Square Inch" },
            { value: "SQUARE_FOOT", label: "Square Foot" },
            { value: "SQUARE_YARD", label: "Square Yard" },
            { value: "SQUARE_MILE", label: "Square Mile" },
            { value: "ACRE", label: "Acre" },
            { value: "HECTARE", label: "Hectare" },
        ],
        DENSITY: [
            { value: "GRAM_PER_CUBIC_CENTIMETER", label: "g/cm³" },
            { value: "KILOGRAM_PER_CUBIC_CENTIMETER", label: "kg/cm³" },
            { value: "GRAM_PER_CUBIC_METER", label: "g/m³" },
            { value: "KILOGRAM_PER_CUBIC_METER", label: "kg/m³" },
            { value: "GRAM_PER_MILLIMETER", label: "g/mm" },
            { value: "GRAM_PER_LITER", label: "g/L" },
            { value: "POUND_PER_CUBIC_FOOT", label: "lb/ft³" },
            { value: "POUND_PER_CUBIC_INCH", label: "lb/in³" },
            { value: "OUNCE_PER_GALLON_US", label: "oz/gal (US)" },
        ],
        CURRENCY: [
            { value: "EUR", label: "Euro" },
            { value: "USD", label: "US Dollar" },
            { value: "GBP", label: "British Pound" },
            { value: "JPY", label: "Japanese Yen" },
            { value: "CHF", label: "Swiss Franc" },
            { value: "CAD", label: "Canadian Dollar" },
            { value: "AUD", label: "Australian Dollar" },
        ],
        PRESSURE: [
            { value: "PASCAL", label: "Pascal" },
            { value: "HECTOPASCAL", label: "Hectopascal" },
            { value: "KILOPASCAL", label: "Kilopascal" },
            { value: "BAR", label: "Bar" },
            { value: "ATMOSPHERE", label: "Atmosphere" },
            { value: "TORR", label: "Torr" },
            { value: "PSI", label: "PSI" },
        ],
        SPEED: [
            { value: "CENTIMETER_PER_MINUTE", label: "cm/min" },
            { value: "CENTIMETER_PER_SECOND", label: "cm/s" },
            { value: "FOOT_PER_HOUR", label: "ft/h" },
            { value: "FOOT_PER_MINUTE", label: "ft/min" },
            { value: "FOOT_PER_SECOND", label: "ft/s" },
            { value: "INCH_PER_MINUTE", label: "in/min" },
            { value: "INCH_PER_SECOND", label: "in/s" },
            { value: "KILOMETER_PER_HOUR", label: "km/h" },
            { value: "KILOMETER_PER_SECOND", label: "km/s" },
            { value: "METER_PER_HOUR", label: "m/h" },
            { value: "METER_PER_MINUTE", label: "m/min" },
            { value: "METER_PER_SECOND", label: "m/s" },
        ],
        ENERGY: [
            { value: "ELECTRON_VOLT", label: "eV" },
            { value: "JOULE", label: "Joule" },
            { value: "KILOJOULE", label: "kJ" },
            { value: "MEGAJOULE", label: "MJ" },
            { value: "CALORIE", label: "Calorie" },
            { value: "KILOCALORIE", label: "kcal" },
            { value: "WATT_HOUR", label: "Wh" },
            { value: "KILOWATT_HOUR", label: "kWh" },
            { value: "BTU", label: "BTU" },
            { value: "ERG", label: "Erg" },
        ],
        FORCE: [
            { value: "DYNE", label: "Dyne" },
            { value: "KILO_FORCE", label: "Kiloforce" },
            { value: "KILOPOND", label: "Kilopond" },
            { value: "KIP", label: "Kip" },
            { value: "NEWTON", label: "Newton" },
            { value: "KILONEWTON", label: "Kilonewton" },
            { value: "OUNCE_FORCE", label: "ozf" },
            { value: "POUNDAL", label: "Poundal" },
            { value: "POUND_FORCE", label: "lbf" },
            { value: "TON_FORCE", label: "Ton-force" },
        ],
        TIME: [
            { value: "NANOSECOND", label: "Nanosecond" },
            { value: "MILLISECOND", label: "Millisecond" },
            { value: "SECOND", label: "Second" },
            { value: "MINUTE", label: "Minute" },
            { value: "HOUR", label: "Hour" },
            { value: "DAY", label: "Day" },
            { value: "WEEK", label: "Week" },
            { value: "MONTH", label: "Month" },
            { value: "YEAR", label: "Year" },
        ],
    };

    const unitsForCurrentType = conversionUnits[selectedConversionType] || [];

    return (
        <div className="converter">
            <Dropdown
                title={"Conversion Type"}
                options={conversionTypes}
                selectedOption={selectedConversionType}
                onChange={(value) => setSelectedConversionType(value)}
            />

            <ConverterBase units={unitsForCurrentType}
                           conversionType={selectedConversionType}
                           apiPath="/api/conversion/convert"
                           setConverterBaseResult={setConverterResult}
            />
        </div>
    );
};

Converter.propTypes = {
    setConverterResult: PropTypes.func.isRequired
}


export default Converter;
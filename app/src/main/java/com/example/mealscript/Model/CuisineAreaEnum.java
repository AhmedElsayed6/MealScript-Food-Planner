package com.example.mealscript.Model;

public enum CuisineAreaEnum {
    AMERICAN("US"),
    BRITISH("GB"),
    CANADIAN("CA"),
    CHINESE("CN"),
    CROATIAN("HR"),
    DUTCH("NL"),
    EGYPTIAN("EG"),
    FILIPINO("PH"),
    FRENCH("FR"),
    GREEK("GR"),
    INDIAN("IN"),
    IRISH("IE"),
    ITALIAN("IT"),
    JAMAICAN("JM"),
    JAPANESE("JP"),
    KENYAN("KE"),
    MALAYSIAN("MY"),
    MEXICAN("MX"),
    MOROCCAN("MA"),
    POLISH("PL"),
    PORTUGUESE("PT"),
    RUSSIAN("RU"),
    SPANISH("ES"),
    THAI("TH"),
    TUNISIAN("TN"),
    TURKISH("TR"),
    UKRAINIAN("UA"),
    UNKNOWN(""),
    VIETNAMESE("VN");

    private final String countryCode;

    CuisineAreaEnum(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public static String getCountryCodeByArea(String area) {
        try {
            return CuisineAreaEnum.valueOf(area.toUpperCase()).getCountryCode();
        } catch (IllegalArgumentException e) {
            return "Unknown Area";
        }
    }
}

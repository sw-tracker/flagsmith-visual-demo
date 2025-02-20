package hellofx;

public interface FeatureFlagsProxy {
    void update(String country, String money);
    void getUserFlagsAndTraits();
    String getEmail();
    String getColour();
    String getCountry();
    boolean getUpdateButtonEnabled();
    boolean getMoneySpentEnabled();
    boolean getGeolocationEnabled();
}

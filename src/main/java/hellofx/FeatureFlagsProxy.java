package hellofx;

public abstract class FeatureFlagsProxy {
  protected String EMAIL;
  protected String COUNTRY;
  protected String lastColor = "blue";

  public FeatureFlagsProxy(int id) {

    this.EMAIL = InitialData.getEmail(id);
    try {
      this.COUNTRY = InitialData.getCountry(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  abstract public void update(String country, String money);

  abstract public void getUserFlagsAndTraits();

  abstract public String getColour();

  abstract public boolean getUpdateButtonEnabled();

  abstract public boolean getMoneySpentEnabled();

  abstract public boolean getGeolocationEnabled();

  public String getEmail() {
    return EMAIL;
  }

  public String getCountry() {
    return COUNTRY;
  }
}

package hellofx;

public enum FlagsEnum {
  // Background colour such as: darkblue, red, black, yellow
  BACKGROUND_COLOUR("background_colour"),
  // Enables button functionality
  UPDATE_BUTTON("update_button"),
  // Display money input
  MONEY_SPENT("money_spent"),
  // Display country input
  GEOLOCATION("geolocation")
  ;
  private String key;

  FlagsEnum(String key) {
    this.key = key;
  }

  public String getValue() {
    return key;
  }
}

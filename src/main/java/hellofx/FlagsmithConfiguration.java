package hellofx;

import com.flagsmith.FeatureUser;
import com.flagsmith.FlagsAndTraits;
import com.flagsmith.FlagsmithClient;
import com.flagsmith.Trait;
import org.apache.commons.lang3.StringUtils;

public class FlagsmithConfiguration {

  private final FlagsmithClient flagsmithClient;
  private final FeatureUser user;
  private final String EMAIL = "a@a.com";
  private final String COUNTRY = "DE";
  private final String DEFAULT_BACKGROUND_COLOUR = "red";
  private FlagsAndTraits userFlagsAndTraits;

  public FlagsmithConfiguration() {
    this.flagsmithClient = FlagsmithClient
        .newBuilder()
        .setApiKey("7QqkWrLp5sK9S7kHRrA4RH")
        .withApiUrl("http://localhost:8000/api/v1/")
        .enableLogging()
        .build();

    this.user = new FeatureUser();
    user.setIdentifier(EMAIL);

    this.updateUserTrait("email", EMAIL);
    this.updateUserTrait("country", COUNTRY);

    userFlagsAndTraits = this.flagsmithClient.getUserFlagsAndTraits(user);
    System.out.println(EMAIL + " " + userFlagsAndTraits);
  }

  public String getEmail() {
    return EMAIL;
  }

  public String getColour() {
    final String backgroundColour = this.flagsmithClient.getFeatureFlagValue("background_colour", this.userFlagsAndTraits);
    if (StringUtils.isNotBlank(backgroundColour)) {
      return backgroundColour;
    }
    return DEFAULT_BACKGROUND_COLOUR;
  }

  public String getCountry() {
    return COUNTRY;
  }

  private void updateUserTrait(String key, String value) {
    final Trait trait = new Trait();
    trait.setIdentity(this.user);
    trait.setKey(key);
    trait.setValue(value);
    this.flagsmithClient.updateTrait(this.user, trait);
  }
}

package hellofx;

import com.flagsmith.FeatureUser;
import com.flagsmith.FlagsAndTraits;
import com.flagsmith.FlagsmithClient;
import com.flagsmith.FlagsmithConfig;
import com.flagsmith.FlagsmithLoggerLevel;
import com.flagsmith.Trait;
import org.apache.commons.lang3.StringUtils;

import static hellofx.FlagsEnum.BACKGROUND_COLOUR;
import static hellofx.FlagsEnum.GEOLOCATION;
import static hellofx.FlagsEnum.MONEY_SPENT;
import static hellofx.FlagsEnum.UPDATE_BUTTON;

public class FlagsmithConfiguration {

  private final String API_URL = "http://localhost:8000/api/v1/";
  private final String ENV_KEY = "YkrU87KuysrZ8UPWSKns8f";
  private final FlagsmithClient flagsmithClient;
  private final FeatureUser user;
  private final String EMAIL;
  private final String COUNTRY;
  private FlagsAndTraits userFlagsAndTraits;
  private String lastColor = "blue";

  public FlagsmithConfiguration(int id) throws Exception {
    this.EMAIL = InitialData.getEmail(id);
    this.COUNTRY = InitialData.getCountry(id);

    this.flagsmithClient = FlagsmithClient
        .newBuilder()
        .setApiKey(ENV_KEY)
        .withApiUrl(API_URL)
        .enableLogging(FlagsmithLoggerLevel.ERROR)
        .build();

    this.user = new FeatureUser();
    user.setIdentifier(EMAIL);

    this.updateUserTrait("email", EMAIL);
    this.updateUserTrait("country", COUNTRY);

    this.getUserFlagsAndTraits();
  }

  public void getUserFlagsAndTraits() {
    userFlagsAndTraits = this.flagsmithClient.getUserFlagsAndTraits(user);
  }

  public String getEmail() {
    return EMAIL;
  }

  public String getColour() {
    final String backgroundColour = this.flagsmithClient.getFeatureFlagValue(BACKGROUND_COLOUR.getValue(), this.userFlagsAndTraits);
    if (StringUtils.isNotBlank(backgroundColour)) {
      lastColor = backgroundColour;
    }
    return lastColor;
  }

  public String getCountry() {
    return COUNTRY;
  }

  public void update(String country, String money) {
    this.updateUserTrait("country", country);
    this.updateUserTrait("money", money);
  }

  private void updateUserTrait(String key, String value) {
    final Trait trait = new Trait();
    trait.setIdentity(this.user);
    trait.setKey(key);
    trait.setValue(value);
    this.flagsmithClient.updateTrait(this.user, trait);
  }

  public boolean getUpdateButtonEnabled() {
    return this.flagsmithClient.hasFeatureFlag(UPDATE_BUTTON.getValue(), this.userFlagsAndTraits);
  }

  public boolean getMoneySpentEnabled() {
    return this.flagsmithClient.hasFeatureFlag(MONEY_SPENT.getValue(), this.userFlagsAndTraits);
  }

  public boolean getGeolocationEnabled() {
    return this.flagsmithClient.hasFeatureFlag(GEOLOCATION.getValue(), this.userFlagsAndTraits);
  }
}

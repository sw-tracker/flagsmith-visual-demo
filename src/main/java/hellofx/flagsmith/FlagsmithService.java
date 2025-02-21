package hellofx.flagsmith;

import com.flagsmith.FeatureUser;
import com.flagsmith.FlagsAndTraits;
import com.flagsmith.FlagsmithClient;
import com.flagsmith.FlagsmithLoggerLevel;
import com.flagsmith.Trait;
import hellofx.FeatureFlagsProxy;
import org.apache.commons.lang3.StringUtils;

import static hellofx.FlagsEnum.BACKGROUND_COLOUR;
import static hellofx.FlagsEnum.GEOLOCATION;
import static hellofx.FlagsEnum.MONEY_SPENT;
import static hellofx.FlagsEnum.UPDATE_BUTTON;

public class FlagsmithService extends FeatureFlagsProxy {

  // # Email: boss@boss.com
  //# Password: Admin123!
  //# API Key: wuyCglzN.53Y57ie9L0B3Vmope97aFmKTV3v6AWeY
  private final String API_URL = "http://localhost:8000/api/v1/";
  private final String PROJECT_ENV_KEY = "JhgtXTF7NfDdx5iGsDdCBs";
  private final FlagsmithClient flagsmithClient;
  private final FeatureUser user;
  private FlagsAndTraits userFlagsAndTraits;

  public FlagsmithService(int id) {
    super(id);

      this.flagsmithClient = FlagsmithClient
        .newBuilder()
        .setApiKey(PROJECT_ENV_KEY)
        .withApiUrl(API_URL)
        .enableLogging(FlagsmithLoggerLevel.ERROR)
        .build();

    this.user = new FeatureUser();
    user.setIdentifier(EMAIL);

    this.updateUserTrait("email", EMAIL);
    this.updateUserTrait("country", COUNTRY);

    this.getUserFlagsAndTraits();
  }

  @Override
  public void getUserFlagsAndTraits() {
    userFlagsAndTraits = this.flagsmithClient.getUserFlagsAndTraits(user);
  }

  @Override
  public String getColour() {
    final String backgroundColour = this.flagsmithClient.getFeatureFlagValue(BACKGROUND_COLOUR.getValue(), this.userFlagsAndTraits);
    if (StringUtils.isNotBlank(backgroundColour)) {
      lastColor = backgroundColour;
    }
    return lastColor;
  }

  @Override
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

  @Override
  public boolean getUpdateButtonEnabled() {
    return this.flagsmithClient.hasFeatureFlag(UPDATE_BUTTON.getValue(), this.userFlagsAndTraits);
  }

  @Override
  public boolean getMoneySpentEnabled() {
    return this.flagsmithClient.hasFeatureFlag(MONEY_SPENT.getValue(), this.userFlagsAndTraits);
  }

  @Override
  public boolean getGeolocationEnabled() {
    return this.flagsmithClient.hasFeatureFlag(GEOLOCATION.getValue(), this.userFlagsAndTraits);
  }
}

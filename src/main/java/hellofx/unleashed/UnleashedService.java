package hellofx.unleashed;

import hellofx.FeatureFlagsProxy;
import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import io.getunleash.util.UnleashConfig;
import io.getunleash.variant.Variant;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static hellofx.FlagsEnum.BACKGROUND_COLOUR;
import static hellofx.FlagsEnum.MONEY_SPENT;
import static hellofx.FlagsEnum.UPDATE_BUTTON;

public class UnleashedService extends FeatureFlagsProxy {
  private final Unleash unleashedClient;
  private final UnleashContext unleashContext;
  private final Map evaluationContext = new HashMap<String, String>();

  public UnleashedService(int id) {
    super(id);
    UnleashConfig config = UnleashConfig.builder()
      .appName("unleash-onboarding-java")
      .instanceId("unleash-onboarding-instance")
      .unleashAPI("http://localhost:4242/api/")
      .fetchTogglesInterval(3)
      .apiKey("default:development.unleash-insecure-api-token") // in production use environment variable
      .build();

    this.unleashedClient = new DefaultUnleash(config);

    evaluationContext.put("email", EMAIL);
    evaluationContext.put("country", COUNTRY);
    this.unleashContext = new UnleashContext(EMAIL, null, null, evaluationContext);
  }

  @Override
  public void update(String country, String money) {
    evaluationContext.put("country", country);
    evaluationContext.put("money-balance", money);
  }

  @Override
  public void getUserFlagsAndTraits() {

  }

  @Override
  public String getColour() {
//      System.out.println("evaluationContext: " + evaluationContext);
    final Variant backgroundColour = this.unleashedClient.getVariant(BACKGROUND_COLOUR.getValue(), unleashContext);
    String variantKey = backgroundColour.getName();
//      System.out.println("Background colour: " + variantKey);

    if (StringUtils.isNotBlank(variantKey)) {
      lastColor = variantKey;
    }
    return lastColor;
  }

  @Override
  public boolean getUpdateButtonEnabled() {
//      System.out.println("evaluationContext: " + evaluationContext);
    final boolean isEnabled = this.unleashedClient.isEnabled(UPDATE_BUTTON.getValue(), unleashContext);
//      System.out.println("Money spent: " + isEnabled);
    return isEnabled;
  }

  @Override
  public boolean getMoneySpentEnabled() {
//      System.out.println("evaluationContext: " + evaluationContext);
    final boolean isEnabled = this.unleashedClient.isEnabled(MONEY_SPENT.getValue(), unleashContext);
//      System.out.println("Money spent: " + isEnabled);
    return isEnabled;
  }

  @Override
  public boolean getGeolocationEnabled() {
    return true;
  }
}

package hellofx.flipt2;

import hellofx.FeatureFlagsProxy;
import io.flipt.client.FliptClient;
import io.flipt.client.FliptException.EvaluationException;
import io.flipt.client.models.BooleanEvaluationResponse;
import io.flipt.client.models.VariantEvaluationResponse;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static hellofx.FlagsEnum.BACKGROUND_COLOUR;
import static hellofx.FlagsEnum.MONEY_SPENT;
import static hellofx.FlagsEnum.UPDATE_BUTTON;

// Same flags/segments as hellofx.flipt.FliptService, against the Flipt 2.0
// demo (see flipt2/ and the "Flipt 2.0" section of the README). Flipt 2.0
// adds the "environment" concept on top of namespaces, so this needs a
// newer flipt-client-java (1.3.4+, see pom.xml) whose FliptClient exposes
// .environment(...) - the older FliptEvaluationClient client used for v1
// cannot talk to a v2 server at all.
public class Flipt2Service extends FeatureFlagsProxy {
  private final FliptClient fliptClient;
  private final Map evaluationContext = new HashMap<String, String>();

  public Flipt2Service(int id) {
    super(id);
    try {
      this.fliptClient = FliptClient.builder()
        .url("http://localhost:8081")
        .environment("production")
        .namespace("default")
        .updateInterval(Duration.ofSeconds(2))
        .build();

      evaluationContext.put("email", EMAIL);
      evaluationContext.put("country", COUNTRY);
    } catch (EvaluationException e) {
      throw new RuntimeException(e);
    }
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
    try {
      final VariantEvaluationResponse backgroundColour = this.fliptClient.evaluateVariant(BACKGROUND_COLOUR.getValue(), this.EMAIL, evaluationContext);
      String variantKey = backgroundColour.getVariantKey();

      if (StringUtils.isNotBlank(variantKey)) {
        lastColor = variantKey;
      }
    } catch (EvaluationException e) {
      throw new RuntimeException(e);
    }
    return lastColor;
  }

  @Override
  public boolean getUpdateButtonEnabled() {
    try {
      final BooleanEvaluationResponse response = this.fliptClient.evaluateBoolean(UPDATE_BUTTON.getValue(), this.EMAIL, evaluationContext);
      return response.isEnabled();
    } catch (EvaluationException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getMoneySpentEnabled() {
    try {
      final BooleanEvaluationResponse response = this.fliptClient.evaluateBoolean(MONEY_SPENT.getValue(), this.EMAIL, evaluationContext);
      return response.isEnabled();
    } catch (EvaluationException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean getGeolocationEnabled() {
    return true;
  }
}
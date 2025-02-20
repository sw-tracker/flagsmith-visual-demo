package hellofx.flipt;

import hellofx.FeatureFlagsProxy;
import io.flipt.client.EvaluationException;
import io.flipt.client.FliptEvaluationClient;
import io.flipt.client.models.VariantEvaluationResponse;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static hellofx.FlagsEnum.BACKGROUND_COLOUR;

public class FliptService extends FeatureFlagsProxy {
  private final FliptEvaluationClient fliptClient;
  private final Map evaluationContext = new HashMap<String, String>();

  public FliptService(int id) {
    super(id);
    try {
      this.fliptClient = FliptEvaluationClient.builder().url("http://localhost:8080").updateInterval(Duration.ofSeconds(3)).build();

      evaluationContext.put("email", EMAIL);
      evaluationContext.put("country", COUNTRY);
    } catch (EvaluationException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(String country, String money) {
    evaluationContext.put("country", country);
  }

  @Override
  public void getUserFlagsAndTraits() {

  }

  @Override
  public String getColour() {
    try {
//      System.out.println("evaluationContext: " + evaluationContext);
      final VariantEvaluationResponse backgroundColour = this.fliptClient.evaluateVariant(BACKGROUND_COLOUR.getValue(), this.EMAIL, evaluationContext);
      String variantKey = backgroundColour.getVariantKey();
//      System.out.println("Background colour: " + variantKey);

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
    return true;
  }

  @Override
  public boolean getMoneySpentEnabled() {
    return false;
  }

  @Override
  public boolean getGeolocationEnabled() {
    return true;
  }
}

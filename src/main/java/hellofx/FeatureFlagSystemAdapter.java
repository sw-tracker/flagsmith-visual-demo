package hellofx;

import hellofx.flagsmith.FlagsmithService;
import hellofx.flipt.FliptService;
import hellofx.flipt2.Flipt2Service;
import hellofx.unleashed.UnleashedService;

public class FeatureFlagSystemAdapter {

  public final FeatureFlagSystems USE_FFS = FeatureFlagSystems.FLIPT2;

  public FeatureFlagsProxy getFfs(int id) {
    switch (USE_FFS) {
      case FLAGSMITH:
        return new FlagsmithService(id);
      case FLIPT:
        return new FliptService(id);
      case FLIPT2:
        return new Flipt2Service(id);
      case UNLEASHED:
        return new UnleashedService(id);
      default:
        throw new RuntimeException("Unknown FeatureFlagSystem: " + USE_FFS);
    }
  }

  public enum FeatureFlagSystems {
    FLAGSMITH,
    FLIPT,
    FLIPT2,
    UNLEASHED;
  }
}

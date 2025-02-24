package hellofx;

import hellofx.flagsmith.FlagsmithService;
import hellofx.flipt.FliptService;
import hellofx.unleashed.UnleashedService;

public class FeatureFlagSystemAdapter {

  public final FeatureFlagSystems USE_FFS = FeatureFlagSystems.UNLEASHED;

  public FeatureFlagsProxy getFfs(int id) {
    switch (USE_FFS) {
      case FLAGSMITH:
        return new FlagsmithService(id);
      case FLIPT:
        return new FliptService(id);
      case UNLEASHED:
        return new UnleashedService(id);
      default:
        throw new RuntimeException("Unknown FeatureFlagSystem: " + USE_FFS);
    }
  }

  public enum FeatureFlagSystems {
    FLAGSMITH,
    FLIPT,
    UNLEASHED;
  }
}

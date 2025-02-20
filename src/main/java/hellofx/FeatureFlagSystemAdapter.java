package hellofx;

import hellofx.flagsmith.FlagsmithConfiguration;

public class FeatureFlagSystemAdapter {

    public final FeatureFlagSystems USE_FFS = FeatureFlagSystems.FLAGSMITH;

    public FeatureFlagsProxy getFfs(int id) {
        switch (USE_FFS){
            case FLAGSMITH:
                return new FlagsmithConfiguration(id);
            default:
                throw new RuntimeException("Unknown FeatureFlagSystem: " + USE_FFS);
        }
    }

    public enum FeatureFlagSystems {
        FLAGSMITH,
        FEATURE_VISOR;
    }
}

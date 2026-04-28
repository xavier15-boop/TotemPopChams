package walksy.popchams.config;

import main.walksy.lib.api.WalksyLibApi;
import main.walksy.lib.core.config.impl.LocalConfig;

public class WalksyLibIntegration implements WalksyLibApi {

    @Override
    public LocalConfig getConfig() {
        return new Config().getOrCreateConfig();
    }
}

package walksy.popchams;

import net.fabricmc.api.ModInitializer;
import walksy.popchams.render.ChamExtractor;

public class TotemPopChams implements ModInitializer {

    private static ChamExtractor extractor;

    @Override
    public void onInitialize() {
        extractor = new ChamExtractor();
    }

    public static ChamExtractor getExtractor() {
        return extractor;
    }
}

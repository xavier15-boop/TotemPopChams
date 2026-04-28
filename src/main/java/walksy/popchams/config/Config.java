package walksy.popchams.config;

import main.walksy.lib.api.WalksyLibConfig;
import main.walksy.lib.core.config.impl.LocalConfig;
import main.walksy.lib.core.config.local.Category;
import main.walksy.lib.core.config.local.Option;
import main.walksy.lib.core.config.local.builders.LocalConfigBuilder;
import main.walksy.lib.core.config.local.options.BooleanOption;
import main.walksy.lib.core.config.local.options.ColorOption;
import main.walksy.lib.core.config.local.options.NumericalOption;
import main.walksy.lib.core.config.local.options.groups.OptionGroup;
import main.walksy.lib.core.config.local.options.type.WalksyLibColor;
import main.walksy.lib.core.utils.PathUtils;

public class Config implements WalksyLibConfig {

    public static boolean modEnabled = true;
    public static boolean showOwnPops = false;
    public static boolean fadeOut = true;
    public static int lifeTime = 50;
    public static boolean disperse = false;
    public static double disperseSpeed = 1.5;
    public static double disperseMaxDistance = 1;

    public static boolean filledModelEnabled = true;
    public static WalksyLibColor filledColor = new WalksyLibColor(137, 0, 255, 150);

    public static boolean wireframeEnabled = true;
    public static WalksyLibColor wireframeColor = new WalksyLibColor(100, 0, 255, 150);
    public static double wireframeThickness = 2.0;

    private static final Option<Boolean> modEnabledOption = BooleanOption.createBuilder("Mod Enabled", () -> modEnabled, modEnabled, newV -> modEnabled = newV)
            .build();

    private static final Option<Boolean> showOwnPopsOption = BooleanOption.createBuilder("Show Own Pops", () -> showOwnPops, showOwnPops, newV -> showOwnPops = newV)
            .availability(() -> modEnabled, "")
            .build();

    private static final Option<Boolean> fadeOutOption = BooleanOption.createBuilder("Fade Over Time", () -> fadeOut, fadeOut, newV -> fadeOut = newV)
            .availability(() -> modEnabled, "")
            .build();

    private static final Option<Integer> lifeTimeOption = NumericalOption.<Integer>createBuilder("Fading Life Time", () -> lifeTime, lifeTime, newV -> lifeTime = newV)
            .values(0, 250, 1)
            .availability(() -> modEnabled && fadeOut, "")
            .build();

    private static final Option<Boolean> disperseOption = BooleanOption.createBuilder("Disperse Enabled", () -> disperse, disperse, newV -> disperse = newV)
            .availability(() -> modEnabled, "")
            .build();

    private static final Option<Double> disperseSpeedOption = NumericalOption.<Double>createBuilder("Disperse Speed", () -> disperseSpeed, disperseSpeed, newV -> disperseSpeed = newV)
            .values(0D, 10D, 0.5D)
            .availability(() -> modEnabled && disperse, "") // was incorrectly checking fadeOut
            .build();

    private static final Option<Double> disperseMaxDistanceOption = NumericalOption.<Double>createBuilder("Fading Life Time", () -> disperseMaxDistance, disperseMaxDistance, newV -> disperseMaxDistance = newV)
            .values(0D, 8D, 0.1D)
            .availability(() -> modEnabled && disperse, "")
            .build();

    private static final Option<Boolean> filledModelEnabledOption = BooleanOption.createBuilder("Filled Model Enabled", () -> filledModelEnabled, filledModelEnabled, newV -> filledModelEnabled = newV)
            .availability(() -> modEnabled, "")
            .build();

    private static final Option<WalksyLibColor> filledModelColorOption = ColorOption.createBuilder("Filled Model Color", () -> filledColor, filledColor, newV -> filledColor = newV)
            .availability(() -> modEnabled && filledModelEnabled, "")
            .build();

    private static final Option<Boolean> wireframeEnabledOption = BooleanOption.createBuilder("Wireframe Enabled", () -> wireframeEnabled, wireframeEnabled, newV -> wireframeEnabled = newV)
            .availability(() -> modEnabled, "")
            .build();

    private static final Option<WalksyLibColor> wireframeColorOption = ColorOption.createBuilder("Wireframe Color", () -> wireframeColor, wireframeColor, newV -> wireframeColor = newV)
            .availability(() -> modEnabled && wireframeEnabled, "")
            .build();

    private static final Option<Double> wireframeThicknessOption = NumericalOption.<Double>createBuilder("Wireframe Thickness", () -> wireframeThickness, wireframeThickness, newV -> wireframeThickness = newV)
            .values(0D, 5D, 0.1D)
            .availability(() -> modEnabled && wireframeEnabled, "")
            .build();

    private static final Category generalCategory = Category.createBuilder("General")
            .group(OptionGroup.createBuilder("Global Options")
                    .addOption(modEnabledOption)
                    .addOption(showOwnPopsOption)
                    .addOption(fadeOutOption)
                    .addOption(lifeTimeOption)
                    .build())
            .group(OptionGroup.createBuilder("Disperse Options")
                    .addOption(disperseOption)
                    .addOption(disperseSpeedOption)
                    .addOption(disperseMaxDistanceOption)
                    .build())
            .group(OptionGroup.createBuilder("Filled Model Options")
                    .addOption(filledModelEnabledOption)
                    .addOption(filledModelColorOption)
                    .build())
            .group(OptionGroup.createBuilder("Wireframe Options")
                    .addOption(wireframeEnabledOption)
                    .addOption(wireframeColorOption)
                    .addOption(wireframeThicknessOption)
                    .build())
            .build();


    @Override
    public LocalConfig define() {
        return LocalConfig.createBuilder("Totem Pop Chams")
                .path(PathUtils.ofConfigDir("totempopchams"))
                .category(generalCategory)
                .build();
    }
}

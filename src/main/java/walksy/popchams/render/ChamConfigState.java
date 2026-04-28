package walksy.popchams.render;

import net.minecraft.util.math.MathHelper;
import walksy.popchams.capture.CapturedPlayer;
import walksy.popchams.config.Config;

public class ChamConfigState {
    public boolean renderFillModel;
    public boolean renderWireframe;
    public boolean disperse;
    public boolean fadeOut;
    public double disperseSpeed;
    public double disperseMaxDistance;
    public double lifeTime;
    public double wireframeThickness;
    public int filledColor;
    public int wireframeColor;
    public float displacementAmount;
    public float alpha;

    public void updateConfigRenderState(final CapturedPlayer player, final float delta) {
        this.renderFillModel = Config.filledModelEnabled;
        this.renderWireframe = Config.wireframeEnabled;
        this.disperse = Config.disperse;
        this.disperseSpeed = Config.disperseSpeed;
        this.disperseMaxDistance = Config.disperseMaxDistance;
        this.filledColor = Config.filledColor.getRGB();
        this.wireframeColor = Config.wireframeColor.getRGB();
        this.fadeOut = Config.fadeOut;
        this.lifeTime = Config.lifeTime;
        this.wireframeThickness = Config.wireframeThickness;
        this.displacementAmount = this.displacement(player, alpha);
        this.alpha = this.alpha(player, delta);
    }

    private float displacement(final CapturedPlayer player, final float delta) {
        final float s = Math.max(0, player.age - 1 + delta);
        final float ss = (float) MathHelper.clamp(this.disperseSpeed, 1f, 10f);
        final float sm = (float) Math.pow(ss / 10f, 2) * 2f;
        return this.disperse ? (float) Math.min(s * sm, this.disperseMaxDistance) : 0f;
    }

    private float alpha(final CapturedPlayer player, final float delta) {
        if (!this.fadeOut) {
            return 1f;
        }

        final float s = Math.max(0, player.age - 1 + delta);
        final float f = (float) (this.lifeTime * 0.8f);
        final float l = s >= f ? (float) (1f - ((s - f) / (this.lifeTime - f))) : 1f;
        final float d = this.disperse ? (float) (1f - (this.displacementAmount / this.disperseMaxDistance)) : 1f;
        return MathHelper.clamp(l * d, 0f, 1f);
    }
}

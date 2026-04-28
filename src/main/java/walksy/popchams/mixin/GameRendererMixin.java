package walksy.popchams.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.popchams.TotemPopChams;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    //credit: https://github.com/ytcyde

    @Shadow
    public abstract Camera getCamera();

    @Inject(
            method = "renderWorld(Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/MinecraftClient;getCameraEntity()Lnet/minecraft/entity/Entity;"
            )
    )
    private void onRenderWorldHandRendering(RenderTickCounter tickCounter, CallbackInfo ci) {
        float tickDelta = tickCounter.getTickProgress(true);

        Quaternionf q = this.getCamera().getRotation().conjugate(new Quaternionf());
        Matrix4f positionMatrix = new Matrix4f().rotate(q);

        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiplyPositionMatrix(positionMatrix);

        TotemPopChams.getExtractor().extract(matrixStack, tickDelta);
    }
}

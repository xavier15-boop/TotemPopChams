package walksy.popchams.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import walksy.popchams.config.Config;
import walksy.popchams.handler.PositionHandler;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "onEntityStatus", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/EntityStatusS2CPacket;getEntity(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;"))
    public void handleStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        if (!Config.modEnabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {
            return;
        }
        Entity entity = packet.getEntity(client.world);
        if (entity instanceof PlayerEntity player) {
            if (!Config.showOwnPops) {
                if (player == client.player) {
                    return;
                }
            }
            if (packet.getStatus() == EntityStatuses.USE_TOTEM_OF_UNDYING) {
                PositionHandler.handleTotem(player);
            }
        }
    }
}

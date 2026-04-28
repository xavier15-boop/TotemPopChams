package walksy.popchams.handler;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import walksy.popchams.capture.CapturedPlayer;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PositionHandler {

    private static final Set<CapturedPlayer> positions = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void handleTotem(final PlayerEntity player) {
        positions.add(new CapturedPlayer(MinecraftClient.getInstance().world, player));
    }

    public static Set<CapturedPlayer> getPositions() {
        return positions;
    }
}

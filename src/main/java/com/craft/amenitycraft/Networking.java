package com.craft.amenitycraft;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
public class Networking{public record RemoteSyncPayload(String blockId) implements CustomPayload{
public static final Id<RemoteSyncPayload> ID = new Id<>(Identifier.of(AmenityCraft.MOD_ID, "remote_sync"));
public static final PacketCodec<RegistryByteBuf, RemoteSyncPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, RemoteSyncPayload::blockId, RemoteSyncPayload::new);
@Override 
public Id<? extends CustomPayload> getId(){
    return ID;
}
}
public static void registerC2SPackets(){
PayloadTypeRegistry.playC2S().register(RemoteSyncPayload.ID, RemoteSyncPayload.CODEC);
ServerPlayNetworking.registerGlobalReceiver(RemoteSyncPayload.ID, (payload, context) -> {
    context.server().execute(() -> {
        var stack = context.player().getMainHandStack();
        if (stack.isOf(AmenityCraft.LUMEN_REMOTE)) {
            stack.set(AmenityCraft.TARGET_BLOCK, payload.blockId());
        }
    });
}); 
}
}
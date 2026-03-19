package com.craft.amenitycraft.mixin;
import com.craft.amenitycraft.AmenityCraft;
import com.craft.amenitycraft.block.BeatBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayerEntity.class)
public class PlayerStepMixin{
@Inject(method = "tick", at = @At("HEAD"))
private void onTick(CallbackInfo ci){
PlayerEntity player = (PlayerEntity) (Object) this;
BlockPos below = player.getBlockPos().down();
var world = player.getWorld();
var state = world.getBlockState(below);

if (state.isOf(AmenityCraft.BEAT_BLOCK)){

if (!state.get(BeatBlock.LIT)){
AmenityCraft.BEAT_BLOCK.onSteppedOn(world, below, state, player);
 }
  }
}
}
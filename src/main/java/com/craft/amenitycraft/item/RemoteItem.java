package com.craft.amenitycraft.item;
import com.craft.amenitycraft.AmenityCraft;
import com.craft.amenitycraft.screen.RemoteScreen;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class RemoteItem extends Item{

public RemoteItem(Settings settings){
super(settings);
}

@Override
public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
ItemStack stack = user.getStackInHand(hand);

if (user.isSneaking()) {
if (world.isClient) {
MinecraftClient.getInstance().setScreen(new RemoteScreen());
}

return TypedActionResult.success(stack);
}

if (!world.isClient){
String filter = stack.getOrDefault(AmenityCraft.TARGET_BLOCK, "minecraft:redstone_lamp");
BlockPos p = user.getBlockPos();
for (BlockPos t : BlockPos.iterate(p.add(-30, -10, -30), p.add(30, 10, 30))){
BlockState s = world.getBlockState(t);
if (filter.equals("minecraft:torch")){
if (s.isOf(Blocks.TORCH))
world.setBlockState(t, AmenityCraft.UNLIT_TORCH.getDefaultState());
else if (s.isOf(AmenityCraft.UNLIT_TORCH)) world.setBlockState(t, Blocks.TORCH.getDefaultState());

else if (s.isOf(Blocks.WALL_TORCH)){
world.setBlockState(t, AmenityCraft.UNLIT_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, s.get(WallTorchBlock.FACING)));
} 

else if (s.isOf(AmenityCraft.UNLIT_WALL_TORCH)){
world.setBlockState(t, Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, s.get(WallTorchBlock.FACING)));
}

} 
else if (Registries.BLOCK.getId(s.getBlock()).toString().equals(filter) && s.contains(Properties.LIT)) {
world.setBlockState(t, s.with(Properties.LIT, !s.get(Properties.LIT)));
  }
 }
}
return TypedActionResult.success(stack);
   }
}
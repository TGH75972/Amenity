package com.craft.amenitycraft.item;
import com.craft.amenitycraft.AmenityCraft;
import com.craft.amenitycraft.block.TurnedOffLanternBlock;
import com.craft.amenitycraft.screen.RemoteScreen;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
public class RemoteItem extends Item{
public RemoteItem(Settings settings){
super(settings);
}
@Override
public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
ItemStack stack = user.getStackInHand(hand);
if(user.isSneaking()){
if(world.isClient) MinecraftClient.getInstance().setScreen(new RemoteScreen());
return TypedActionResult.success(stack);
 }
if(!world.isClient){
String filter = stack.getOrDefault(AmenityCraft.TARGET_BLOCK, "Lumen Lamp");
BlockPos p = user.getBlockPos();
Boolean targetOn = null;
for(BlockPos t : BlockPos.iterate(p.add(-30, -10, -30), p.add(30, 10, 30))){
BlockState s = world.getBlockState(t);
if(filter.equals("Lumen Lamp") && s.isOf(AmenityCraft.LUMEN_LAMP)){
   targetOn = !s.get(Properties.LIT); break; 
  }
if(filter.equals("Candle") && s.getBlock() instanceof CandleBlock){
   targetOn = !s.get(Properties.LIT); break; 
  }
if(filter.equals("Normal Lantern")){
if(s.isOf(Blocks.LANTERN)){
   targetOn = false; break; 
  }
if(s.isOf(AmenityCraft.TURNED_OFF_LANTERN) && !s.get(TurnedOffLanternBlock.SOUL)){
   targetOn = true; break; 
  }
}
if(filter.equals("Soul Lantern")){
if(s.isOf(Blocks.SOUL_LANTERN)){
   targetOn = false; break; 
  }
if(s.isOf(AmenityCraft.TURNED_OFF_LANTERN) && s.get(TurnedOffLanternBlock.SOUL)){
  targetOn = true; break; 
}
}
if(filter.equals("Normal Campfire")){
if(s.isOf(Blocks.CAMPFIRE) && s.get(Properties.LIT)){
   targetOn = false; break; 
  }
if(s.isOf(Blocks.CAMPFIRE) && !s.get(Properties.LIT)){
   targetOn = true; break; 
  }
}
if(filter.equals("Soul Campfire")){
if(s.isOf(Blocks.SOUL_CAMPFIRE) && s.get(Properties.LIT)){
   targetOn = false; break; 
  }
if(s.isOf(Blocks.SOUL_CAMPFIRE) && !s.get(Properties.LIT)){
   targetOn = true; break;
   }
 }
}
if(targetOn != null){
for(BlockPos t : BlockPos.iterate(p.add(-30, -10, -30), p.add(30, 10, 30))){
BlockState s = world.getBlockState(t);
if(filter.equals("Lumen Lamp") && s.isOf(AmenityCraft.LUMEN_LAMP))
world.setBlockState(t, s.with(Properties.LIT, targetOn), Block.NOTIFY_LISTENERS);
if(filter.equals("Candle") && s.getBlock() instanceof CandleBlock)
world.setBlockState(t, s.with(Properties.LIT, targetOn), Block.NOTIFY_LISTENERS);
if(filter.equals("Normal Lantern")){
if(!targetOn && s.isOf(Blocks.LANTERN))
world.setBlockState(t, AmenityCraft.TURNED_OFF_LANTERN.getDefaultState().with(LanternBlock.HANGING, s.get(LanternBlock.HANGING)).with(TurnedOffLanternBlock.SOUL, false), Block.NOTIFY_LISTENERS);
if(targetOn && s.isOf(AmenityCraft.TURNED_OFF_LANTERN) && !s.get(TurnedOffLanternBlock.SOUL))
world.setBlockState(t, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, s.get(LanternBlock.HANGING)), Block.NOTIFY_LISTENERS);
}
if(filter.equals("Soul Lantern")){
if(!targetOn && s.isOf(Blocks.SOUL_LANTERN))
world.setBlockState(t, AmenityCraft.TURNED_OFF_LANTERN.getDefaultState().with(LanternBlock.HANGING, s.get(LanternBlock.HANGING)).with(TurnedOffLanternBlock.SOUL, true), Block.NOTIFY_LISTENERS);
if(targetOn && s.isOf(AmenityCraft.TURNED_OFF_LANTERN) && s.get(TurnedOffLanternBlock.SOUL))
world.setBlockState(t, Blocks.SOUL_LANTERN.getDefaultState().with(LanternBlock.HANGING, s.get(LanternBlock.HANGING)), Block.NOTIFY_LISTENERS);
}
if(filter.equals("Normal Campfire") && s.isOf(Blocks.CAMPFIRE)){
world.setBlockState(t, s.with(Properties.LIT, targetOn).with(Properties.WATERLOGGED, false), Block.NOTIFY_LISTENERS);
}
if(filter.equals("Soul Campfire") && s.isOf(Blocks.SOUL_CAMPFIRE)){
world.setBlockState(t, s.with(Properties.LIT, targetOn).with(Properties.WATERLOGGED, false), Block.NOTIFY_LISTENERS);
}
   }
  }
}
return TypedActionResult.success(stack);
   }
 }
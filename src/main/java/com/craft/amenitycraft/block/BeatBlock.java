package com.craft.amenitycraft.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
public class BeatBlock extends Block{
public static final BooleanProperty LIT=Properties.LIT;
public BeatBlock(Settings settings){
super(settings);
this.setDefaultState(this.getDefaultState().with(LIT, false));
}
@Override
public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity){
if(!world.isClient && !state.get(LIT)){
world.setBlockState(pos, state.with(LIT, true));
float pitch = 0.5f + world.random.nextFloat() * 0.15f;
world.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), SoundCategory.BLOCKS, 1f, pitch);
world.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 0.4f, pitch + 0.5f);
world.scheduleBlockTick(pos, this, 10);
 }
}
@Override 
public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random){
world.setBlockState(pos, state.with(LIT, false));
}
@Override 
protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
builder.add(LIT);
 }
}
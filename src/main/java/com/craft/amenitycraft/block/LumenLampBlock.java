package com.craft.amenitycraft.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
public class LumenLampBlock extends Block{
public static final BooleanProperty LIT = Properties.LIT;
public LumenLampBlock(Settings settings){
super(settings);
setDefaultState(getDefaultState().with(LIT, false));
}
@Override
protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
builder.add(LIT);
 }
}
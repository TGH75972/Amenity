package com.craft.amenitycraft.block;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
public class TurnedOffCampfireBlock extends CampfireBlock{
public static final BooleanProperty SOUL = BooleanProperty.of("soul");
public TurnedOffCampfireBlock(Settings settings){
super(false, 1, settings);
setDefaultState(getDefaultState().with(LIT, false).with(SIGNAL_FIRE, false).with(WATERLOGGED, false).with(SOUL, false));
}
@Override
protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
builder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING, SOUL);
 }
}
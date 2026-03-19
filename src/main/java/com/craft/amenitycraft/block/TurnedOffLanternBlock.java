package com.craft.amenitycraft.block;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
public class TurnedOffLanternBlock extends LanternBlock{
public static final BooleanProperty SOUL = BooleanProperty.of("soul");
public TurnedOffLanternBlock(Settings settings){
super(settings);
setDefaultState(getDefaultState().with(HANGING, false).with(WATERLOGGED, false).with(SOUL, false));
}
@Override
protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
builder.add(HANGING, WATERLOGGED, SOUL);
 }
}
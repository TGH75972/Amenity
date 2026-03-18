package com.craft.amenitycraft.item;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
public class HeadphonesItem extends Item{
public HeadphonesItem(Settings settings){
super(settings);
}

@Override
public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
if (world.isClient && entity instanceof PlayerEntity player){
ItemStack discStack = player.getInventory().getStack(0);
if (discStack.contains(DataComponentTypes.JUKEBOX_PLAYABLE)){
boolean isWearing = player.getEquippedStack(EquipmentSlot.HEAD) == stack;

if (isWearing){
    player.getAbilities().allowFlying = player.getAbilities().allowFlying;
}
  }
 }
}
}
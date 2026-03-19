package com.craft.amenitycraft.item;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ClickType;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
public class RepairKitItem extends Item{
public RepairKitItem(Settings settings){
super(settings);
}
@Override
public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference){
if (clickType != ClickType.LEFT || otherStack.isEmpty() || !otherStack.isDamageable()){
return false;
}
if (otherStack.isDamaged()) {
otherStack.setDamage(0);
Registry<Enchantment>registry=player.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT);
if (registry != null){
List<RegistryEntry<Enchantment>> possible = StreamSupport.stream(registry.getIndexedEntries().spliterator(), false).filter(entry -> entry.value().isSupportedItem(otherStack)).collect(Collectors.toList());
if (!possible.isEmpty()){
RegistryEntry<Enchantment> randomEnchant = possible.get(new Random().nextInt(possible.size()));
ItemEnchantmentsComponent enchantments = otherStack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(enchantments);
builder.set(randomEnchant, 1);
otherStack.set(DataComponentTypes.ENCHANTMENTS, builder.build());
  }
}
if (!player.getAbilities().creativeMode){
stack.setDamage(stack.getDamage() + 1);
if (stack.getDamage() >= stack.getMaxDamage()){
stack.setCount(0);
  }
 }
return true;
   }
return false;
  }
}
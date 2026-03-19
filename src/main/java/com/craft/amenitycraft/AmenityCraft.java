package com.craft.amenitycraft;
import com.craft.amenitycraft.block.*;
import com.craft.amenitycraft.item.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.component.ComponentType;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
public class AmenityCraft implements ModInitializer{
public static final String MOD_ID = "amenitycraft";
public static final ComponentType<String> TARGET_BLOCK = Registry.register(Registries.DATA_COMPONENT_TYPE,Identifier.of(MOD_ID, "target_block"),ComponentType.<String>builder().codec(Codec.STRING).build());
public static final Block BEAT_BLOCK = new BeatBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).luminance(s -> s.get(Properties.LIT) ? 15 : 0));
public static final Block LUMEN_LAMP = new LumenLampBlock(AbstractBlock.Settings.copy(Blocks.REDSTONE_LAMP).luminance(state -> state.get(Properties.LIT) ? 15 : 0));
public static final Block TURNED_OFF_LANTERN = new TurnedOffLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).luminance(s -> 0));
public static final Item LUMEN_REMOTE = new RemoteItem(new Item.Settings().maxCount(1));
public static final Item REPAIR_KIT = new RepairKitItem(new Item.Settings().maxDamage(59));
@Override
public void onInitialize(){
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "beat_block"), BEAT_BLOCK);
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "lumen_lamp"), LUMEN_LAMP);
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "turned_off_lantern"), TURNED_OFF_LANTERN);
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "beat_block"), new BlockItem(BEAT_BLOCK, new Item.Settings()));
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "lumen_lamp"), new BlockItem(LUMEN_LAMP, new Item.Settings()));
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "turned_off_lantern"), new BlockItem(TURNED_OFF_LANTERN, new Item.Settings()));
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "lumen_remote"), LUMEN_REMOTE);
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "repair_kit"), REPAIR_KIT);
Networking.registerC2SPackets();
 }
}
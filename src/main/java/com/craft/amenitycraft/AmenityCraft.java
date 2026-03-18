package com.craft.amenitycraft;
import com.craft.amenitycraft.block.BeatBlock;
import com.craft.amenitycraft.item.HeadphonesItem;
import com.craft.amenitycraft.item.RemoteItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
public class AmenityCraft implements ModInitializer {
public static final String MOD_ID = "amenitycraft";
public static final ComponentType<String> TARGET_BLOCK = Registry.register(Registries.DATA_COMPONENT_TYPE,Identifier.of(MOD_ID, "target_block"),ComponentType.<String>builder().codec(Codec.STRING).build());
public static final Block BEAT_BLOCK = new BeatBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).luminance(s -> s.get(BeatBlock.LIT) ? 15 : 0));
public static final Block UNLIT_TORCH = new TorchBlock(null, AbstractBlock.Settings.copy(Blocks.TORCH).luminance(s -> 0));
public static final Block UNLIT_WALL_TORCH = new WallTorchBlock(null, AbstractBlock.Settings.copy(Blocks.WALL_TORCH).luminance(s -> 0));
public static final Item SONIC_HEADPHONES = new HeadphonesItem(new Item.Settings().maxCount(1).equipmentSlot((entity, stack) -> EquipmentSlot.HEAD));
public static final Item LUMEN_REMOTE = new RemoteItem(new Item.Settings().maxCount(1));
@Override
public void onInitialize() {
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "beat_block"), BEAT_BLOCK);
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "unlit_torch"), UNLIT_TORCH);
Registry.register(Registries.BLOCK, Identifier.of(MOD_ID, "unlit_wall_torch"), UNLIT_WALL_TORCH);
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "beat_block"), new BlockItem(BEAT_BLOCK, new Item.Settings()));
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "sonic_headphones"), SONIC_HEADPHONES);
Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "lumen_remote"), LUMEN_REMOTE);
Networking.registerC2SPackets();
  }
}
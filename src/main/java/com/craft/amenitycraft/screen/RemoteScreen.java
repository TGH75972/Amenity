package com.craft.amenitycraft.screen;
import com.craft.amenitycraft.Networking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
public class RemoteScreen extends Screen{
public RemoteScreen(){
super(Text.literal("Remote Frequency Settings"));
}

@Override
protected void init(){
String[] opts = {"minecraft:torch", "minecraft:redstone_lamp", "minecraft:candle"};
int y = 40;
for (String opt : opts){
this.addDrawableChild(ButtonWidget.builder(Text.literal("Target: " + opt), b->{
ClientPlayNetworking.send(new Networking.RemoteSyncPayload(opt));
this.close();
}).dimensions(this.width / 2 - 100, y, 200, 20).build());
y += 25;
 }
  }
}
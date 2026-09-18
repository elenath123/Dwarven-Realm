package com.github.hummel.drealm.nei;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.nbt.NBTTagCompound;

public final class NEICompat {
    private NEICompat() {
    }

    public static void registerHandlerInfo() {
        sendHandlerInfo("drealm.red_mountains.crafting", "drealm:red_dwarven_table");
        sendHandlerInfo("drealm.wind_mountains.crafting", "drealm:wind_dwarven_table");

        sendCatalystInfo("drealm.red_mountains.crafting", "drealm:red_dwarven_table");
        sendCatalystInfo("drealm.wind_mountains.crafting", "drealm:wind_dwarven_table");
    }

    private static void sendHandlerInfo(String handlerId, String itemName) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("handler", handlerId);
        tag.setString("modName", "Dwarven Realm");
        tag.setString("modId", "drealm");
        tag.setBoolean("modRequired", true);
        tag.setString("itemName", itemName);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", tag);
    }

    private static void sendCatalystInfo(String handlerId, String itemName) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("handlerID", handlerId);
        tag.setString("itemName", itemName);
        tag.setInteger("priority", 0);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", tag);
    }
}
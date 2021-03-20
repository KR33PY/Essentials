package com.earth2me.essentials.items;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionData;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ItemDataDeserializer implements JsonDeserializer<FlatItemDb.ItemData> {

    private static final Field MATERIAL;
    private static final Field FALLBACKS;
    private static final Field POTIONDATA;
    private static final Field ENTITY;

    static {
        Field material = null;
        Field fallbacks = null;
        Field potionData = null;
        Field entity = null;
        try {
            material = FlatItemDb.ItemData.class.getDeclaredField("material");
            fallbacks = FlatItemDb.ItemData.class.getDeclaredField("fallbacks");
            potionData = FlatItemDb.ItemData.class.getDeclaredField("potionData");
            entity = FlatItemDb.ItemData.class.getDeclaredField("entity");
            material.setAccessible(true);
            fallbacks.setAccessible(true);
            potionData.setAccessible(true);
            entity.setAccessible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        MATERIAL = material;
        FALLBACKS = fallbacks;
        POTIONDATA = potionData;
        ENTITY = entity;
    }

    @Override
    public FlatItemDb.ItemData deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) {
        final FlatItemDb.ItemData data = new FlatItemDb.ItemData(Material.AIR);
        try {
            final JsonObject root = json.getAsJsonObject();
            final JsonElement material = root.get("material");
            final JsonElement fallbacks = root.get("fallbacks");
            final JsonElement potionData = root.get("potionData");
            final JsonElement entity = root.get("entity");
            if (material != null) {
                MATERIAL.set(data, FlatItemDb.gson.fromJson(material, String.class));
            }
            if (fallbacks != null) {
                FALLBACKS.set(data, FlatItemDb.gson.fromJson(fallbacks, String[].class));
            }
            if (potionData != null) {
                POTIONDATA.set(data, FlatItemDb.gson.fromJson(potionData, PotionData.class));
            }
            if (entity != null) {
                ENTITY.set(data, EntityType.valueOf(entity.getAsString())); // problematic, important to use valueOf!
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

}

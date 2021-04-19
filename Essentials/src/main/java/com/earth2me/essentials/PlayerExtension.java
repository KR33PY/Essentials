/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Server
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package com.earth2me.essentials;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlayerExtension {
    protected Player base;
    private String name;

    public PlayerExtension(Player base) {
        this.base = base;
        this.name = base.getName();
    }

    public final Player getBase() {
        final Player player;
        if (!this.base.isValid() && (player = Bukkit.getPlayerExact((String)this.name)) != null) {
            this.base = player;
        }
        return this.base;
    }

    public final Player setBase(Player base) {
        this.base = base;
        return this.base;
    }

    public Server getServer() {
        return this.getBase().getServer();
    }

    public World getWorld() {
        return this.getBase().getWorld();
    }

    public Location getLocation() {
        return this.getBase().getLocation();
    }
}

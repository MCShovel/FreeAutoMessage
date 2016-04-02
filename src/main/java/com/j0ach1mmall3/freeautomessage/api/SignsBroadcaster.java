package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class SignsBroadcaster extends PlayerBroadcaster {
    private static final String[] CLEAR = new String[4];

    private final List<String> signs;

    public SignsBroadcaster(Main plugin, String identifier, boolean random, int interval, List<String> messages, List<String> enabledWorlds, String permission, List<String> signs) {
        super(plugin, identifier, random, interval, messages, enabledWorlds, permission);
        this.signs = signs;
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        for(String s : this.signs) {
            if(this.getWorld(s) == null) continue;
            p.sendSignChange(this.deserializeLocation(s), CLEAR);
            p.sendSignChange(this.deserializeLocation(s), this.getSign(p, message));
        }
    }

    private Location deserializeLocation(String s) {
        String[] parts = s.split("/");
        return new Location(Bukkit.getWorld(parts[0]), Parsing.parseInt(parts[1]), Parsing.parseInt(parts[2]), Parsing.parseInt(parts[3]));
    }

    private World getWorld(String s) {
        String[] parts = s.split("/");
        return Bukkit.getWorld(parts[0]);
    }

    private String[] getSign(Player p, String message) {
        String[] splitted = message.split("\\|");
        String[] sign = new String[4];
        for(int i=0;i<splitted.length;i++) {
            sign[i] = Placeholders.parse(splitted[i], p);
        }
        return sign;
    }
}

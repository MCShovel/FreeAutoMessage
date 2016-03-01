package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.Parsing;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class BossbarBroadcaster extends PlayerBroadcaster {
    private final Map<Player, BossBar> previous = new HashMap<>();

    public BossbarBroadcaster(String identifier, boolean random, int interval, List<String> messages, List<String> enabledWorlds, String permission) {
        super(identifier, random, interval, messages, enabledWorlds, permission);
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        String[] splitted = message.split("\\|");
        if(this.previous.containsKey(p)) this.previous.get(p).removeAll();
        BossBar bossBar = Bukkit.getServer().createBossBar(Placeholders.parse(splitted[0], p), BarColor.valueOf(splitted[2]), BarStyle.valueOf(splitted[3]));
        bossBar.setProgress(Parsing.parseDouble(splitted[1]) / 100);
        bossBar.addPlayer(p);
        this.previous.put(p, bossBar);
    }

    public void cleanup() {
        for(Map.Entry<Player, BossBar> entry : this.previous.entrySet()) {
            entry.getValue().removeAll();
        }
    }
}
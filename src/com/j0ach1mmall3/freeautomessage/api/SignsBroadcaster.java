package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.integration.Placeholders;
import com.j0ach1mmall3.jlib.methods.Parsing;
import com.j0ach1mmall3.jlib.methods.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class SignsBroadcaster implements Broadcaster {
    private String identifier;
    private boolean random;
    private List<String> signs;
    private int interval;
    private List<String> messages;
    private int count = 0;

    public SignsBroadcaster(String identifier, boolean random, List<String> signs, int interval, List<String> messages) {
        this.identifier = identifier;
        this.random = random;
        this.signs = signs;
        this.interval = interval;
        this.messages = messages;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public boolean getRandom() {
        return this.random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public List<String> getSigns() {
        return this.signs;
    }

    public void setSigns(List<String> signs) {
        this.signs = signs;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void broadcast() {
        int id;
        if(this.random) id = Random.getInt(this.messages.size());
        else {
            if(this.count >= this.messages.size()) this.count = 0;
            id = this.count;
            this.count++;
        }
        for(String s : this.signs) {
            if(this.getWorld(s) == null) continue;
            Block b = this.getWorld(s).getBlockAt(this.deserializeLocation(s));
            if(b.getState() instanceof Sign) {
                Sign sign = (Sign) b.getState();
                for(int i=0;i<4;i++) {
                    sign.setLine(i, "");
                }
                this.setSign(sign, this.messages.get(id));
            }
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

    private void setSign(Sign sign, String message) {
        String[] parts = message.split("\\|");
        int a = parts.length;
        if(parts.length > 4) a = 4;
        for(int i=0;i<a;i++) {
            sign.setLine(i, Placeholders.parse(parts[i]));
            sign.update();
        }
    }
}

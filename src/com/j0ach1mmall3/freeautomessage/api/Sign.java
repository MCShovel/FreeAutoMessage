package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.freeautomessage.Main;
import org.bukkit.Location;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class Sign {
    private final Main plugin;
    private String broadcasterIdentifier;
    private Location location;

    public Sign(Main plugin, String broadcasterIdentifier, Location location) {
        this.plugin = plugin;
        this.broadcasterIdentifier = broadcasterIdentifier;
        this.location = location;
    }

    public String getBroadcasterIdentifier() {
        return this.broadcasterIdentifier;
    }

    public void setBroadcasterIdentifier(String broadcasterIdentifier) {
        this.broadcasterIdentifier = broadcasterIdentifier;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void add() {
        this.plugin.getSigns().addSign(this);
    }

    public void remove() {
        this.plugin.getSigns().removeSign(this);
    }

    public List<String> list() {
        return this.plugin.getSigns().listSigns(this.broadcasterIdentifier);
    }

    public boolean exists() {
        return this.plugin.getSigns().existsSign(this);
    }

    public boolean isSignsBroadcaster() {
        return this.plugin.getSigns().isSignsBroadcaster(this.broadcasterIdentifier);
    }
}

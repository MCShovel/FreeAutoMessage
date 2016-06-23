package com.j0ach1mmall3.freeautomessage.config;

import com.j0ach1mmall3.freeautomessage.Main;
import com.j0ach1mmall3.freeautomessage.api.Sign;
import com.j0ach1mmall3.freeautomessage.api.SignsBroadcaster;
import org.bukkit.Location;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public final class Signs extends BroadcasterConfig {

    public Signs(Main plugin) {
        super("signs.yml", plugin, "Signs");
    }

    @Override
    protected SignsBroadcaster getBroadcasterByIdentifier(String identifier) {
        String path = "SignsBroadcasters." + identifier + '.';
        return new SignsBroadcaster(
                identifier,
                this.config.getBoolean(path + "Random"),
                this.config.getInt(path + "Interval"),
                this.config.getStringList(path + "Messages"),
                this.config.getString(path + "Permission"),
                this.config.getStringList(path + "Signs")
        );
    }

    public void addSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        signs.add(this.serializeLocation(sign.getLocation()));
        this.config.set("SignsBroadcasters." + sign.getBroadcasterIdentifier() + ".Signs", signs);
        this.customConfig.saveConfig(this.config);
    }

    public void removeSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        signs.remove(this.serializeLocation(sign.getLocation()));
        this.config.set("SignsBroadcasters." + sign.getBroadcasterIdentifier() + ".Signs", signs);
        this.customConfig.saveConfig(this.config);
    }

    public List<String> listSigns(String broadcaster) {
        return this.config.getStringList("SignsBroadcasters." + broadcaster + ".Signs");
    }

    public boolean existsSign(Sign sign) {
        List<String> signs = this.config.getStringList("SignsBroadcasters." + sign.getBroadcasterIdentifier() + "Signs");
        return signs.contains(this.serializeLocation(sign.getLocation()));
    }

    public boolean isSignsBroadcaster(String identifier) {
        return this.customConfig.getKeys("SignsBroadcasters").contains(identifier);
    }

    private String serializeLocation(Location l) {
        return l.getWorld().getName() + '/' + String.valueOf(l.getBlockX()) + '/' + String.valueOf(l.getBlockY()) + '/' + String.valueOf(l.getBlockZ());
    }
}

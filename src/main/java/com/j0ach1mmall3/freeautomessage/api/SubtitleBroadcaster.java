package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.visual.Subtitle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public final class SubtitleBroadcaster extends WorldsBroadcaster {
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    public SubtitleBroadcaster(String identifier, boolean random, int interval, List<String> messages, String permission, List<String> enabledWorlds, int fadeIn, int stay, int fadeOut) {
        super(identifier, random, interval, messages, permission, enabledWorlds);
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public int getFadeIn() {
        return this.fadeIn;
    }

    public int getStay() {
        return this.stay;
    }

    public int getFadeOut() {
        return this.fadeOut;
    }

    @Override
    protected void broadcastInternal(Player p, String message) {
        new Subtitle(p, message, this.fadeIn, this.stay, this.fadeOut).send();
    }
}

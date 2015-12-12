package com.j0ach1mmall3.freeautomessage;

import com.j0ach1mmall3.freeautomessage.api.Broadcaster;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/08/2015
 */
public class BroadcastScheduler extends BukkitRunnable {
    private final Broadcaster broadcaster;

    public BroadcastScheduler(Broadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }

    public void run() {
        this.broadcaster.broadcast();
    }
}

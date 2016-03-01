package com.j0ach1mmall3.freeautomessage.api;

import com.j0ach1mmall3.jlib.methods.Random;

import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 18/08/2015
 */
public abstract class Broadcaster {
    private final String identifier;
    private final boolean random;
    private final int interval;
    private final List<String> messages;
    private int count;

    public Broadcaster(String identifier, boolean random, int interval, List<String> messages) {
        this.identifier = identifier;
        this.random = random;
        this.interval = interval;
        this.messages = messages;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean isRandom() {
        return this.random;
    }

    public int getInterval() {
        return this.interval;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public final void broadcast() {
        int id;
        if(this.random) id = Random.getInt(this.messages.size());
        else {
            if(this.count >= this.messages.size()) this.count = 0;
            id = this.count;
            this.count++;
        }
        this.broadcast(this.messages.get(id));
    }

    protected abstract void broadcast(String message);
}

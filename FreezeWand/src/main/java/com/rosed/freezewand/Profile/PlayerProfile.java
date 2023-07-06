package com.rosed.freezewand.Profile;

import java.util.UUID;

public class PlayerProfile {

    private UUID uuid;
    private boolean frozen;

    public PlayerProfile(UUID uuid)   {

        this.uuid = uuid;
        frozen = false;

    }

    /**
     * return true if player is frozen
     * @return frozen state
     */
    public boolean isFrozen()   { return frozen; }

    /**
     * set frozen state of player
     * @param frozen freeze player
     */
    public void setFrozen(boolean frozen)   { this.frozen = frozen; }
}

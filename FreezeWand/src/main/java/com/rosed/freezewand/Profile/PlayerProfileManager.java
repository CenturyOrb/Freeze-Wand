package com.rosed.freezewand.Profile;

import java.util.HashMap;
import java.util.UUID;

public class PlayerProfileManager {

    private HashMap<UUID, PlayerProfile> playerProfiles;

    public PlayerProfileManager()   {
        playerProfiles = new HashMap<>();
    }

    /**
     * get playerProfile Hashmap
     * @return returns playerProfiles map
     */
    public HashMap<UUID, PlayerProfile> getPlayerProfiles()   { return playerProfiles; }

}

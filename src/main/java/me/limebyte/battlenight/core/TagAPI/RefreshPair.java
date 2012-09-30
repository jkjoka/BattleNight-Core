package me.limebyte.battlenight.core.TagAPI;

import org.bukkit.entity.Player;

public class RefreshPair {
    private final Player seer;
    private final Player target;

    public RefreshPair(Player seer, Player target) {
        this.seer = seer;
        this.target = target;
    }

    public Player getSeer() {
        return this.seer;
    }

    public Player getTarget() {
        return this.target;
    }
}

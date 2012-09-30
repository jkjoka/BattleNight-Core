package me.limebyte.battlenight.core.TagAPI;

import java.util.ArrayList;


import org.bukkit.entity.Player;

public class ShowBomb implements Runnable {
    private final ArrayList<RefreshPair> pairs = new ArrayList<RefreshPair>();

    public void queue(RefreshPair pair) {
        this.pairs.add(pair);
    }

    @Override
    public void run() {
        for (final RefreshPair pair : this.pairs) {
            final Player seer = pair.getSeer();
            final Player target = pair.getTarget();
            if ((seer != null) && (target != null)) {
                seer.showPlayer(target);
            }
        }
    }
}

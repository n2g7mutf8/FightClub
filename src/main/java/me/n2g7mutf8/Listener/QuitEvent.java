package me.n2g7mutf8.Listener;

import me.n2g7mutf8.User.Fighter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        Fighter fighter = new Fighter(player.getUniqueId());

        if (fighter.getCurrentKit() != null) {
            fighter.setCurrentKit(null);
        } else if (fighter.getKillstreak() >= 1) {
            fighter.setKillstreak(0);
        }
    }
}

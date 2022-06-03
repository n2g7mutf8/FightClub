package me.n2g7mutf8.Listener;

import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.User.FighterManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Fighter fighter = new Fighter(player.getUniqueId());

        FighterManager.resetPlayer(player);
    }
}

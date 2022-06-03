package me.n2g7mutf8.Listener;

import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.User.FighterManager;
import me.n2g7mutf8.Utils.ColorHelper;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeadEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() == null) { return; }

        Player victim = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        Fighter victimProfile = new Fighter(victim.getUniqueId());
        Fighter killerProfile = new Fighter(killer.getUniqueId());

        victimProfile.setDeaths(victimProfile.getDeaths() + 1);
        killerProfile.setKills(killerProfile.getKills() + 1);

        killerProfile.setGold(killerProfile.getGold() + 25);

        victimProfile.setKillstreak(0);
        if (killerProfile.getKillstreak() <= 0) {
            killerProfile.setKillstreak(1);
        } else {
            killerProfile.setKillstreak(killerProfile.getKillstreak() + 1);
        }

        victimProfile.setCurrentKit(null);

        event.getDrops().clear();
        event.setDeathMessage(null);

        victim.getWorld().playEffect(victim.getLocation(), Effect.MAGIC_CRIT, 1, 1);
        killer.playSound(killer.getLocation(), Sound.BLOCK_NOTE_PLING, 1 , 1);

        String[] msg = new String[] {
            ColorHelper.translate("&a&m----------------------------------------"),
            ColorHelper.translate("&a&l✖ &cEnemy Killed:" + victim.getName()),
            ColorHelper.translate("&a&l➜ &fGold:&a " + killerProfile.getGold() + " &f| Kills: &b" + killerProfile.getKills() + " &f(&b+1&f)"),
            ColorHelper.translate("&a&m----------------------------------------")
        };

        killer.sendMessage(msg);
        FighterManager.resetPlayer(victim);
    }
}

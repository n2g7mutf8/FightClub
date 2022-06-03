package me.n2g7mutf8.Commands;

import me.lucko.helper.Commands;
import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.User.FighterManager;
import org.bukkit.entity.Player;

public class SpawnCommand {

    public void register() {
        Commands.create()
                .assertPlayer()
                .assertUsage("[command]")
                .handler(c -> {
                    Player sender = c.sender();
                    Fighter fighter = new Fighter(sender.getUniqueId());

                    FighterManager.resetPlayer(sender);
                }).register("spawn","casual","sp");
    }
}

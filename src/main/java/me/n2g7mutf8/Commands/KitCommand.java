package me.n2g7mutf8.Commands;

import me.lucko.helper.Commands;
import me.n2g7mutf8.GUI.KitMenu;
import org.bukkit.entity.Player;

public class KitCommand {

    public void register() {
        Commands.create()
                .assertPlayer()
                .assertUsage("[command]")
                .handler(c -> {
                    Player sender = c.sender();

                    new KitMenu(sender).open();
                }).register("kit","kits","k");
    }
}

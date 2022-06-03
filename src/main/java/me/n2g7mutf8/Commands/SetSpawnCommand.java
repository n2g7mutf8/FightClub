package me.n2g7mutf8.Commands;

import me.lucko.helper.Commands;
import me.n2g7mutf8.Core;
import me.n2g7mutf8.Utils.ColorHelper;
import org.bukkit.entity.Player;

public class SetSpawnCommand {

    private final static Core plugin = Core.getInstance();

    public void register() {
        Commands.create()
                .assertPlayer()
                .assertPermission("fc.setspawn", ColorHelper.translate("&7(&cError&7) &cYou don't have permission to do that >:("))
                .assertUsage("[command]")
                .handler(c -> {
                    Player sender = c.sender();

                    plugin.config.set("spawn.x", sender.getLocation().getX());
                    plugin.config.set("spawn.y", sender.getLocation().getY());
                    plugin.config.set("spawn.z", sender.getLocation().getZ());
                    plugin.config.set("spawn.yaw", sender.getLocation().getYaw());
                    plugin.config.set("spawn.pitch", sender.getLocation().getPitch());
                }).register("setspawn","setcasual","setsp");
    }
}

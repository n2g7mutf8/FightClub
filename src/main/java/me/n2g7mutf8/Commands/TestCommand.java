package me.n2g7mutf8.Commands;

import me.lucko.helper.Commands;
import me.n2g7mutf8.GUI.TestGUI;
import org.bukkit.entity.Player;

public class TestCommand {

    public void register() {
        Commands.create()
            .assertPlayer()
            .assertUsage("[command]")
            .handler(c -> {
                Player sender = c.sender();

                new TestGUI(sender);
            }).register("test", "sus");
    }
}

package me.n2g7mutf8.User;

import me.n2g7mutf8.Core;
import me.n2g7mutf8.Utils.ColorHelper;
import me.n2g7mutf8.Utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class FighterManager {

    private static final Core plugin = Core.getInstance();

    public static void giveSpawnItem(Player player) {
        Fighter fighter = new Fighter(player.getUniqueId());

        player.getInventory().setItem(0,
                new ItemBuilder(Material.ENCHANTED_BOOK)
                        .name(ColorHelper.translate("&8&l» &a&lYour Stats &8&l«")).build());
        player.getInventory().setItem(4,
                new ItemBuilder(Material.COMPASS)
                        .name(ColorHelper.translate("&8&l» &a&lKit Selector &8&l«")).build());
        player.getInventory().setItem(8,
                new ItemBuilder(Material.ENDER_CHEST)
                        .name(ColorHelper.translate("&8&l» &a&lEvent &8&l«")).build());
    }

    public static void moveToSpawn(Player player) {
        Location spawn = new Location(player.getWorld(),
                plugin.config.getDouble("spawn.x"),
                plugin.config.getDouble("spawn.y"),
                plugin.config.getDouble("spawn.z"));
        player.teleport(spawn);
    }

    public static void resetPlayer(Player player) {
        player.setGameMode(GameMode.SURVIVAL);

        player.closeInventory();
        player.getInventory().clear();
        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setArmorContents(null);
        player.updateInventory();

        player.setHealth(20.0D);
        player.setFoodLevel(20);
        player.setSaturation(12.8F);
        player.setMaximumNoDamageTicks(20);
        player.setFireTicks(0);
        player.setFallDistance(0.0F);

        player.setLevel(0);
        player.setExp(0.0F);

        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.2F);
        player.setAllowFlight(false);

        player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);

        Fighter fighter = new Fighter(player.getUniqueId());

        fighter.setCurrentKit(null);

        giveSpawnItem(player);
        moveToSpawn(player);
    }

    public static void sendStats(Player player, Fighter user) {
        final String[] stats = new String[]{
                "&f&m-----------------------------------------------------",
                "&a&lYour Stats",
                "&a Kills: &f" + user.getKills(),
                "&a Deaths: &f" + user.getDeaths(),
                "&a KD: &f" + (double) user.getKills() / user.getDeaths(),
                "&a KillStreak: &f" + user.getKillstreak(),
                "&a Credits: &f" + user.getGold(),
                "&f&m-----------------------------------------------------"
        };

        for (String msg : stats) {
            player.sendMessage(ColorHelper.translate(msg));
        }
    }
}

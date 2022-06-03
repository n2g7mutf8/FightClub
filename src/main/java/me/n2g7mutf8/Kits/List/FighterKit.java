package me.n2g7mutf8.Kits.List;

import me.n2g7mutf8.Kits.Kit;
import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.Utils.ColorHelper;
import me.n2g7mutf8.Utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FighterKit extends Kit {

    public FighterKit() {
        super("&AFighter", Material.IRON_SWORD, new String[] {
                "&7Welcome to the server !",
                "&7This is the basic kit with",
                "&7decent ability !",
                "",
                "&a&lClick to equip"
        });
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[] {
            new ItemStack(Material.CHAINMAIL_BOOTS),
            new ItemStack(Material.CHAINMAIL_LEGGINGS),
            new ItemStack(Material.CHAINMAIL_CHESTPLATE),
            new ItemStack(Material.CHAINMAIL_HELMET)
        };
    }

    @Override
    public ItemStack getSword() {
        return new ItemStack(Material.STONE_SWORD);
    }

    @Override
    public ItemStack getAbilityItem() {
        List<String> lore = new ArrayList<>();
        lore.add("&7With each hit you take, there is a");
        lore.add("&7chance you will receive Strength I.");
        lore.add("");
        lore.add("&b*this ability auto engages*");

        return new ItemBuilder(Material.NETHER_STAR)
                .name(ColorHelper.translate("&a&lTriggered &7(Passive)"))
                .lore(ColorHelper.translate(lore)).build();
    }

    @Override
    public PotionEffect[] getPotionEffects() {
        return new PotionEffect[] {
                new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0)
        };
    }

    @Override
    public void interactEvent(PlayerInteractEvent event) {

    }

    @Override
    public void damageEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null) { return; }
        if (!(event.getEntity() instanceof Player)) { return; }

        Player player = (Player) event.getEntity();
        Fighter fighter = new Fighter(player.getUniqueId());

        if ( fighter.getCurrentKit() != this ) { return; }

        Random ran = new Random();
        int x = ran.nextInt(100) + 1;
        int d = fighter.getKitLevel().get(this);

        if (x < 25) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, d * 60, 0));
        }
    }
}

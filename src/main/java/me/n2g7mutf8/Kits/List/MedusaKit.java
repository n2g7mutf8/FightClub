package me.n2g7mutf8.Kits.List;

import me.n2g7mutf8.Kits.Kit;
import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.Utils.ColorHelper;
import me.n2g7mutf8.Utils.ItemBuilder;
import org.bukkit.Color;
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

public class MedusaKit extends Kit {

    public MedusaKit() {
        super("&2Medusa", Material.FERMENTED_SPIDER_EYE, new String[] {
                "&7Poisonous entity",
                "&7Your poison is so strong that",
                "&7it can kill your enemies",
                "&7easily",
                "",
                "&2&lClick to equip"
        });
    }

    @Override
    public ItemStack[] getArmor() {
        return new ItemStack[] {
                new ItemBuilder(Material.LEATHER_BOOTS).color(Color.GREEN).build(),
                new ItemBuilder(Material.LEATHER_LEGGINGS).color(Color.GREEN).build(),
                new ItemStack(Material.IRON_CHESTPLATE),
                new ItemBuilder(Material.LEATHER_HELMET).color(Color.GREEN).build()
        };
    }

    @Override
    public ItemStack getSword() {
        return new ItemStack(Material.WOOD_SWORD);
    }

    @Override
    public ItemStack getAbilityItem() {
        List<String> lore = new ArrayList<>();
        lore.add("&7With each hit your enemy take, there is a");
        lore.add("&7chance they will receive Poison II.");
        lore.add("");
        lore.add("&b*this ability auto engages*");

        return new ItemBuilder(Material.FERMENTED_SPIDER_EYE)
                .name(ColorHelper.translate("&2&lPoisonous &7(Passive)"))
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
        if (event.getDamager() == null) { return; }
        if (event.getEntity() == null) { return; }
        if (!(event.getDamager() instanceof Player)) { return; }
        if (!(event.getEntity() instanceof Player)) { return; }

        Player player = (Player) event.getDamager();
        Fighter fighter = new Fighter(player.getUniqueId());

        Player victim = (Player) event.getEntity();

        if ( fighter.getCurrentKit() != this ) { return; }

        Random ran = new Random();
        int x = ran.nextInt(100) + 1;
        int d = fighter.getKitLevel().get(this);

        if (x < 20) {
            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, d * 40, 1));
        }
    }
}


package me.n2g7mutf8.Kits;

import me.n2g7mutf8.User.Fighter;
import me.n2g7mutf8.Utils.ColorHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public abstract class Kit implements Listener {

    private final String name;
    private final Material icon;
    private final String[] desc;

    public Kit(String name, Material icon, String[] desc) {
        this.name = name;
        this.icon = icon;
        this.desc = desc;
    }

    public String getName() { return this.name; }
    public Material getIcon() { return this.icon; }
    public String[] getDesc() { return this.desc; }

    public void equipKit(Player player) {
        Fighter fighter = new Fighter(player.getUniqueId());
        player.getInventory().clear();

        Arrays.stream(this.getPotionEffects()).forEach(player::addPotionEffect);
        player.getInventory().setArmorContents(this.getArmor());
        player.getInventory().setItem(0, getSword());
        if (getAbilityItem() != null) player.getInventory().setItem(8, getAbilityItem());

        player.updateInventory();
        player.getOpenInventory().close();
        fighter.setCurrentKit(this);
        player.sendMessage(ColorHelper.translate("&7(&aKitPvP&7) &fYou have chosen the &a" + this.getName() + "&f kit."));
    }

    public abstract ItemStack[] getArmor();

    public abstract ItemStack getSword();

    public abstract ItemStack getAbilityItem();

    public abstract PotionEffect[] getPotionEffects();

    public abstract void interactEvent(PlayerInteractEvent event);

    public abstract void damageEvent(EntityDamageByEntityEvent event);
}

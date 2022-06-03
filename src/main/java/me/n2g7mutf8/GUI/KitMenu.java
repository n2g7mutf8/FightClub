package me.n2g7mutf8.GUI;

import me.lucko.helper.Events;
import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.scheme.MenuPopulator;
import me.lucko.helper.menu.scheme.MenuScheme;
import me.lucko.helper.terminable.Terminable;
import me.n2g7mutf8.Kits.Kit;
import me.n2g7mutf8.Kits.KitManager;
import me.n2g7mutf8.Utils.ColorHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class KitMenu extends Gui implements Listener {

    public KitMenu(Player player) {
        super(player, 3, "&a&lKit Selector");
    }

    @Override
    public void redraw() {
        List<Kit> kitList = new KitManager().getKits();
        MenuScheme scheme = new MenuScheme()
                .mask("111111111")
                .mask("110000011")
                .mask("111111111");
        MenuPopulator populator = scheme.newPopulator(this);

        Terminable event = Events.subscribe(InventoryClickEvent.class, EventPriority.HIGH)
                .handler(e -> {
                    if (e.getWhoClicked() instanceof Player) { return; }
                    if (e.getClickedInventory().getTitle() != this.getInitialTitle()) { return; }
                    Player player = (Player) e.getWhoClicked();

                    for (Kit kit : kitList) {
                        if (e.getCurrentItem().getType() == kit.getIcon()) {
                            kit.equipKit(player);
                        } else {
                            player.sendMessage(ColorHelper.translate("&7(&cError&7) &cYou cannot do that !"));
                            e.setCancelled(true);
                        }
                    }
                });

        for (int i = 0; i < 9; ++i) {
            populator.accept(ItemStackBuilder.of(Material.STAINED_GLASS_PANE).name("").data(7).buildItem().build());
        }
        populator.accept(ItemStackBuilder.of(Material.STAINED_GLASS_PANE).name("").data(7).buildItem().build());

        for (Kit kit : kitList) {
            populator.accept(ItemStackBuilder.of(kit.getIcon())
                    .name(kit.getName())
                    .lore(kit.getDesc())
                    .buildItem().build());
        }
        populator.accept(ItemStackBuilder.of(Material.STAINED_GLASS_PANE).name("").data(7).buildItem().build());

        for (int z = 0; z < 9; ++z) {
            populator.accept(ItemStackBuilder.of(Material.STAINED_GLASS_PANE).name("").data(7).buildItem().build());
        }

        bind(event);
    }
}

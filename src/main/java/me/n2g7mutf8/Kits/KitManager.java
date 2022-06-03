package me.n2g7mutf8.Kits;

import me.n2g7mutf8.Kits.List.*;

import java.util.ArrayList;
import java.util.List;

public class KitManager {

    private final List<Kit> kits = new ArrayList<>();

    public KitManager() {
        kits.add(new FighterKit());
        kits.add(new MedusaKit());
    }

    public Kit getKitByName(String name) {
        for (Kit kit : kits) {
            if (name.equals(kit.getName())) {
                return kit;
            }
        }

        return null;
    }

    public List<Kit> getKits() {
        return kits;
    }
}

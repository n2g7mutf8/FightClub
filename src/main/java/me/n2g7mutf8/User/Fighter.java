package me.n2g7mutf8.User;

import me.n2g7mutf8.Kits.Kit;

import java.util.HashMap;
import java.util.UUID;

public class Fighter {

    private UUID uuid;
    private int kills;
    private int deaths;
    private int killstreak;
    private int gold;
    private float KDR;
    private Kit currentKit;
    private HashMap<Kit, Integer> kitLevel;

    public Fighter (UUID uuid) {
        this.uuid = uuid;
        this.kills = 0;
        this.killstreak = 0;
        this.deaths = 0;
        this.gold = 0;
        this.KDR = 0.0F;
    }

    public int getKills() { return this.kills; }
    public void setKills(int amount) { this.kills = amount; }

    public int getDeaths() { return this.deaths; }
    public void setDeaths(int amount) { this.deaths = amount; }

    public int getKillstreak() { return this.killstreak; }
    public void setKillstreak(int amount) { this.killstreak = amount; }

    public int getGold() { return this.gold; }
    public void setGold(int amount) { this.gold = amount; }

    public float getKDR() { return (float) kills / deaths; }

    public Kit getCurrentKit() { return this.currentKit; }
    public void setCurrentKit(Kit kit) { this.currentKit = kit; }

    public void resetStats() {
        this.deaths = 0;
        this.kills = 0;
        this.gold = 0;
        this.KDR = 0.0F;
        this.killstreak = 0;
    }

    public HashMap<Kit, Integer> getKitLevel() {
        return this.kitLevel;
    }
    public void setKitLevel(Kit kit, Integer level) {
        if (this.kitLevel.get(kit) >= 6) { return; }
        if (level <= 1) { return; }

        if (!this.kitLevel.containsKey(kit)) {
            this.kitLevel.put(kit, 1);
        } else {
            this.kitLevel.replace(kit, level);
        }
    }
}

package me.n2g7mutf8.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ColorHelper {

    public final static String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------";
    public final static String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "-----------------------------------------------------";

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> lines) {
        List<String> strings = new ArrayList<>();
        for (String line : lines) {
            strings.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        return strings;
    }

    public static List<String> translate(String[] lines) {
        List<String> strings = new ArrayList<>();
        for (String line : lines) {
            if (line != null) {
                strings.add(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        return strings;
    }
}

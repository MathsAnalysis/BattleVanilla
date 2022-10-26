package dev.killergamerpls.vanilla.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtils {

    public static ItemStack blackStainedGlassPane() {
        return new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1, DyeColor.BLACK.getWoolData());
    }


    public static ItemStack woolPink(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.PINK.getWoolData()).setName(CC.translate("&d&l1")).get();
    }

    public static ItemStack woolLime(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.LIME.getWoolData()).setName(CC.translate("&a&l2")).get();
    }

    public static ItemStack woolLightBlue(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.LIGHT_BLUE.getWoolData()).setName(CC.translate("&b&l3")).get();
    }

    public static ItemStack woolOrange(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.ORANGE.getWoolData()).setName(CC.translate("&6&l4")).get();
    }

    public static ItemStack woolGreen(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.GREEN.getWoolData()).setName(CC.translate("&2&l5")).get();
    }

    public static ItemStack woolCyan(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.CYAN.getWoolData()).setName(CC.translate("&3&l6")).get();
    }

    public static ItemStack woolPurple(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.PURPLE.getWoolData()).setName(CC.translate("&5&l7")).get();
    }

    public static ItemStack woolLightGray(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.GRAY.getWoolData()).setName(CC.translate("&8&l8")).get();
    }

    public static ItemStack woolRed(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.RED.getWoolData()).setName(CC.translate("&4&l9")).get();
    }

    public static ItemStack woolBlack(){
        return new ItemBuilder(Material.LEGACY_WOOL, 1, DyeColor.BLACK.getWoolData()).setName(CC.translate("&0&l10")).get();
    }


    public static ItemStack defaultStainedGlassPane(){
        ItemStack itemStack = new ItemStack(Material.BLACK_STAINED_GLASS, 1, (byte)5);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setDisplayName(CC.translate("&a&lDefault"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }



    public static ItemStack getPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setPlayerProfile(player.getPlayerProfile());
        skull.setItemMeta(meta);
        return skull;
    }
}
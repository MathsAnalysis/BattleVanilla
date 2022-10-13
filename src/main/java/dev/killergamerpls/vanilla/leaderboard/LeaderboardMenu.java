package dev.killergamerpls.vanilla.leaderboard;

import dev.killergamerpls.vanilla.Vanilla;
import dev.killergamerpls.vanilla.utils.CC;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LeaderboardMenu implements InventoryProvider {

    private static SmartInventory INVENTORY;
    public static SmartInventory getInventory(Player player) {
        return INVENTORY = SmartInventory.builder()
                .id("CLASSIFICA")
                .provider(new LeaderboardMenu())
                .size(3, 9)
                .manager(Vanilla.get().getInventoryManager())
                .title("§8Classifica")
                .build();
    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        contents.fill(ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));

        ItemStack kills = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta killsMeta = kills.getItemMeta();
        killsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("kills"));
        killsMeta.setDisplayName(CC.translate("&4&lClassifica Uccisioni"));
        killsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        kills.setItemMeta(killsMeta);

        ItemStack deaths = new ItemStack(getPlayerSkull(player));
        ItemMeta deathsMeta = deaths.getItemMeta();
        deathsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("deaths"));
        deathsMeta.setDisplayName(CC.translate("&4&lClassifica Morti"));
        deathsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        deaths.setItemMeta(deathsMeta);

        ItemStack mobKills = new ItemStack(Material.ARROW);
        ItemMeta mobKillsMeta = mobKills.getItemMeta();
        mobKillsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("mobKills"));
        mobKillsMeta.setDisplayName(CC.translate("&4&lClassifica Mob Uccisi"));
        mobKillsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        mobKills.setItemMeta(mobKillsMeta);

        ItemStack blockBroken = new ItemStack(Material.COBBLESTONE);
        ItemMeta blockBrokenMeta = blockBroken.getItemMeta();
        blockBrokenMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("blockBroken"));
        blockBrokenMeta.setDisplayName(CC.translate("&4&lClassifica Blocchi Rotti"));
        blockBrokenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        blockBroken.setItemMeta(blockBrokenMeta);

        ItemStack goldenAppleEaten = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta goldenAppleEatenMeta = goldenAppleEaten.getItemMeta();
        goldenAppleEatenMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("goldenAppleEaten"));
        goldenAppleEatenMeta.setDisplayName(CC.translate("&4&lClassifica Mele Mangiate"));
        goldenAppleEatenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        goldenAppleEaten.setItemMeta(blockBrokenMeta);

        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));

        contents.set(new SlotPos(1, 2), ClickableItem.empty(goldenAppleEaten));
        contents.set(new SlotPos(1, 3), ClickableItem.empty(kills));
        contents.set(new SlotPos(1, 4), ClickableItem.empty(deaths));
        contents.set(new SlotPos(1, 5), ClickableItem.empty(mobKills));
        contents.set(new SlotPos(1, 6), ClickableItem.empty(blockBroken));

    }

    public static ItemStack getPlayerSkull(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        skull.setItemMeta(meta);
        return skull;
    }
}
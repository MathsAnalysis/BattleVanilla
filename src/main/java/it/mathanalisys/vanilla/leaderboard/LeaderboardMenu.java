package it.mathanalisys.vanilla.leaderboard;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import it.mathanalisys.vanilla.Vanilla;
import it.mathanalisys.vanilla.utils.CC;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class LeaderboardMenu implements InventoryProvider {

    public static SmartInventory getInventory(Player player) {
        return SmartInventory.builder()
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

        killsMeta.displayName(Component.text(CC.translate("&4&lClassifica Uccisioni")));
        killsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("kills"));
        killsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        kills.setItemMeta(killsMeta);

        ItemStack deaths = new ItemStack(getPlayerSkull(player));
        ItemMeta deathsMeta = deaths.getItemMeta();
        deathsMeta.displayName(Component.text(CC.translate("&5&lClassifica Morti")));
        deathsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("deaths"));
        deathsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        deaths.setItemMeta(deathsMeta);

        ItemStack mobKills = new ItemStack(Material.ARROW);
        ItemMeta mobKillsMeta = mobKills.getItemMeta();
        mobKillsMeta.displayName(Component.text(CC.translate("&5&lClassifica Mob Uccisi")));
        mobKillsMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("mobKills"));
        mobKillsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        mobKills.setItemMeta(mobKillsMeta);

        ItemStack blockBroken = new ItemStack(Material.COBBLESTONE);
        ItemMeta blockBrokenMeta = blockBroken.getItemMeta();
        blockBrokenMeta.displayName(Component.text(CC.translate("&5&lClassifica Blocchi Rotti")));
        blockBrokenMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("blockBroken"));
        blockBrokenMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        blockBroken.setItemMeta(blockBrokenMeta);

        ItemStack goldenAppleEaten = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta goldenAppleEatenMeta = goldenAppleEaten.getItemMeta();
        goldenAppleEatenMeta.displayName(Component.text(CC.translate("&5&lClassifica Mele Mangiate")));
        goldenAppleEatenMeta.setLore(Vanilla.get().getLeaderboardManager().getLeaderboards().get("goldenAppleEaten"));
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
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        skull.setItemMeta(meta);
        return skull;
    }
}
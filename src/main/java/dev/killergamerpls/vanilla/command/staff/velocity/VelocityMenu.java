package dev.killergamerpls.vanilla.command.staff.velocity;


import dev.killergamerpls.vanilla.Vanilla;
import dev.killergamerpls.vanilla.utils.CC;
import dev.killergamerpls.vanilla.utils.ItemUtils;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VelocityMenu implements InventoryProvider {

    private static SmartInventory INVENTORY;
    public static SmartInventory getInventory(Player player) {
        return INVENTORY = SmartInventory.builder()
                .id("VELOCITY")
                .provider(new VelocityMenu())
                .size(4, 9)
                .manager(Vanilla.get().getInventoryManager())
                .title("§8Fly Boost")
                .build();
    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        //contents.put(19, InvItem.fromCancelled(ItemUtils.blackStainedGlassPane()));
        //contents.put(20, InvItem.fromCancelled(ItemUtils.blackStainedGlassPane()));
        //contents.put(24, InvItem.fromCancelled(ItemUtils.blackStainedGlassPane()));
        // contents.put(25, InvItem.fromCancelled(ItemUtils.blackStainedGlassPane()));

        ItemStack itemstack = new ItemStack(Material.LEGACY_WATCH);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&dVelocità: &f" + player.getFlySpeed()));
        itemstack.setItemMeta(itemMeta);


        contents.fill(ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        contents.set(new SlotPos(0, 4), ClickableItem.empty(itemstack));

        contents.set(new SlotPos(1, 1), ClickableItem.of(ItemUtils.woolPink(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.1f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (1)"));
            }
        }));

        contents.set(new SlotPos(1, 2), ClickableItem.of(ItemUtils.woolLime(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.2f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (2)"));
            }
        }));

        contents.set(new SlotPos(1, 3), ClickableItem.of(ItemUtils.woolLightBlue(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.3f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (3)"));
            }
        }));

        contents.set(new SlotPos(1, 4), ClickableItem.of(ItemUtils.woolOrange(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.4f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (4)"));
            }
        }));

        contents.set(new SlotPos(1, 5), ClickableItem.of(ItemUtils.woolGreen(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.5f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (5)"));
            }
        }));

        contents.set(new SlotPos(1, 6), ClickableItem.of(ItemUtils.woolCyan(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.6f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (6)"));
            }
        }));

        contents.set(new SlotPos(1, 7), ClickableItem.of(ItemUtils.woolPurple(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.7f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (7)"));
            }
        }));

        contents.set(new SlotPos(2, 3), ClickableItem.of(ItemUtils.woolLightGray(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.8f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (8)"));
            }
        }));

        contents.set(new SlotPos(2, 4), ClickableItem.of(ItemUtils.woolRed(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(0.9f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (9)"));
            }
        }));

        contents.set(new SlotPos(2, 5), ClickableItem.of(ItemUtils.woolBlack(), e -> {
            if (e.getClick().isLeftClick()) {
                player.setFlySpeed(1f);
                player.sendMessage(CC.translate("&aHai impostato una velocità con la fly di (10)"));
            }
        }));
    }
}
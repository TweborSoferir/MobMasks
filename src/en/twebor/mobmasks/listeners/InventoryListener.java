package en.twebor.mobmasks.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals(ChatColor.DARK_GREEN + "MobMasks Help")) {
            ItemStack item = event.getCurrentItem();
            if (isHelpInventoryItem(item)) {
                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();

                String itemName = item.getItemMeta().getDisplayName();
                if (itemName.equals(ChatColor.BLUE + "Plugin Info")) {
                    player.sendMessage("Plugin information");
                } else if (itemName.equals(ChatColor.GOLD + "Commands")) {
                    player.sendMessage("Commands info");
                } else if (itemName.equals(ChatColor.GREEN + "Levelling Up")) {
                    player.sendMessage("Levelling up info");
                } else if (itemName.equals(ChatColor.DARK_PURPLE + "Mask Effects")) {
                    player.sendMessage("Mask effects info");
                }
            }
        }
    }

    private boolean isHelpInventoryItem(ItemStack item) {
        String[] helpItemNames = {
                ChatColor.BLUE + "Plugin Info",
                ChatColor.GOLD + "Commands",
                ChatColor.GREEN + "Levelling Up",
                ChatColor.DARK_PURPLE + "Mask Effects",
        };

        ItemMeta meta = item.getItemMeta();
        String displayName = meta.getDisplayName();

        for (String helpItemName : helpItemNames) {
            if (displayName.equals(helpItemName)) {
                return true;
            }
        }
        return false;
    }
}

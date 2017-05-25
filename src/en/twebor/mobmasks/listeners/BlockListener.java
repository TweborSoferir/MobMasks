package en.twebor.mobmasks.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();

        if (item.getItemMeta().hasDisplayName()) {
            if (item.getItemMeta().getDisplayName().equals("Chicken Mask")) {
                // Checks if right clicking against a cauldron.
                if (event.getBlockAgainst().getType() == Material.CAULDRON) {
                    event.setCancelled(true);
                    return;
                }

                event.setCancelled(true);
                if (event.getPlayer() != null) {
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot place mob masks!");
                }
            }
        }
    }
}

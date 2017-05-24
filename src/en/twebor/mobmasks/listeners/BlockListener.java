package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BlockListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack mainhand = event.getItemInHand();
        ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
        ItemMeta meta = mainhand.getItemMeta();
        String offhandName = offhand.getItemMeta().getDisplayName();
        Player player = event.getPlayer();
        int skullsForRankOne = 10; //Config value.  10 is temp.
        int skullsForRankTwo = 25; //Also config.
        int skullsForRankThree = 50;

        if (meta.hasDisplayName()) {
            if ((meta.getDisplayName().equals("MHF_Chicken's Head") || meta.getDisplayName().equals("Chicken Head")) &&
                    mainhand.getType() == Material.SKULL_ITEM) {
                // No item in offhand.  Set offhand to mask.
                if (offhand.getType() == Material.AIR || offhand.getType() == null) {
                    event.setCancelled(true);

                    List<String> lore = MaskUtils.generateFreshLore(skullsForRankOne);

                    int amount = mainhand.getAmount();
                    int rank = MaskUtils.getRank(amount, skullsForRankOne, skullsForRankTwo, skullsForRankThree);
                    //Generate new mask.  Then remove all skulls from main hand and add the count to the mask's lore.
                    player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                }

            } else if (meta.getDisplayName().equals("Chicken Mask") && mainhand.getType() == Material.SKULL_ITEM) {
                // Checks if right clicking against a cauldron.
                if (event.getBlockAgainst().getType() == Material.CAULDRON) {
                    event.setCancelled(true);
                    if ((offhandName.equals("MHF_Chicken's Head") || offhandName.equals("Chicken Head")) &&
                            offhand.getType() == Material.SKULL_ITEM) {
                        int amount = offhand.getAmount();
                        player.getInventory().setItemInOffHand(new ItemStack(Material.AIR)); //Might clear offhand.
                        if (!meta.hasLore()) {
                            List<String> newLore = MaskUtils.generateFreshLore(skullsForRankOne);
                        } else {
                            // call MaskUtils.updateLore once implemented.
                        }
                    }
                }

                event.setCancelled(true);
                if (event.getPlayer() != null) {
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot place mob masks!");
                }
            }
        }
    }




}


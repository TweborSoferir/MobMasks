package en.twebor.mobmasks.listeners;

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
        ItemStack item = event.getItemInHand();
        ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
        ItemMeta meta = item.getItemMeta();
        String offhandName = offhand.getItemMeta().getDisplayName();
        Player player = event.getPlayer();
        int minHeadsForRankOne = 10; //Config value.  10 is temp.
        int minHeadsForRankTwo = 50; //Also config.
        int minHeadsForRankThree = 100;
        int headsForMax = minHeadsForRankOne + minHeadsForRankTwo + minHeadsForRankThree;

        if (meta.hasDisplayName()) {
            if ((meta.getDisplayName().equals("MHF_Chicken's Head") || meta.getDisplayName().equals("Chicken Head")) &&
                    item.getType() == Material.SKULL_ITEM) {
                event.setCancelled(true);
                int rank;
                int amount = item.getAmount();
                List<String> lore;
                if (item.getAmount() < minHeadsForRankOne) {
                    return;
                } else if (amount < minHeadsForRankTwo + minHeadsForRankOne) {
                    //Adding because # of heads resets after rank-up.  So 60 heads would use 10 for R1, 50 for R2.
                    rank = 1; //Did not have enough to reach Rank 2.
                    amount -= minHeadsForRankOne;
                    lore = generateFreshLore(rank, amount, minHeadsForRankTwo);
                } else if (amount < headsForMax) {
                    rank = 2;
                    amount -= minHeadsForRankOne - minHeadsForRankTwo;
                    lore = generateFreshLore(rank, amount, minHeadsForRankThree);
                } else if (amount >= headsForMax) {
                    rank = 3;
                    lore = generateFreshLore(rank, amount, minHeadsForRankThree);
                }
            } else if (meta.getDisplayName().equals("Chicken Mask") && item.getType() == Material.SKULL_ITEM) {
                // Checks if right clicking against a cauldron.
                if (event.getBlockAgainst().getType() == Material.CAULDRON) {
                    event.setCancelled(true);
                    if ((offhandName.equals("MHF_Chicken's Head") || offhandName.equals("Chicken Head")) &&
                            offhand.getType() == Material.SKULL_ITEM) {
                        int amount = offhand.getAmount();
                        player.getInventory().setItemInOffHand(null); //Clears offhand if valid head and enough heads.
                        // Chicken Mask
                        //  Unbreakable
                        // Rank #
                        // # / # heads until next rank
                        if (!meta.hasLore()) {
                            List<String> newLore = new ArrayList<>();
                            newLore.add("Rank 1");
                            newLore.add("");
                        } else {

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

    private List<String> generateFreshLore(int rank, int skulls, int nextRankSkulls) {
        List<String> newLore = new ArrayList<>();
        newLore.add("Rank " + rank);
        if (rank < 3) {
            newLore.add("" + skulls + " / "+ nextRankSkulls + " heads for next rank");
        } else if (rank == 3) {
            newLore.add("Filler");
        }
        return newLore;
    }

    private List<String> updateLore(List<String> oldLore) {
        //String[] splitStr = str.split("\\s+");
        return null;
    }


}


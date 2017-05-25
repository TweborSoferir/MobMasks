package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteractListener implements Listener {
    private JavaPlugin plugin;

    public PlayerInteractListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Checks if cauldron is clicked on.
        if (event.getClickedBlock().getType() != Material.CAULDRON) return;
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        // Immediately cancels if mainhand is not a skull.
        if (mainhand == null || mainhand.getType() != Material.SKULL_ITEM) return;
        ItemStack offhand = player.getInventory().getItemInOffHand();
        //Immediately cancels if offhand is not empty or skull, as to get this far Mainhand is skull/mask.
        if (offhand.getType() != null && offhand.getType() != Material.SKULL_ITEM) return;
        SkullMeta mainMeta = (SkullMeta) mainhand.getItemMeta();


        int headsForTier1 = plugin.getConfig().getInt("Mask Levels.Tier 1");
        int headsForTier2 = plugin.getConfig().getInt("Mask Levels.Tier 2");
        int headsForTier3 = plugin.getConfig().getInt("Mask Levels.Tier 3");
        int[] tiers = {headsForTier1, headsForTier2, headsForTier3};

        String type = ""; //Used to pull description for lore

        if (!mainMeta.hasOwner()) {
            if (mainhand.getDurability() == SkullType.CREEPER.ordinal()) {
                //Creeper case
                type = "creeper";
            } else if (mainhand.getDurability() == SkullType.SKELETON.ordinal()) {
                //Skeleton case
                type = "skeleton";
            }
        } else if (mainMeta.hasOwner()) {
                switch (mainMeta.getOwner()) {
                    case "Horse Mask":
                        type = "horse";
                        break;
                    case "Rabbit Mask":
                        type = "rabbit";
                        break;
                    case "Blaze Mask":
                        type = "blaze";
                        break;
                    case "Chicken Mask":
                        type = "chicken";
                        break;
                    case "Iron Golem Mask":
                        type = "iron golem";
                        break;
                    default:
                        return;
                }
        }
        List<String> oldLore = new ArrayList<>();
        List<String> lore = new ArrayList<>();
        int amount;
        if (!mainMeta.hasLore()) { //This means that mainhand is a skull, not mask.
            amount = mainhand.getAmount(); //Making new mask.
            oldLore = MaskUtils.createNewLore(headsForTier1, type);
            lore = MaskUtils.updateLore(oldLore, amount, tiers);
            mainhand.setAmount(1); //Set to 1 as the last head becomes the new mask.
        } else {
            amount = offhand.getAmount(); //Mask will be in mainhand, and so only has to be updated.
            lore = MaskUtils.updateLore(mainMeta.getLore(), amount, tiers);
        }
        mainhand.getItemMeta().setLore(lore);
    }
}

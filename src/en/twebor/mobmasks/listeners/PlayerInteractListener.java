package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
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
        if (event.hasBlock()) {
            if (event.getClickedBlock().getType() != Material.CAULDRON) return;
        }
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        // Immediately cancels if mainhand is not a skull.
        if (mainhand == null || mainhand.getType() != Material.SKULL_ITEM) {
            return;
        }
        ItemStack offhand = player.getInventory().getItemInOffHand();
        //Immediately cancels if offhand is not empty or skull, as to get this far Mainhand is skull/mask.
        if (offhand.getType() != Material.AIR && offhand.getType() != Material.SKULL_ITEM) {
            return;
        }
        SkullMeta mainMeta = (SkullMeta) mainhand.getItemMeta();


        int headsForTier1 = plugin.getConfig().getInt("Mask Levels.Tier 1");
        int headsForTier2 = plugin.getConfig().getInt("Mask Levels.Tier 2");
        int headsForTier3 = plugin.getConfig().getInt("Mask Levels.Tier 3");
        int[] tiers = {headsForTier1, headsForTier2, headsForTier3};

        String type = ""; //Used to pull description for lore
        String name = ChatColor.AQUA + "";
        if (mainMeta.hasOwner()) {
                switch (mainMeta.getOwner()) {
                    case "gavertoso":
                        type = "horse";
                        name += "Horse Mask";
                        break;
                    case "Natalieisawesome":
                        type = "rabbit";
                        name += "Rabbit Mask";
                        break;
                    case "MHF_Blaze":
                        type = "blaze";
                        name += "Blaze Mask";
                        break;
                    case "MHF_Chicken":
                        type = "chicken";
                        name += "Chicken Mask";
                        break;
                    case "MHF_Golem":
                        type = "iron golem";
                        name += "Iron Golem Mask";
                        break;
                    default:
                        return;
                }
        } else if (!mainMeta.hasOwner()) {
            if (mainhand.getDurability() == SkullType.CREEPER.ordinal()) {
                //Creeper case
                type = "creeper";
                name += "Creeper Mask";
            } else if (mainhand.getDurability() == SkullType.SKELETON.ordinal()) {
                //Skeleton case
                type = "skeleton";
                name += "Skeleton Mask";
            }
        }
        List<String> oldLore = new ArrayList<>();
        List<String> lore = new ArrayList<>();
        int amount;
        if (!mainMeta.hasLore()) { //This means that mainhand is a skull, not mask.
            amount = mainhand.getAmount(); //Making new mask.
            mainhand.setAmount(1);
            oldLore = MaskUtils.createNewLore(headsForTier1, type);
            lore = MaskUtils.updateLore(oldLore, amount, tiers);
             //Set to 1 as the last head becomes the new mask.
        } else {
            amount = offhand.getAmount(); //Mask will be in mainhand, and so only has to be updated.
            lore = MaskUtils.updateLore(mainMeta.getLore(), amount, tiers);
            player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
        }
        mainMeta.setLore(lore);
        mainMeta.setDisplayName(name);
        mainhand.setItemMeta(mainMeta);

    }
}

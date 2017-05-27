package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.maskeffects.MaskBlaze;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerAnimationListener implements Listener { // Used for the Blaze effect.
    private JavaPlugin plugin;

    public PlayerAnimationListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerAnimation(PlayerInteractEvent event) {
        //if (!event.getAnimationType().equals(PlayerAnimationType.ARM_SWING)) {
        if (!event.getAction().equals(Action.LEFT_CLICK_AIR) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            // Cancels if animation is not an arm-swing.
            return;
        }

        Player player = event.getPlayer();
        ItemStack helm = player.getInventory().getHelmet();
        // Cancels if invalid mask.
        if (!isValidMask(helm)) {
            return;
        }
        MaskBlaze blazeEffect = new MaskBlaze(plugin, player);
        blazeEffect.launchFireball();
    }


    private boolean isValidMask(ItemStack helm) {
        if (helm == null) {
            return false;
        }
        if (helm.getType() != Material.SKULL_ITEM) {
            return false;
        // Next it checks the skulls owner and meta.
        } else {
            if (!helm.hasItemMeta()) {
                return false;
            } else if (helm.hasItemMeta()) {
                SkullMeta helmMeta = (SkullMeta) helm.getItemMeta();
                // Cancels if Skull worn does not have an owner, as then its not a Blaze head..
                if (!helmMeta.hasOwner()) {
                    return false;
                //Cancels if the head's owner does not match MHF_Blaze.
                } else {
                    if (!helmMeta.getOwner().equals("MHF_Blaze")) {
                        return false;
                    }
                }
                // Cancels if head does not have Lore.  Masks must have Lore.
                if (!helmMeta.hasLore()) {
                    return false;

                }
            }
        }
        return true;
    }
}

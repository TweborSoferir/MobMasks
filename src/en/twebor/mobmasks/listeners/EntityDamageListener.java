package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.utils.MaskEffectUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

// Used for the chicken Mask.
public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        // Cancels if the damage cause was not falling.
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        // Cancels if the entity was not a Player.
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helm = player.getInventory().getHelmet();
        // Cancels if mask is not a valid chicken mask.
        if (!MaskEffectUtils.isValidMask(helm, Mask.CHICKEN)) return;
    }
}

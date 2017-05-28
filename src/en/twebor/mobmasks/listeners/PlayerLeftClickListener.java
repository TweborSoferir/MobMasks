package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.maskeffects.MaskBlaze;
import en.twebor.mobmasks.utils.MaskEffectUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerLeftClickListener implements Listener { // Used for the Blaze effect.
    private JavaPlugin plugin;

    public PlayerLeftClickListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Cancels if player did not left click the air.
        if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) return;

        Player player = event.getPlayer();
        ItemStack helm = player.getInventory().getHelmet();
        // Cancels if invalid mask.
        if (!MaskEffectUtils.isValidMask(helm, Mask.BLAZE)) return;
        MaskBlaze blazeEffect = new MaskBlaze(plugin, player);
        blazeEffect.launchFireball();
    }
}

package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.maskeffects.MaskCreeper;
import en.twebor.mobmasks.utils.MaskEffectUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CreeperMaskDamageListener implements Listener {
    private int[] explosionChances;
    private int[] explosionPower;
    private boolean maskNullifiesReactiveExplosionDamage;
    private boolean blockDamage;

    public CreeperMaskDamageListener(JavaPlugin plugin) {
        // Creates an array containing the explosion chances.
        explosionChances = new int[]
                {plugin.getConfig().getInt("Creeper Mask.Explosion %.Tier 0"),
                plugin.getConfig().getInt("Creeper Mask.Explosion %.Tier 1"),
                plugin.getConfig().getInt("Creeper Mask.Explosion %.Tier 2"),
                plugin.getConfig().getInt("Creeper Mask.Explosion %.Tier 3")};
        explosionPower = new int[]
                {plugin.getConfig().getInt("Creeper Mask.Explosion Power.Tier 0"),
                plugin.getConfig().getInt("Creeper Mask.Explosion Power.Tier 1"),
                plugin.getConfig().getInt("Creeper Mask.Explosion Power.Tier 2"),
                plugin.getConfig().getInt("Creeper Mask.Explosion Power.Tier 3")};
        maskNullifiesReactiveExplosionDamage = plugin.getConfig().getBoolean(
                "Creeper Mask.Nullifies Damage From Other Creeper Masks");
        blockDamage = plugin.getConfig().getBoolean("Creeper Mask.Explosions Damage Blocks");
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        // Cancels if Entity that hit the player was a projectile.
        EntityType damagerType = event.getDamager().getType();
        if (damagerType == EntityType.ARROW || damagerType == EntityType.SNOWBALL ||
                damagerType == EntityType.SHULKER_BULLET || damagerType == EntityType.SPLASH_POTION ||
                damagerType == EntityType.EGG || damagerType == EntityType.FIREBALL ||
                damagerType == EntityType.TIPPED_ARROW || damagerType == EntityType.LLAMA ||
                damagerType == EntityType.FISHING_HOOK) {
            return;
        }
        // Cancels if entity damaged is not a player
        if (event.getEntity().getType() != EntityType.PLAYER) return;

        Player playerDamaged = (Player) event.getEntity();
        // Cancels if helmet is not a valid mask/
        ItemStack helm = playerDamaged.getInventory().getHelmet();

        if (!MaskEffectUtils.isValidMask(helm, Mask.CREEPER)) return;
        MaskCreeper creeperEffect = new MaskCreeper(playerDamaged, helm, explosionChances, explosionPower,
                blockDamage);
        creeperEffect.explode();
    }

    @EventHandler
    public void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
        if (!maskNullifiesReactiveExplosionDamage) {
            // If this boolean is false, then players take full damage from other creeper masks and themselves.
            return;
        } else {
            // Explosions generated via createExplosion have a damager of null.
            if (event.getDamager() == null) {
                // Checks if player.
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    Player player = (Player) event.getEntity();
                    // And then checks if the player has a valid mask equipped.
                    if (MaskEffectUtils.isValidMask(player.getInventory().getHelmet(), Mask.CREEPER)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}

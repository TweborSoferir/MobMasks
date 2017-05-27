package en.twebor.mobmasks.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityExplodeListener implements Listener { // Used for toggling of Blaze Mask block damage and fires.
    private boolean blockDamage;
    private boolean setsFires;

    public EntityExplodeListener(JavaPlugin plugin) {
        this.blockDamage = plugin.getConfig().getBoolean("Blaze Mask.Block Damage");
        this.setsFires = plugin.getConfig().getBoolean("Blaze Mask.Sets Fires");
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (blockDamage) {
            // If block damage is enabled, this Listener is canceled immediately, as fireballs already do BD.
            return;
        }
        if (entity.getType() != EntityType.FIREBALL) {
            // Only care about the fireballs.
            return;
        }

        // Casts the entity as a fireball, since if non-fireballs would fail the EntityType check.
        Fireball fireball = (Fireball) entity;
        if (!(fireball.getShooter() instanceof Player)) {
            // Cancels if fireball was not fired by a player.
            return;
        }
        // Cancelling the event and creating our own fireball.
        float yield = ((Fireball) entity).getYield();
        Location loc = event.getLocation();
        event.setCancelled(true);
        loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), yield, setsFires, blockDamage);
    }
}

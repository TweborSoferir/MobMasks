package en.twebor.mobmasks.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityShootBowListener implements Listener {
    private int[] bonusArrows;
    private int[] bonusArrowsChance;

    public EntityShootBowListener(JavaPlugin plugin) {
        this.bonusArrows = new int[] {
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows.Tier 0"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows.Tier 1"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows.Tier 2"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows.Tier 3")};
        this.bonusArrowsChance = new int[] {
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows %.Tier 0"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows %.Tier 1"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows %.Tier 2"),
                plugin.getConfig().getInt("Skeleton Mask.Bonus Arrows %.Tier 3"),};
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        // Entity is null if the arrows were spawned by the mask effect.
        if (entity == null) {
            return;
        }
        // Cancels if Entity that fired the arrow was not a player.
        if (entity.getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) entity;
    }
}

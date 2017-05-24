package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MobUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType type = event.getEntityType();
        if (MobUtil.isValidMob(type)) {
            List<ItemStack> drops = new ArrayList<>();

            // Adds the mob's head to the drop pool.
            drops.add(MobUtil.getSkullDrop(type));

            event.getDrops().addAll(drops);
        }
    }
}

package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MobUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityListener implements Listener {
    private JavaPlugin plugin;

    public EntityListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType type = event.getEntityType();
        if (MobUtil.isValidMob(type)) {
            double dropPercent = plugin.getConfig().getInt("Items.Head Drop %");
            Random random = new Random();
            if (random.nextInt(100)+1 <= dropPercent) {
                List<ItemStack> drops = new ArrayList<>();

                // Adds the mob's head to the drop pool.
                drops.add(MobUtil.getSkullDrop(type));

                event.getDrops().addAll(drops);
            }
        }
    }
}

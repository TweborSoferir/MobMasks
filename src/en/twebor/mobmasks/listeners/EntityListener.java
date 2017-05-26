package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.utils.MobUtils;
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
    private List<EntityType> enabledMobs;

    public EntityListener(JavaPlugin plugin, List<EntityType> enabledMobs) {
        this.plugin = plugin;
        this.enabledMobs = enabledMobs;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType type = event.getEntityType();
        if (MobUtils.isValidMob(type)) {
            if (isMobEnabled(type)) {
                double dropPercent = plugin.getConfig().getInt("Items.Head Drop %");
                Random random = new Random();

                // Rolls a random number from 1-100 and compares it to the config drop percentage.
                if (random.nextInt(100) + 1 <= dropPercent) {
                    List<ItemStack> drops = new ArrayList<>();

                    // Adds the mob's head to the drop pool.
                    drops.add(MobUtils.getSkullDrop(type));

                    event.getDrops().addAll(drops);
                }
            }
        }
    }

    private boolean isMobEnabled(EntityType type) {
        for (EntityType mob : enabledMobs) {
            if (mob == type) {
                return true;
            }
        }
        return false;
    }
}

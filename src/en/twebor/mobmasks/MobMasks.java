package en.twebor.mobmasks;

import en.twebor.mobmasks.commands.CommandMobMasks;
import en.twebor.mobmasks.commands.CommandInfo;
import en.twebor.mobmasks.commands.CommandSkull;
import en.twebor.mobmasks.listeners.BlockListener;
import en.twebor.mobmasks.listeners.EntityListener;
import en.twebor.mobmasks.listeners.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobMasks extends JavaPlugin {
    private FileConfiguration config = getConfig();
    private List<EntityType> enabledMobs = new ArrayList<>();
  
    @Override
    public void onEnable() {
        createConfig();
        createEnabledMobList();
        registerEvents();
        registerCommands();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new EntityListener(this, this.enabledMobs), this);
    }

    public void registerCommands() {
        this.getCommand("mobmasks").setExecutor(new CommandMobMasks());
        this.getCommand("info").setExecutor(new CommandInfo());
        this.getCommand("skull").setExecutor(new CommandSkull(this));
    }

    private void createConfig() {
        config.addDefault("Items.Head Drop %", 100);
        String[] mobs = {"Chicken", "Iron Golem", "Blaze", "Rabbit", "Horse", "Skeleton", "Creeper"};
        List<String> mobList = new ArrayList<>();

        for (String mob : mobs) {
            mobList.add(mob + " : true");
        }

        config.addDefault("Mobs", mobList);

        config.addDefault("Mask Levels.Tier 1", 100);
        config.addDefault("Mask Levels.Tier 2", 200);
        config.addDefault("Mask Levels.Tier 3", 300);

        config.options().copyDefaults(true);
        saveConfig();
    }

    private void createEnabledMobList() {
        Map<String, Boolean> mobConfigList = new HashMap<>();

        for (String line : config.getStringList("Mobs")) {
            String[] args = line.split(":");
            mobConfigList.put(args[0].trim(), Boolean.parseBoolean(args[1].trim()));
        }

        for (String key : mobConfigList.keySet()) {
            if (mobConfigList.get(key)) {
                switch (key) {
                    case "Chicken":
                        enabledMobs.add(EntityType.CHICKEN);
                        break;
                    case "Iron Golem":
                        enabledMobs.add(EntityType.IRON_GOLEM);
                        break;
                    case "Blaze":
                        enabledMobs.add(EntityType.BLAZE);
                        break;
                    case "Rabbit":
                        enabledMobs.add(EntityType.RABBIT);
                        break;
                    case "Horse":
                        enabledMobs.add(EntityType.RABBIT);
                        break;
                    case "Skeleton":
                        enabledMobs.add(EntityType.SKELETON);
                        break;
                    case "Creeper":
                        enabledMobs.add(EntityType.CREEPER);
                        break;
                }
            }
        }
    }
}

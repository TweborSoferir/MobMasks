package en.twebor.mobmasks;

import en.twebor.mobmasks.commands.CommandMobMasks;
import en.twebor.mobmasks.commands.CommandInfo;
import en.twebor.mobmasks.commands.CommandSkull;
import en.twebor.mobmasks.listeners.*;
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
        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new EntityListener(this, this.enabledMobs), this);
        pm.registerEvents(new CreeperMaskDamageListener(this), this);
        pm.registerEvents(new EntityShootBowListener(this), this);
        pm.registerEvents(new PlayerLeftClickListener(this), this);
        pm.registerEvents(new EntityExplodeListener(this), this);
        pm.registerEvents(new EntityDamageListener(this), this);
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

        config.addDefault("Creeper Mask.Explosion %.Tier 0", 0);
        config.addDefault("Creeper Mask.Explosion %.Tier 1", 10);
        config.addDefault("Creeper Mask.Explosion %.Tier 2", 20);
        config.addDefault("Creeper Mask.Explosion %.Tier 3", 30);
        config.addDefault("Creeper Mask.Explosion Power.Tier 0", 0);
        config.addDefault("Creeper Mask.Explosion Power.Tier 1", 2);
        config.addDefault("Creeper Mask.Explosion Power.Tier 2", 3);
        config.addDefault("Creeper Mask.Explosion Power.Tier 3", 4);
        config.addDefault("Creeper Mask.Nullifies Damage From Other Creeper Masks", true);
        config.addDefault("Creeper Mask.Explosions Damage Blocks", false);

        config.addDefault("Skeleton Mask.Bonus Arrows.Tier 0", 0);
        config.addDefault("Skeleton Mask.Bonus Arrows.Tier 1", 3);
        config.addDefault("Skeleton Mask.Bonus Arrows.Tier 2", 3);
        config.addDefault("Skeleton Mask.Bonus Arrows.Tier 3", 3);
        config.addDefault("Skeleton Mask.Bonus Arrows %.Tier 0", 0);
        config.addDefault("Skeleton Mask.Bonus Arrows %.Tier 1", 20);
        config.addDefault("Skeleton Mask.Bonus Arrows %.Tier 2", 40);
        config.addDefault("Skeleton Mask.Bonus Arrows %.Tier 3", 60);
        config.addDefault("Skeleton Mask.Bonus Arrow Velocity", 2.5);
        config.addDefault("Skeleton Mask.Bonus Arrows Spread.Tier 0", 0);
        config.addDefault("Skeleton Mask.Bonus Arrows Spread.Tier 1", 5);
        config.addDefault("Skeleton Mask.Bonus Arrows Spread.Tier 2", 4);
        config.addDefault("Skeleton Mask.Bonus Arrows Spread.Tier 3", 3);

        config.addDefault("Blaze Mask.Fireball Chance.Tier 0", 0);
        config.addDefault("Blaze Mask.Fireball Chance.Tier 1", 3);
        config.addDefault("Blaze Mask.Fireball Chance.Tier 2", 6);
        config.addDefault("Blaze Mask.Fireball Chance.Tier 3", 10);
        config.addDefault("Blaze Mask.Fireball Power.Tier 0", 3F);
        config.addDefault("Blaze Mask.Fireball Power.Tier 1", 3F);
        config.addDefault("Blaze Mask.Fireball Power.Tier 2", 3F);
        config.addDefault("Blaze Mask.Fireball Power.Tier 3", 3F);
        config.addDefault("Blaze Mask.Block Damage", false);
        config.addDefault("Blaze Mask.Sets Fires" , true);

        config.addDefault("Chicken Mask.Fall Damage Reduction %.Tier 0", 0);
        config.addDefault("Chicken Mask.Fall Damage Reduction %.Tier 1", 50);
        config.addDefault("Chicken Mask.Fall Damage Reduction %.Tier 2", 75);
        config.addDefault("Chicken Mask.Fall Damage Reduction %.Tier 3", 100);

        config.addDefault("Iron Golem Mask.Damage Reduction %.Tier 0", 0);
        config.addDefault("Iron Golem Mask.Damage Reduction %.Tier 1", 10);
        config.addDefault("Iron Golem Mask.Damage Reduction %.Tier 2", 20);
        config.addDefault("Iron Golem Mask.Damage Reduction %.Tier 3", 30);


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

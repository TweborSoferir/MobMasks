package en.twebor.mobmasks;

import en.twebor.mobmasks.commands.CommandInfo;
import en.twebor.mobmasks.commands.CommandSkull;
import en.twebor.mobmasks.listeners.BlockListener;
import en.twebor.mobmasks.listeners.EntityListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MobMasks extends JavaPlugin {
    private FileConfiguration config = getConfig();
  
    @Override
    public void onEnable() {
        createConfig();
        saveConfig();
        registerEvents();
        registerCommands();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockListener(), this);
        pm.registerEvents(new EntityListener(this), this);
    }

    public void registerCommands() {
        this.getCommand("info").setExecutor(new CommandInfo());
        this.getCommand("skull").setExecutor(new CommandSkull(this));
    }

    private void createConfig() {
        config.addDefault("Items.Head Drop %", 75);

        config.addDefault("Mobs.Chicken", true);
        config.addDefault("Mobs.Iron Golem", true);
        config.addDefault("Mobs.Blaze", true);
        config.addDefault("Mobs.Rabbit", true);
        config.addDefault("Mobs.Horse", true);
        config.addDefault("Mobs.Skeleton", true);
        config.addDefault("Mobs.Creeper", true);

        config.addDefault("Mask Levels.Tier 1", 100);
        config.addDefault("Mask Levels.Tier 2", 200);
        config.addDefault("Mask Levels.Tier 3", 300);

        config.options().copyDefaults(true);
    }
}

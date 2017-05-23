package en.twebor.mobmasks;

import en.twebor.mobmasks.commands.CommandInfo;
import en.twebor.mobmasks.commands.CommandSkull;
import en.twebor.mobmasks.listeners.BlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public class MobMasks extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
        this.getCommand("info").setExecutor(new CommandInfo());
        this.getCommand("skull").setExecutor(new CommandSkull());
    }
}

package en.twebor.mobmasks.maskeffects;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MaskGeneral { //All other masks extend from this.
    private int headsForTier1;
    private int headsForTier2;
    private int headsForTier3;

    public MaskGeneral(JavaPlugin plugin, PlayerInteractEvent event) {
        int headsForTier1 = plugin.getConfig().getInt("Mask Levels.Tier 1");
        int headsForTier2 = plugin.getConfig().getInt("Mask Levels.Tier 2");
        int headsForTier3 = plugin.getConfig().getInt("Mask Levels.Tier 3");
    }
}

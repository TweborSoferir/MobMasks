package en.twebor.mobmasks.maskeffects;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class MaskSkeleton {
    private int[] bonusArrows;
    private int[] bonusArrowsChance;
    private float[] bonusArrowSpread;
    private float arrowSpeed;
    private Player player;

    public MaskSkeleton(Player player,int[] bonusArrows, int[] bonusArrowsChance, float[] bonusArrowSpread,
                        float arrowSpeed) {
        this.bonusArrows = bonusArrows;
        this.bonusArrowsChance = bonusArrowsChance;
        this.bonusArrowSpread = bonusArrowSpread;
        this.arrowSpeed = arrowSpeed;
        this.player = player;
    }

    public void multishot() {
        // helmet has already been checked to be valid in the isValidMask method in the listener.
        ItemStack helmet = player.getInventory().getHelmet();
        // If helmet does not have Meta, cancels.
        if (!helmet.hasItemMeta()) {
            return;
        }
        SkullMeta helmMeta = (SkullMeta) helmet.getItemMeta();
        if (MaskUtils.willTrigger(helmMeta, bonusArrowsChance)) {
            Location eyeLoc = player.getEyeLocation();
            World world = eyeLoc.getWorld();
            Vector direction = eyeLoc.getDirection();
            Location newLoc = eyeLoc.add(direction);
            for (int i = 0; i < bonusArrows[MaskUtils.getTier(helmMeta)] ; i++) {
                // Prevents arrows from spawning within each other.
                newLoc = newLoc.add(0, 0.1, 0);
                Arrow arrow = world.spawnArrow(newLoc, direction, arrowSpeed,
                        bonusArrowSpread[MaskUtils.getTier(helmMeta)]);
                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                arrow.setShooter(player);
            }
        }
    }
}

package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.maskeffects.MaskSkeleton;
import en.twebor.mobmasks.utils.MaskEffectUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityShootBowListener implements Listener {
    private int[] bonusArrows;
    private int[] bonusArrowsChance;
    private float[] bonusArrowSpread;
    private float bonusArrowVelocity;

    // Used for the Skeleton Mask.
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
        this.bonusArrowSpread = new float[] {
                (float) plugin.getConfig().getDouble("Skeleton Mask.Bonus Arrows Spread.Tier 0"),
                (float) plugin.getConfig().getDouble("Skeleton Mask.Bonus Arrows Spread.Tier 1"),
                (float) plugin.getConfig().getDouble("Skeleton Mask.Bonus Arrows Spread.Tier 2"),
                (float) plugin.getConfig().getDouble("Skeleton Mask.Bonus Arrows Spread.Tier 3"),};
        this.bonusArrowVelocity = (float) plugin.getConfig().getDouble("Skeleton Mask.Bonus Arrow Velocity");
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        // Entity is null if the arrows were spawned by the mask effect.
        if (entity == null) return;
        // Cancels if Entity that fired the arrow was not a player.
        if (entity.getType() != EntityType.PLAYER) return;

        Player player = (Player) entity;
        ItemStack helm = player.getInventory().getHelmet();
        // If invalid Mask, cancels.
        if (!MaskEffectUtils.isValidMask(helm, Mask.SKELETON)) return;
        Float arrowSpeed = bonusArrowVelocity;

        // If not fully drawn, speed is based on draw force, to prevent low draw shots from having full speed.
        if (event.getForce() < .9) {
            arrowSpeed = event.getForce() * 2;
        }
        MaskSkeleton skeletonEffect = new MaskSkeleton(player, bonusArrows, bonusArrowsChance, bonusArrowSpread,
                arrowSpeed);
        skeletonEffect.multishot();
    }
}

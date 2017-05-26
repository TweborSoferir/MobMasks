package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.maskeffects.MaskSkeleton;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityShootBowListener implements Listener {
    private int[] bonusArrows;
    private int[] bonusArrowsChance;
    private float[] bonusArrowSpread;
    private float bonusArrowVelocity;

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
        if (entity == null) {
            return;
        }
        // Cancels if Entity that fired the arrow was not a player.
        if (entity.getType() != EntityType.PLAYER) {
            return;
        }

        Player player = (Player) entity;
        ItemStack helm = player.getInventory().getHelmet();
        // If invalid Mask, cancels.
        if (!isValidMask(helm)) {
            return;
        }
        Float arrowSpeed = bonusArrowVelocity;

        // If not fully drawn, speed is based on draw force, to prevent spamming of low draw shots.
        if (event.getForce() < .9) {
            arrowSpeed = event.getForce() * 2;
        }
        MaskSkeleton skeletonEffect = new MaskSkeleton(player, bonusArrows, bonusArrowsChance, bonusArrowSpread,
                arrowSpeed);
        skeletonEffect.multishot();
    }

    private boolean isValidMask(ItemStack helm) {
        if (helm == null) {
            return false;
        }
        if (helm.getType() != Material.SKULL_ITEM) {
            return false;
            // Next it checks the skulls owner and meta.
        } else {
            if (helm.hasItemMeta()) {
                SkullMeta helmMeta = (SkullMeta) helm.getItemMeta();
                // Cancels if Skull worn has an owner, as then its not a Skeleton skull.
                if (helmMeta.hasOwner()) {
                    return false;
                    //Cancels if the head's durability does not match the Skeleton ordinal.
                } else {
                    if (helm.getDurability() != SkullType.SKELETON.ordinal()) {
                        return false;
                    }
                }
                // Cancels if head does not have Lore.  Masks must have Lore.
                if (!helmMeta.hasLore()) {
                    return false;
                }
            }
        }
        return true;
    }
}

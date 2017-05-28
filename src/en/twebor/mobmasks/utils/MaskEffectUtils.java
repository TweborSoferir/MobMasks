package en.twebor.mobmasks.utils;

import en.twebor.mobmasks.Mask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

public class MaskEffectUtils {

    private MaskEffectUtils() {}

    // Checks if it is the correct Mask Type.
    public static boolean isValidMask(ItemStack helm, Mask mask) {
        EntityType type = mask.getType();
        boolean hasOwner;
        int ordinal = -1;
        // Checks if the Mask type given has an owner.
        if (type == EntityType.CREEPER) {
            hasOwner = false;
            ordinal = SkullType.CREEPER.ordinal();
        } else if (type == EntityType.SKELETON) {
            hasOwner = false;
            ordinal = SkullType.SKELETON.ordinal();
        } else hasOwner = true;
        // Cancels if the helm does not exist.
        if (helm == null) return false;
        // Cancels if helm is not a skull.
        if (helm.getType() != Material.SKULL_ITEM) return false;
        // Next it checks the skulls owner and meta.
        if (helm.hasItemMeta()) {
            SkullMeta helmMeta = (SkullMeta) helm.getItemMeta();
            // If the helm does not have Lore, than it is not a valid mask.
            if (!helmMeta.hasLore()) return false;
            // If mask type is not a Creeper or Skeleton, it must have an owner to be a valid mask.
            if (helmMeta.hasOwner() && hasOwner) {
                // Checks if the skin owners of the masks are the same.
                if (helmMeta.getOwner().equals(MaskUtils.getOwner(mask))) return true;
                // Skeleton and Creeper masks have no owner.
            } else if (!helmMeta.hasOwner() && !hasOwner) {
                // Compares the mask durability to the ordinal of the EntityType.
                if (helm.getDurability() == ordinal) return true;
                // Mask type and the checked helmet do not have the same owner or lack of owner.
            } else return false;
        }
        // Has failed the hasItemMeta check.
        return false;
    }

    // Returns a boolean to decide if the effect will occur.
    public static boolean willTriggerMaskEffect(SkullMeta helmetMeta, int[] effectChances) {
        int tier = MaskUtils.getTier(helmetMeta);
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        return (number <= effectChances[tier]);
    }
}

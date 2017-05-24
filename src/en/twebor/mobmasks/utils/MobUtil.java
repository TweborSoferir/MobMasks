package en.twebor.mobmasks.utils;

import en.twebor.mobmasks.Mask;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class MobUtil {
    // Empty constructor, so can't be instantiated.
    private MobUtil(){}

    public static boolean isValidMob(EntityType mob) {
        for (Mask mask : Mask.values()) {
            if (mask.getType() == mob) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getSkullDrop(EntityType type) {
        for (Mask mask : Mask.values()) {
            if (type == mask.getType()) {
                return mask.getSkull();
            }
        }
        return null;
    }

    public static void setMaskMeta(ItemStack skull, String displayName) {
        // For use with skulls already in MC. (Skeleton, Creeper).
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setDisplayName(displayName);
        skullMeta.setUnbreakable(true);
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        skull.setItemMeta(skullMeta);
    }

    // TODO: 5/24/2017 Set the names of these static methods to something more general. 
    public static void setMaskMeta(ItemStack skull, String displayName, String owner) {
        // For use with player skulls for mobs.
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(owner);
        skullMeta.setDisplayName(displayName);
        skullMeta.setUnbreakable(true);
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        skull.setItemMeta(skullMeta);
    }
}

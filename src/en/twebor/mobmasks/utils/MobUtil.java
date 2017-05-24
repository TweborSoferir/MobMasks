package en.twebor.mobmasks.utils;

import en.twebor.mobmasks.Mask;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

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
}

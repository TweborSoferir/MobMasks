package en.twebor.mobmasks.utils;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtil {
    private ItemUtil() {
    }

    public static void setSkullName(ItemStack skull, String displayName) {
        setSkullNameAndOwner(skull, displayName, "");
    }

    public static void setSkullNameAndOwner(ItemStack skull, String displayName, String owner) {
        // For use with player skulls for mobs.
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        if (!owner.isEmpty())
            skullMeta.setOwner(owner);
        skullMeta.setDisplayName(displayName);
        skullMeta.setUnbreakable(true);
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        skull.setItemMeta(skullMeta);
    }
}

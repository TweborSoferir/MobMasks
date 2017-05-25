package en.twebor.mobmasks.utils;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtils {
    // Empty constructor, so can't be instantiated.
    private ItemUtils() {}

    public static void setItemName(ItemStack item, String name) {
        // Sets the display name of an item.
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    public static void addAllHideItemFlags(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
    }

    public static void setSkullName(ItemStack skull, String displayName) {

        setSkullNameAndOwner(skull, displayName, "NO OWNER");
    }

    public static void setSkullNameAndOwner(ItemStack skull, String displayName, String owner) {
        // For use with player skulls for mobs.
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        skullMeta.setOwner(owner);
        skullMeta.setDisplayName(displayName);
        skullMeta.setUnbreakable(true);
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        skull.setItemMeta(skullMeta);
    }
}

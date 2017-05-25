package en.twebor.mobmasks.utils;

import en.twebor.mobmasks.Mask;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MaskUtils {

    private MaskUtils() {}

    public static int getRank(int amount, int rankOneSkulls, int rankTwoSkulls, int rankThreeSkulls) {
        if (amount < rankOneSkulls) {
            return 0; //Not enough heads.
        } else if (amount < rankTwoSkulls) {
            return 1; //Did not have enough to reach Rank 2,but enough to pass rank 1.
        } else if (amount < rankThreeSkulls) {
            return 2;
        } else if (amount >= rankThreeSkulls) {
            return 3;
        }
        return 0;
    }

    public static List<String> generateFreshLore(int skullsForRank1) {
        List<String> newLore = new ArrayList<>();
        //TODO  Create descriptions for each mask to be default first few lines.
        //newLore.add(maskType.getDescription();
        newLore.add("Rank 0");
        newLore.add("0 / " + skullsForRank1 + " skulls for next rank");
        return newLore;
    }

    //TODO Implement this.
    public static List<String> updateLore(List<String> oldLore) {
        //String[] splitStr = str.split("\\s+");
        return null;
    }

    public static Mask getMask(ItemStack item) {
        /* Only works for skulls.
         * Returns if an item matches a mask, otherwise it will return null.
         * This compares the owners so it will prevent players from naming masks themselves to be used.
         **/
        if (item.getType() != Material.SKULL_ITEM) {
            return null;
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();

        for (Mask mask : Mask.values()) {
            SkullMeta maskMeta = (SkullMeta) mask.getMask().getItemMeta();

            if (meta.getOwner().equals("NO OWNER")) {
                if (maskMeta.getDisplayName().equals(meta.getDisplayName())) {
                    return mask;
                }
            } else {
                if (maskMeta.getOwner().equals(meta.getOwner())) {
                    return mask;
                }
            }
        }
        return null;
    }

}

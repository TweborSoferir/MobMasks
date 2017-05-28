package en.twebor.mobmasks.utils;

import en.twebor.mobmasks.Mask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaskUtils {

    private MaskUtils() {
    }

    public static List<String> createNewLore(int skullsForRank1, String type) {
        List<String> newLore = new ArrayList<>();
        //TODO  Create descriptions for each mask to be default first few lines.
        //newLore.add(maskType.getDescription();
        newLore.add(getDescription(type));
        newLore.add("Rank 0");
        newLore.add("0 / " + skullsForRank1 + " skulls"); // "0 / 100 skulls"
        return newLore;
    }

    public static List<String> updateLore(List<String> oldLore, int skullsAdded, int[] tiers) {
        List<String> newLore = new ArrayList<>();
        newLore.add(oldLore.get(0)); //Description remains constant.
        String[] splitStrRank = oldLore.get(1).split("\\s+"); //Splits rank by space.  Rank is index 1.
        int rank = Integer.parseInt(splitStrRank[1]);
        String[] splitStrSkulls = oldLore.get(2).split("\\s+"); //Skull Amt should be 0, Ind 2 is current goal.
        int amount = Integer.parseInt(splitStrSkulls[0]);
        int goal;
        int nextGoal;
        if (rank < 3) {
            goal = Integer.parseInt(splitStrSkulls[2]);
            if (amount + skullsAdded >= goal) { //T1. Ind 0, T2, Ind 1, T3, ind 2
                nextGoal = tiers[rank];
                rank++; //Increments Rank by one and update the next goal.
            } else { //Not enough skulls to reach next rank;
                nextGoal = goal;
            }
        } else {/*Rank >= 3  Does nothing.  Amt incremented outside of if/else */
            goal = -1;
            nextGoal = -1;
        }
        amount += skullsAdded; //Increases regardless of results in if/else
        newLore.add("Rank " + rank);
        if (rank < 3) {
            newLore.add(amount + " / " + nextGoal + " skulls");
        } else {
            newLore.add(amount + " skulls");
        }
        return newLore;
    }

    private static String getDescription(String key) {
        switch (key) {
            case "horse":
                return "Speed";
            case "chicken":
                return "No fall damage";
            case "iron golem":
                return "Reduced damage taken";
            case "rabbit":
                return "Jump boost";
            case "blaze":
                return "Chance to launch fireballs after attacks";
            case "skeleton":
                return "Chance to fire additional arrows";
            case "creeper":
                return "Chance to explode when damaged";
            default:
                return "No effect";
        }
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

            if (meta.getOwner().equals("NO OWNER") || !meta.hasOwner()) {
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

    // Returns Mask Rank.
    public static int getTier(SkullMeta helmetMeta) {
        if (helmetMeta.hasLore()) {
            List<String> lore = helmetMeta.getLore();
            String[] splitStrRank = lore.get(1).split("\\s+"); //Splits rank by space.  Rank is index 1.
            int rank = Integer.parseInt(splitStrRank[1]);
            return rank;
        } else {
            return 0;
        }
    }

    // Returns the skin owner of the given mask.
    public static String getOwner(Mask mask) {
        EntityType type = mask.getType();
        String name = type.name();
        switch (name) {
            case "CREEPER":
                return "NO OWNER";
            case "SKELETON":
                return "NO OWNER";
            case "BLAZE":
                return "MHF_Blaze";
            case "CHICKEN":
                return "MHF_Chicken";
            case "IRON_GOLEM":
                return "MHF_Golem";
            case "HORSE":
                return "gavertoso";
            case "RABBIT":
                return "Natalieisawesome";
            default:
                return "Invalid Mask";
        }
    }
}

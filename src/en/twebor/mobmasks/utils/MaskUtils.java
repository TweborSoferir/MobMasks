package en.twebor.mobmasks.utils;

import java.util.ArrayList;
import java.util.List;

public class MaskUtils {

    private MaskUtils() {}

    public static int getRank(int amount, int[] tiers) {
        if (amount < tiers[0]) {
            return 0; //Not enough heads.
        } else if (amount < tiers[1]) {
            return 1; //Did not have enough to reach Rank 2,but enough to pass rank 1.
        } else if (amount < tiers[2]) {
            return 2;
        } else if (amount >= tiers[2]) {
            return 3;
        }
        return 0;
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

    //TODO Implement this.
    public static List<String> updateLore(List<String> oldLore, int skullsAdded,int[] tiers) {
        List<String> newLore = new ArrayList<>();
        newLore.add(oldLore.get(0)); //Description remains constant.
        String[] splitStrRank = oldLore.get(1).split("\\s+"); //Splits rank by space.  Rank is index 1.
        int rank = Integer.parseInt(splitStrRank[1]);
        String[] splitStrSkulls = oldLore.get(2).split("\\s+"); //Skull Amt should be 0, Ind 2 is current goal.
        int amount = Integer.parseInt(splitStrSkulls[0]);
        int goal = Integer.parseInt(splitStrSkulls[2]);
        int nextGoal = goal; //Changed to next tier's goal if enough skulls.  Else it stays at goal.
        if (rank < 3) {
            if (amount + skullsAdded >= goal) { //T1. Ind 0, T2, Ind 1, T3, ind 2
                nextGoal = tiers[rank];
                rank++; //Increments Rank by one and update the next goal.
            } else { /*Rank >= 3  Does nothing.  Amt incremented outside of if/else */}
        }
        amount += skullsAdded; //Increases regardless of results in if/else
        newLore.add("Rank " + rank);
        if (rank <3) {
            newLore.add(amount + " / " + nextGoal + " skulls");
        } else {
            newLore.add(amount + "skulls");
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
                return "Chance to fire fireballs after attacks";
            case "skeleton":
                return "Chance to fire additional arrows";
            case "creeper":
                return "Chance to explode when damaged";
            default:
                return "No effect";
        }
    }
}

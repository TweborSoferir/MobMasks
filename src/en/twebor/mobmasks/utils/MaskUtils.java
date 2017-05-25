package en.twebor.mobmasks.utils;

import java.util.Collections;
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
        List<String> newLore = Collections.emptyList();
        //TODO  Create descriptions for each mask to be default first few lines.
        //newLore.add(maskType.getDescription();
        newLore.add("Rank 0");
        newLore.add("0 / " + skullsForRank1 + " skulls for next rank");
        return newLore;
    }

    //TODO Implement this.
    public static List<String> updateLore(List<String> oldLore) {
        //String[] splitStr = str.split("\\s+");
        return Collections.emptyList(); // Why return null what the fuck
    }



}

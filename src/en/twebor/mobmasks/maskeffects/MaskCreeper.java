package en.twebor.mobmasks.maskeffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Random;

public class MaskCreeper {
    private ItemStack helmet;
    private int[] explosionChances;
    private int[] explosionPower;
    private boolean blockDamage;
    private Player playerDamaged;

    public MaskCreeper(Player playerDamaged, ItemStack helm, int[] explosionChances, int[] explosionPower, boolean
                       blockDamage) {
        this.helmet = helm;
        this.explosionChances = explosionChances;
        this.explosionPower = explosionPower;
        this.blockDamage = blockDamage;
        this.playerDamaged = playerDamaged;
    }

    public void explode() {
        if (helmet.hasItemMeta()) {
            SkullMeta helmMeta = (SkullMeta) this.helmet.getItemMeta();
            if (willExplode(helmMeta)) {
                Location loc = this.playerDamaged.getLocation();
                loc.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
                        explosionPower[getTier(helmMeta)], false, this.blockDamage);
            }
        }
    }

    // Returns a boolean to decide if the explosion will occur.
    private boolean willExplode(SkullMeta helmetMeta) {
        int tier = getTier(helmetMeta);
        Random random = new Random();
        int number = random.nextInt(100) + 1;
        return (number <= explosionChances[tier]);
    }

    // Returns the tier of the mask.
    private int getTier(SkullMeta helmetMeta) {
        if (helmetMeta.hasLore()) {
            List<String> lore = helmetMeta.getLore();
            String[] splitStrRank = lore.get(1).split("\\s+"); //Splits rank by space.  Rank is index 1.
            int rank = Integer.parseInt(splitStrRank[1]);
            return rank;
        } else {
            return 0;
        }
    }
}

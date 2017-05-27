package en.twebor.mobmasks.maskeffects;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
            if (MaskUtils.willTriggerMaskEffect(helmMeta, explosionChances)) {
                Location loc = this.playerDamaged.getLocation();
                loc.getWorld().createExplosion(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(),
                        explosionPower[MaskUtils.getTier(helmMeta)], false, this.blockDamage);
            }
        }
    }



}

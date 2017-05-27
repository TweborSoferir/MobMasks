package en.twebor.mobmasks.maskeffects;

import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MaskBlaze {
    private Player player;
    private int[] fireballChance;
    private float[] fireballPower;

    public MaskBlaze(JavaPlugin plugin, Player player) {
        fireballChance = new int[] {
                plugin.getConfig().getInt("Blaze Mask.Fireball Chance.Tier 0"),
                plugin.getConfig().getInt("Blaze Mask.Fireball Chance.Tier 1"),
                plugin.getConfig().getInt("Blaze Mask.Fireball Chance.Tier 2"),
                plugin.getConfig().getInt("Blaze Mask.Fireball Chance.Tier 3")};
        fireballPower = new float[] {
                (float) plugin.getConfig().getDouble("Blaze Mask.Fireball Power.Tier 0"),
                (float) plugin.getConfig().getDouble("Blaze Mask.Fireball Power.Tier 1"),
                (float) plugin.getConfig().getDouble("Blaze Mask.Fireball Power.Tier 2"),
                (float) plugin.getConfig().getDouble("Blaze Mask.Fireball Power.Tier 3")};
        this.player = player;
    }

    public void launchFireball() {
        // Helm has already been checked as valid in the Listener.
        SkullMeta helmMeta = (SkullMeta) player.getInventory().getHelmet().getItemMeta();
        int tier = MaskUtils.getTier(helmMeta);
        if (MaskUtils.willTriggerMaskEffect(helmMeta, fireballChance)) {
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection();
            Location newLoc = loc.add(direction);

            Fireball fireball = player.getWorld().spawn(newLoc, Fireball.class);
            fireball.setDirection(direction.multiply(0.2));
            fireball.setVelocity(direction.multiply(0.2));
            fireball.setYield(fireballPower[tier]);
            fireball.setShooter(player);
        }
    }
}

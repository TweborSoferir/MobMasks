package en.twebor.mobmasks.maskeffects;

import en.twebor.mobmasks.utils.MaskEffectUtils;
import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Location;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class MaskBlaze {
    private Player player;
    private int[] fireballChance;
    private float[] fireballPower;
    private boolean setsFires;

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
        this.setsFires = plugin.getConfig().getBoolean("Blaze Mask.Sets Fires");
        this.player = player;
    }

    public void launchFireball() {
        // Helm has already been checked as valid in the Listener.
        SkullMeta helmMeta = (SkullMeta) player.getInventory().getHelmet().getItemMeta();
        int tier = MaskUtils.getTier(helmMeta);
        if (MaskEffectUtils.willTriggerMaskEffect(helmMeta, fireballChance)) {
            Location loc = player.getEyeLocation();
            Vector direction = loc.getDirection();
            Location newLoc = loc.add(direction);

            Fireball fireball = player.getWorld().spawn(newLoc, Fireball.class);
            fireball.setDirection(direction.multiply(0.4));
            fireball.setVelocity(direction.multiply(0.4));
            fireball.setYield(fireballPower[tier]);
            fireball.setIsIncendiary(setsFires);
            fireball.setShooter(player);
        }
    }
}

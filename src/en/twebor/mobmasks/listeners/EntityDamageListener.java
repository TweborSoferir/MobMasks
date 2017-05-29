package en.twebor.mobmasks.listeners;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.utils.MaskEffectUtils;
import en.twebor.mobmasks.utils.MaskUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

// Used for the chicken Mask.
public class EntityDamageListener implements Listener {
    private double[] fallDamageReduction; // Used for Chicken
    private double[] entityAttackDamageReduction; // Used for Golem

    public EntityDamageListener(JavaPlugin plugin) {
        fallDamageReduction = new double[]
                {1 - plugin.getConfig().getDouble("Chicken Mask.Fall Damage Reduction %.Tier 0") / 100,
                1 - plugin.getConfig().getDouble("Chicken Mask.Fall Damage Reduction %.Tier 1") / 100,
                1 - plugin.getConfig().getDouble("Chicken Mask.Fall Damage Reduction %.Tier 2") / 100,
                1- plugin.getConfig().getDouble("Chicken Mask.Fall Damage Reduction %.Tier 3") / 100};
        entityAttackDamageReduction = new double[] {
                1 - plugin.getConfig().getDouble("Iron Golem Mask.Damage Reduction %.Tier 0") / 100,
                1 - plugin.getConfig().getDouble("Iron Golem Mask.Damage Reduction %.Tier 1") / 100,
                1 - plugin.getConfig().getDouble("Iron Golem Mask.Damage Reduction %.Tier 2") / 100,
                1 - plugin.getConfig().getDouble("Iron Golem Mask.Damage Reduction %.Tier 3") / 100};
    }

    @EventHandler
    public void onEntityFallDamage(EntityDamageEvent event) {
        // Cancels if the damage cause was not falling.
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        // Cancels if the entity was not a Player.
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helm = player.getInventory().getHelmet();
        // Cancels if mask is not a valid chicken mask.
        if (!MaskEffectUtils.isValidMask(helm, Mask.CHICKEN)) return;
        // Creates the skullMeta for MaskUtils.getTier.
        SkullMeta helmMeta;
        if (!helm.hasItemMeta()) return;
        else {
            helmMeta = (SkullMeta) helm.getItemMeta();
        }
        // Multiplies the events damage by the reduction, and then sets the resulting value as the new one.
        event.setDamage(event.getDamage() * fallDamageReduction[MaskUtils.getTier(helmMeta)]);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Cancels if the entity damaged is not a player.
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        ItemStack helm = player.getInventory().getHelmet();
        // Cancels if not a valid golem mask.
        if (!MaskEffectUtils.isValidMask(helm, Mask.GOLEM)) return;
        SkullMeta helmMeta;
        if (!helm.hasItemMeta()) return;
        else {
            helmMeta = (SkullMeta) helm.getItemMeta();
        }
        // Multiplies the events damage by the reduction and sets the resulting value as the new one.
        event.setDamage(event.getDamage() * entityAttackDamageReduction[MaskUtils.getTier(helmMeta)]);
    }
}


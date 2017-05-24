package en.twebor.mobmasks;

import en.twebor.mobmasks.utils.MobUtil;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public enum Mask {
    CHICKEN(EntityType.CHICKEN),
    GOLEM(EntityType.IRON_GOLEM),
    BLAZE(EntityType.BLAZE),
    RABBIT(EntityType.RABBIT),
    HORSE(EntityType.HORSE),
    SKELETON(EntityType.SKELETON),
    CREEPER(EntityType.CREEPER);

    private final EntityType type;
    private final ItemStack skull;
    private final ItemStack mask;

    Mask(EntityType type) {
        this.type = type;

        if (this.type == EntityType.SKELETON) {
            this.skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.SKELETON.ordinal());
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Skeleton Mask");
            return;
        } else if (this.type == EntityType.CREEPER) {
            this.skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.CREEPER.ordinal());
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Creeper Mask");
            return;
        }

        this.skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        if (type == EntityType.CHICKEN) {
            MobUtil.setMaskMeta(this.skull, "Chicken Head", "MHF_Chicken");
            this.mask = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
            MobUtil.setMaskMeta(mask, "Chicken Mask", "MHF_Chicken");
        } else if (type == EntityType.IRON_GOLEM) {
            MobUtil.setMaskMeta(skull, "Iron Golem Head", "MHF_Golem");
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Iron Golem Mask", "MHF_Golem");
        } else if (type == EntityType.BLAZE) {
            MobUtil.setMaskMeta(skull, "Blaze Head", "MHF_Blaze");
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Blaze Mask", "MHF_Blaze");
        } else if (type == EntityType.RABBIT) {
            MobUtil.setMaskMeta(skull, "Rabbit Head", "Natalieisawesome");
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Rabbit Mask", "Natalieisawesome");
        } else if (type == EntityType.HORSE) {
            MobUtil.setMaskMeta(skull, "Horse Head", "gavertoso");
            this.mask = skull.clone();
            MobUtil.setMaskMeta(mask, "Horse Mask", "gavertoso");
        } else {
            // Must have this in order to use final for Mask.
            this.mask = null;
        }
    }

    public EntityType getType() {
        return this.type;
    }

    public ItemStack getSkull() {
        return this.skull;
    }

    public ItemStack getMask() {
        return this.mask;
    }
}

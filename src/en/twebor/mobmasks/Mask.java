package en.twebor.mobmasks;

import en.twebor.mobmasks.utils.ItemUtil;
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
            skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.SKELETON.ordinal());
            mask = skull.clone();
            ItemUtil.setSkullName(mask, "Skeleton Mask");
            return;
        } else if (this.type == EntityType.CREEPER) {
            skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.CREEPER.ordinal());
            mask = skull.clone();
            ItemUtil.setSkullName(mask, "Creeper Mask");
            return;
        }

        skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        mask = skull.clone();

        if (type == EntityType.CHICKEN) {
            ItemUtil.setSkullNameAndOwner(this.skull, "Chicken Head", "MHF_Chicken");
            ItemUtil.setSkullNameAndOwner(mask, "Chicken Mask", "MHF_Chicken");
        } else if (type == EntityType.IRON_GOLEM) {
            ItemUtil.setSkullNameAndOwner(skull, "Iron Golem Head", "MHF_Golem");
            ItemUtil.setSkullNameAndOwner(mask, "Iron Golem Mask", "MHF_Golem");
        } else if (type == EntityType.BLAZE) {
            ItemUtil.setSkullNameAndOwner(skull, "Blaze Head", "MHF_Blaze");
            ItemUtil.setSkullNameAndOwner(mask, "Blaze Mask", "MHF_Blaze");
        } else if (type == EntityType.RABBIT) {
            ItemUtil.setSkullNameAndOwner(skull, "Rabbit Head", "Natalieisawesome");
            ItemUtil.setSkullNameAndOwner(mask, "Rabbit Mask", "Natalieisawesome");
        } else if (type == EntityType.HORSE) {
            ItemUtil.setSkullNameAndOwner(skull, "Horse Head", "gavertoso");
            ItemUtil.setSkullNameAndOwner(mask, "Horse Mask", "gavertoso");
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

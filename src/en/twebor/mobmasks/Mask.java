package en.twebor.mobmasks;

import en.twebor.mobmasks.utils.ItemUtils;
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
            ItemUtils.setSkullName(mask, "Skeleton Mask");
            return;
        } else if (this.type == EntityType.CREEPER) {
            skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.CREEPER.ordinal());
            mask = skull.clone();
            ItemUtils.setSkullName(mask, "Creeper Mask");
            return;
        }

        skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        mask = skull.clone();

        if (type == EntityType.CHICKEN) {
            ItemUtils.setSkullNameAndOwner(skull, "Chicken Head", "MHF_Chicken");
            ItemUtils.setSkullNameAndOwner(mask, "Chicken Mask", "MHF_Chicken");
        } else if (type == EntityType.IRON_GOLEM) {
            ItemUtils.setSkullNameAndOwner(skull, "Iron Golem Head", "MHF_Golem");
            ItemUtils.setSkullNameAndOwner(mask, "Iron Golem Mask", "MHF_Golem");
        } else if (type == EntityType.BLAZE) {
            ItemUtils.setSkullNameAndOwner(skull, "Blaze Head", "MHF_Blaze");
            ItemUtils.setSkullNameAndOwner(mask, "Blaze Mask", "MHF_Blaze");
        } else if (type == EntityType.RABBIT) {
            ItemUtils.setSkullNameAndOwner(skull, "Rabbit Head", "Natalieisawesome");
            ItemUtils.setSkullNameAndOwner(mask, "Rabbit Mask", "Natalieisawesome");
        } else if (type == EntityType.HORSE) {
            ItemUtils.setSkullNameAndOwner(skull, "Horse Head", "gavertoso");
            ItemUtils.setSkullNameAndOwner(mask, "Horse Mask", "gavertoso");
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

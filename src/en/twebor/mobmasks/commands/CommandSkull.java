package en.twebor.mobmasks.commands;

import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CommandSkull implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
        skull.setDurability((short) 3);
        skull.addUnsafeEnchantment(Enchantment.DURABILITY, 0);

        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner("MHF_Chicken");
        skullMeta.setDisplayName("Chicken Mask");
        skullMeta.setUnbreakable(true);
//        skullMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        skull.setItemMeta((ItemMeta) skullMeta);
        skull = addGlow(skull);

        player.getInventory().addItem(skull);

        return true;
    }

    public static ItemStack addGlow(ItemStack item){
        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
}

package en.twebor.mobmasks.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
        skullMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        skullMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        skull.setItemMeta((ItemMeta) skullMeta);

        player.getInventory().addItem(skull);

        return true;
    }
}

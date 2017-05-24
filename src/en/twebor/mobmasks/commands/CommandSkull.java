package en.twebor.mobmasks.commands;

import en.twebor.mobmasks.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandSkull implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        ItemUtil.setSkullNameAndOwner(skull, "Blaze Mask", "MHF_Blaze");

        player.getInventory().addItem(skull);

        player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);

        return true;
    }
}

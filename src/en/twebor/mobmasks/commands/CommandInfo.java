package en.twebor.mobmasks.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Set;

public class CommandInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        PlayerInventory inventory = player.getInventory();
        ItemStack itemInHand = inventory.getItemInMainHand();

        if (itemInHand == null) {
            player.sendMessage("No item in hand!");
        } else {
            if (itemInHand.hasItemMeta()) {
                ItemMeta itemMeta = itemInHand.getItemMeta();

                if (itemInHand.getType() == Material.SKULL_ITEM) {
                    SkullMeta skullMeta = (SkullMeta) itemInHand.getItemMeta();
                    if (skullMeta.hasOwner()) {
                        player.sendMessage("The owner is " + skullMeta.getOwner());
                    } else {
                        player.sendMessage("No owner.");
                    }
                }

                Set<ItemFlag> flagSet = itemMeta.getItemFlags();

                for (ItemFlag flag : flagSet) {
                    player.sendMessage(flag.name());
                }

                if (itemMeta.hasLore()) {
                    player.sendMessage("Has lore");
                }

                if (itemMeta.hasDisplayName()) {
                    player.sendMessage("Item display name: " + itemMeta.getDisplayName());
                } else {
                    player.sendMessage("No display name.");
                }

                if (itemMeta.hasLocalizedName()) {
                    player.sendMessage("Localized name: " + itemMeta.getLocalizedName());
                } else {
                    player.sendMessage("No localized name.");
                }
            } else {
                player.sendMessage("No item meta.");
            }

            player.sendMessage("Type name: " + itemInHand.getType().name());
        }
        return true;
    }
}

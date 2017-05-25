package en.twebor.mobmasks.commands;

import en.twebor.mobmasks.Mask;
import en.twebor.mobmasks.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandMobMasks implements CommandExecutor {
    private Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            this.player = (Player) commandSender;
            if (strings.length == 0) {
                // Outputs how many heads the item in mainhand has.
//                player.sendMessage("AMOUNT OF HEADS PLACEHOLDER");
                return true;
            }

            switch (strings[0].toLowerCase()) {
                case "help":
                    helpCommand();
                    break;
                case "give":

                    break;
                case "info":

                    break;
                default:
                    player.sendMessage("Incorrect usage: ");
                    return false;
            }
        }
        return true;
    }

    private void helpCommand() {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "MobMasks Help");

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemUtils.setItemName(diamondSword, ChatColor.BLUE + "Plugin Info");
        ItemUtils.addAllHideItemFlags(diamondSword);

        ItemStack bow = new ItemStack(Material.BOW, 1);
        ItemUtils.setItemName(bow, ChatColor.GOLD + "Commands");

        ItemStack expBottle = new ItemStack(Material.EXP_BOTTLE, 1);
        ItemUtils.setItemName(expBottle, ChatColor.GREEN + "Levelling Up");

        ItemStack creeperHead = Mask.CREEPER.getSkull();
        ItemUtils.setItemName(creeperHead, ChatColor.DARK_PURPLE + "Mask Effects");

        inventory.addItem(diamondSword, bow, expBottle, creeperHead);

        player.openInventory(inventory);
    }
}

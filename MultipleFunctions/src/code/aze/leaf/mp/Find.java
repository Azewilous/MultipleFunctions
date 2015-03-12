package code.aze.leaf.mp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class  Find implements CommandExecutor {

    MultipleFunctions plugin;

    public Find(MultipleFunctions passedPlugin) {
        this.plugin = passedPlugin;
    }

    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if(!player.hasPermission("mp.find") || player.isOp()){
            player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.find "
                    + ChatColor.RED + "To Execute This Command");
        }
        if (args.length == 0){
            player.sendMessage(ChatColor.RED + "Please specify a player.");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "Could not find player " + args[0] + "!");
            return true;
        }
        player.teleport(target.getLocation());
        return true;

    }
}
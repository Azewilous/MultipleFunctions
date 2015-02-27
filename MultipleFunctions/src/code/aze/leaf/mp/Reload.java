package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {

MultipleFunctions plugin;
	
	public Reload(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		Player player = (Player) sender;
		if(player.hasPermission("mp.reload") || player.isOp()){
		if (args.length > 0) {
            sender.sendMessage(ChatColor.RED + "To many arguments.");
            return false;
        }
        if (sender instanceof Player) {
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage(ChatColor.YELLOW + "Reloaded "+ ChatColor.GRAY + plugin.getDescription().getFullName());
            }
		} else 
			player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.reload "
                    + ChatColor.RED + "To Execute This Command");
        return true;	
	}
	
}

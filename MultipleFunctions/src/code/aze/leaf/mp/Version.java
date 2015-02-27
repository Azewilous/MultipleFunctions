package code.aze.leaf.mp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Version implements CommandExecutor {

	MultipleFunctions plugin;
	
	public Version(MultipleFunctions passedPlugin) {
		this.plugin = passedPlugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		Player player = (Player) sender;
		
		String version = Bukkit.getServer().getPluginManager().getPlugin("MultipleFunctions").getDescription().getVersion();
		if(player.hasPermission("mp.version")){
		if(args.length > 0){
			player.sendMessage(ChatColor.RED + "Too Many Arguments");
		} else {
			player.sendMessage(ChatColor.DARK_AQUA + plugin.getName() + " " + ChatColor.DARK_AQUA + version);
		}
	} else 
		player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.version "
                + ChatColor.RED + "To Execute This Command");
		return true;
	}

}

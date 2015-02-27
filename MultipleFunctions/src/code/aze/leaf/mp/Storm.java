package code.aze.leaf.mp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Storm implements CommandExecutor {
    
	MultipleFunctions plugin;
	public Storm(MultipleFunctions passedPlugin){
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("mp.storm") || player.isOp()){
		if(args.length == 0){
		player.getWorld().setThundering(true);
        player.getWorld().setStorm(true);
		}
	} else 
		player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.storm "
                + ChatColor.RED + "To Execute This Command");
		return false;
	}

}
class StormEnd implements CommandExecutor{
	MultipleFunctions plugin;
	public StormEnd(MultipleFunctions passedPlugin){
		this.plugin = passedPlugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
	Player player = (Player) sender;
	if(player.hasPermission("mp.stormend") || player.isOp()){
	if(args.length == 0){
		player.getWorld().setStorm(false);
	}
	} else 
		player.sendMessage(ChatColor.RED + "You Need The Permission Node " + ChatColor.AQUA + "mp.stormend "
                + ChatColor.RED + "To Execute This Command");
		return true;
	}
}